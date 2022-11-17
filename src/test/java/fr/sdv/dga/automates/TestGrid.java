package fr.sdv.dga.automates;

import fr.sdv.dga.automates.game.Cell;
import fr.sdv.dga.automates.game.Grid;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestGrid {

    private int ROW = 6, COLUMN = 5, CELL_PER_LINE = 3;

    private Grid basicGrid,complexGrid;

    @Before
    public void init_grids(){
        basicGrid  = new Grid(COLUMN, ROW, 0);
        complexGrid = new Grid(3,3, 0);
    }

    /**
     * |X| |X|
     * | |X| | Init complex grid
     * |X| |X|
     */
    @Before
    public void init_complex_grid(){

        for (int i = 0; i < CELL_PER_LINE; i ++) {
            for (int j = 0; j < CELL_PER_LINE; j++) {
                complexGrid.setCell(new Cell(i, j, false));
            }
        }

        complexGrid.setCell(new Cell(0,0, true));
        complexGrid.setCell(new Cell(2,0, true));

        complexGrid.setCell(new Cell(1,1, true));

        complexGrid.setCell(new Cell(0,2, true));
        complexGrid.setCell(new Cell(2,2, true));

    }

    /**
     * Check number of columns
     */
    @Test
    public void column_size(){
        assertEquals(COLUMN, basicGrid.getColumn());
    }

    /**
     * Check number of rows
     */
    @Test
    public void row_size(){
        assertEquals(ROW, basicGrid.getRow());
    }

    @Test
    public void get_cell_at_correct_coordinates(){
        Cell cell = basicGrid.getCell(2,3);

        assertEquals(2,cell.getX());
        assertEquals(3,cell.getY());
    }

    /**
     * Exception should have thrown
     * Test method : isInTheBoard
     */
    @Test(expected = IllegalArgumentException.class)
    public void get_cell_out_the_board(){
        basicGrid.getCell(21,-4);
    }

    /**
     * Cell out of the board
     * Test method : isInTheBoard
     */
    @Test(expected = IllegalArgumentException.class)
    public void cell_out_of_the_board(){
        Cell cell = new Cell(10,3);

        basicGrid.getCell(cell);
    }

    /**
     * Cell in the board
     * Test method : isInTheBoard
     */
    @Test
    public void cell_in_the_board(){

        int x = 0, y = 1;

        Cell cell = basicGrid.getCell(x,y);

        assertEquals(cell.getX(), x);
        assertEquals(cell.getY(), y);

    }

    /**
     * Testing with limit of the board
     * Test method : isInTheBoard
     */
    @Test
    public void cell_in_the_board_limit(){
        int x = 0, y = 3;

        Cell cell = basicGrid.getCell(x,y);

        assertEquals(cell.getX(), x);
        assertEquals(cell.getY(), y);

    }

    /**
     * |X| |X| <- Testing here (0,0)
     * | | | |
     * |X| |X|
     * Count 0 neighbor for the cell in the top left corner
     */
    @Test
    public void count_0_neighbor_for_cell_in_the_top_left_corner(){
        Cell cell = new Cell(1,1, false);
        Cell cellTopLeft = complexGrid.getCell(0,0);

        complexGrid.setCell(cell);

        assertEquals(0,complexGrid.countNeighbors(cellTopLeft));
    }

    /**
     * |X| |X| <- Testing here (0,0)
     * | |X| |
     * |X| |X|
     * Count 1 neighbor for the cell in the top left corner
     */
    @Test
    public void count_1_neighbor_for_cell_in_the_top_left_corner(){
        Cell cellTopLeft = complexGrid.getCell(0,0);

        assertEquals(1,complexGrid.countNeighbors(cellTopLeft));
    }

    /**
     * |X| |X|
     * | |X| | <- Testing here (1,1)
     * |X| |X|
     * Count 4 neighbors for the cell at the middle
     */
    @Test
    public void count_4_neighbors_for_cell_at_the_middle(){
        Cell cellMid = complexGrid.getCell(1,1);

        assertEquals(4, complexGrid.countNeighbors(cellMid));
    }

    /**
     * |X| |X|
     * | |X| | <- Testing here (1,1)
     * |X| | |
     * Count 3 neighbors for the cell at the middle
     */
    @Test
    public void count_3_neighbors_for_cell_at_the_middle(){
        Cell cellBottomRight = complexGrid.getCell(2,2);
        Cell cellMid = complexGrid.getCell(1,1);

        cellBottomRight.setAlive(false);

        assertEquals(3, complexGrid.countNeighbors(cellMid));
    }

    /**
     * |X| |X| <- Testing here (0,0)
     * |X|X| |
     * |X| |X|
     * Count 2 neighbors for the cell in the top left corner
     */
    @Test
    public void count_2_neighbors_for_cell_in_the_top_left_corner(){
        Cell cellTopLeftCorner = complexGrid.getCell(0,0);
        Cell cellMidLeft = complexGrid.getCell(0,1);

        cellMidLeft.setAlive(true);

        assertEquals(2, complexGrid.countNeighbors(cellTopLeftCorner));
    }

    /**
     * Start
     * |X|X|X|
     * |X| | |
     * |X| |X|
     *
     * End
     * |X|X| |
     * |X| | |
     * | | | |
     */

    @Test
    public void count_neighbors_for_each_cells(){
        complexGrid.getCell(1,0).setAlive(true);
        complexGrid.getCell(1,1).setAlive(false);
        complexGrid.getCell(0,1).setAlive(true);

        Cell cell;
        int neighbor, i = 0;

        int[] expected = new int[]{2,3,1,3,5,2,1,2,0};

        int[] neighbors = new int[9];

        for (int y = 0; y < CELL_PER_LINE; y++) {
            for (int x = 0; x < CELL_PER_LINE; x++) {
                cell = complexGrid.getCell(x, y);
                neighbor = complexGrid.countNeighbors(cell);

                cell.rule(neighbor);
                neighbors[i] = neighbor;

                i++;
            }
        }

        assertArrayEquals(expected,neighbors);

    }

    /**
     * |X| |X|
     * | |X| | <- Testing here (1,1)
     * |X| |X|
     */
    @Test
    public void count_5_cells_alive(){
        assertEquals(5, complexGrid.countAliveCell());
    }

}
