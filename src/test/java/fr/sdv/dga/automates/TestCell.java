package fr.sdv.dga.automates;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestCell {

    private Cell cellAlive, cellDead;

    private int COLUMN = 3, ROW = 3;

    private Grid grid;

    /**
     * |X| | | <- Testing here (0,0)
     * | |X| |
     * | | | |
     */
    @Before
    public void init_grid(){
        grid = new Grid(COLUMN,ROW, 0);

        for (int i = 0; i < COLUMN; i ++) {
            for (int j = 0; j < ROW; j++) {
                grid.setCell(new Cell(i, j, false));
            }
        }

        grid.setCell(new Cell(0,0,true));
        grid.setCell(new Cell(1,1,true));
    }

    @Before
    public void init_dead_and_alive_cells(){
        cellAlive = new Cell(3,4,true);
        cellDead = new Cell(2,5,false);
    }

    @Test
    public void draw_by_status(){
        assertEquals(cellAlive.draw(), "X");
        assertEquals(cellDead.draw(), " ");
    }

    @Test
    public void not_the_same_coordinates(){
        assertFalse(cellAlive.isSameCoordinates(cellDead.getX(), cellDead.getY()));
    }

    @Test
    public void same_coordinates(){
        Cell cell = new Cell(3,4);
        assertTrue(cellAlive.isSameCoordinates(cell.getX(), cell.getY()));
    }

    @Test
    public void cell_is_alive(){
        assertTrue(cellAlive.isAlive());
    }

    @Test
    public void cell_is_dead(){
        assertFalse(cellDead.isAlive());
    }

    @Test(expected = IllegalArgumentException.class)
    public void negative_coordinates_should_return_exception(){
        new Cell(-10,-5);
    }

    /**
     * |X| | | <- Testing here (0,0)
     * | |X| |
     * | | | |
     * Apply rule for the cell at top left corner
     */
    @Test
    public void rule_with_1_neighbors_cell_at_top_left_corner(){

        int NUMBER_OF_NEIGHBOR = 1;

        Cell cellTopLeftCorner = grid.getCell(0,0);

        assertFalse(cellTopLeftCorner.rule(NUMBER_OF_NEIGHBOR));
    }

    /**
     * |X| | | <- Testing here (0,0)
     * |X|X| |
     * | | | |
     */
    @Test
    public void rule_with_2_neighbors_cell_at_top_left_corner(){
        Cell cellTopLeftCorner = grid.getCell(0,0);
        Cell cellMidLeft = grid.getCell(0,1);

        cellMidLeft.setAlive(true);

        cellTopLeftCorner.rule(2);

        assertEquals(grid.countAliveCell(), 3);
    }

    /**
     * |X| | | <- Testing here (1,0)
     * |X|X| |
     * | | | |
     */
    @Test
    public void rule_with_3_neighbors_cell_at_top_middle(){

        int NUMBER_NEIGHBORS = 3;

        Cell cellTopMiddle = grid.getCell(1,0);

        grid.getCell(0,1).setAlive(true);

        assertTrue(cellTopMiddle.rule(NUMBER_NEIGHBORS));
    }

}
