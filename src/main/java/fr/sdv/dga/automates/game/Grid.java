package fr.sdv.dga.automates.game;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

/**
 * This Grid class manage the board you see on STDOUT
 */
public class Grid {

    private int RANGE_NEIGHBORS = 3, START_COUNTING = 1;
    private int DEFAULT_COLUMN = 10,DEFAULT_ROW = 10, DEFAULT_PAUSE = 2000;
    private int column, row, generation;

    private ArrayList<ArrayList<Cell>> board;

    /**
     * Generate grid with random cells
     * @param column
     * @param row
     * @param generation
     */
    public Grid(int column, int row, int generation){
        this.column = column;
        this.row = row;
        this.generation = generation;

        this.board = new ArrayList(column);

        initRandomBoard();
    }

    /**
     * Generate grid depend on figure, with default column,row and generation
     * @param figure
     */
    public Grid(Figures figure){
        this.column = DEFAULT_COLUMN;
        this.row = DEFAULT_ROW;

        this.board = new ArrayList(column);

        initEmptyBoard();

        switch (figure){
            case PLANE -> {
                setPlane();
            }
            case FLASHING -> {
                setFlash();
            }
        }

    }

    /**
     * Set flashing shape for grid
     */
    private void setFlash(){

        int middleFlash1 = (DEFAULT_ROW / 2) - 1;
        int middleFlash2 = (DEFAULT_ROW / 2) + 1;

        setCell(new Cell(middleFlash1 -1,middleFlash1 - 1, true));
        setCell(new Cell(middleFlash1 - 2, middleFlash1 - 1, true));
        setCell(new Cell(middleFlash1, middleFlash1 - 1, true));

        setCell(new Cell(middleFlash2 -1,middleFlash2 + 1, true));
        setCell(new Cell(middleFlash2 - 2, middleFlash2 + 1, true));
        setCell(new Cell(middleFlash2, middleFlash2 + 1, true));

    }

    /**
     * Set plane shape for grid
     */
    private void setPlane(){

        int y = DEFAULT_ROW - 1;

        setCell(new Cell(1,y, true));

        setCell(new Cell(2, y - 1, true));

        //Bottom line
        setCell(new Cell(0, y - 2, true));
        setCell(new Cell(1, y - 2, true));
        setCell(new Cell(2, y - 2, true));

    }

    /**
     * Init board with dead cells
     */
    private void initEmptyBoard(){

        for (int x = 0; x < column; x++) {
            board.add(new ArrayList<>(row));
            for (int y = 0; y < row; y++) {
                board.get(x).add(new Cell(x, y,false));
            }
        }

    }

    /**
     * Fill the board with randoms cells
     */
    private void initRandomBoard(){

        for (int x = 0; x < column; x++) {
            board.add(new ArrayList<>(row));
            for (int y = 0; y < row; y++) {
                board.get(x).add(new Cell(x, y));
            }
        }

    }

    /**
     * Get cell at x and y coordinates
     * @param x
     * @param y
     * @return
     */
    public Cell getCell(int x,int y){

        if (!isInTheBoard(x, y)) throw new IllegalArgumentException("x or y is out of the board");
        return this.board.get(x).get(y);
    }

    /**
     * Get cell at x and y coordinates
     * @param cell
     * @return
     */
    public Cell getCell(Cell cell){

        int x = cell.getX(), y = cell.getY();

        if (!isInTheBoard(x,y)) throw new IllegalArgumentException("x or y is out of the board");
        return this.board.get(x).get(y);
    }

    /**
     * Set Cell depend on x and y position
     * Useful for test case
     * @param cell
     */
    public void setCell(Cell cell){
        this.board.get(cell.getX()).set(cell.getY(), cell);
    }

    /**
     * Draw grid on STDOUT
     * @return
     */
    public String toString(){

        String result = "";
        Cell cell = null;

        for (int y = 0; y < row; y++) {
            for (int x = 0; x < column ; x++) {
                cell = getCell(x,y);
                if (x == 0){
                    result += "|"+cell.draw()+"|";
                } else {
                    result += cell.draw()+"|";
                }
            }
            result += "\n";
        }

        return result;
    }

    /**
     * Draw the grid with generation number
     * @param generation
     */
    private void draw(int generation){
        System.out.printf("%d : Génération\n", generation);
        System.out.println(this);
    }

    /**
     * Run Game over generations
     */
    public void runGame()throws InterruptedException{
        for (int i = 0; i < generation; i++) {
            game(i);
            if( i != generation - 1)
                pause(DEFAULT_PAUSE);
        }
    }

    /**
     * Rune game while cells live and there is no infinity loop
     * @throws InterruptedException
     */
    public void runWhileCellsLive() throws InterruptedException{

        int i = 0;

        while (countAliveCell() != 0){
            game(i);
            pause(200);
            i++;
        }
    }

    /**
     * Pause main thread
     * @param time
     * @throws InterruptedException
     */
    private void pause(int time) throws  InterruptedException{
        Thread.sleep(time);
    }

    /**
     * Draw game and scan
     * @param iteration
     */
    private void game(int iteration){
        draw(iteration);
        scan();
    }

    /**
     * Generate new board with number of column and row set
     * @return ArrayList<ArrayList<Cell>>
     */
    private ArrayList<ArrayList<Cell>> generateNewBoard(){

        ArrayList<ArrayList<Cell>> board = new ArrayList<>(column);

        for (int x = 0; x < column; x++) {
            board.add(new ArrayList<>(row));
        }

        return board;
    }

    /**
     * Scan whole board, and apply rule on each cell
     */
    private void scan(){

        Cell cell;
        int neighbors;
        boolean status;

        ArrayList<ArrayList<Cell>> newBoard = generateNewBoard();

        for (int x = 0; x < column; x++) {
            for (int y = 0; y < row; y++) {
                cell = getCell(x,y);

                neighbors = countNeighbors(cell);

                status = cell.rule(neighbors);
                newBoard.get(x).add(new Cell(x, y,status));
            }
        }

        setBoard(newBoard);

    }

    /**
     * Check if x and y is in the board
     * @param x
     * @param y
     * @return
     */
    private Boolean isInTheBoard(int x,int y){
        return (y >= 0 && y < row) && (x >= 0 && x < column);
    }

    /**
     * To count neighbors we start in the top left corner, then we read line by line
     * We don't count current cell and coordinates out of the board
     * @return
     */
    public int countNeighbors(Cell cell){

        Cell neighbors;
        int count = 0, x, y;

        for (int i = 0; i  < RANGE_NEIGHBORS; i++) {
            for (int j = 0; j < RANGE_NEIGHBORS; j++) {

                x = (cell.getX() - START_COUNTING) + j;
                y = (cell.getY() - START_COUNTING) + i;

                if (isInTheBoard(x,y) && !cell.isSameCoordinates(x,y)){
                    neighbors = getCell(x,y);
                    count += neighbors.isAlive() ? 1 : 0;
                }

            }
        }

        return count;
    }

    /**
     * Get Column
     * @return
     */
    public int getColumn() {
        return column;
    }

    /**
     * Set Column
     * @param column
     */
    public void setColumn(int column) {
        this.column = column;
    }

    /**
     * Get row
     * @return
     */
    public int getRow() {
        return row;
    }

    /**
     * Set row
     * @param row
     */
    public void setRow(int row) {
        this.row = row;
    }


    /**
     * Count how much cell are alive in the board
     * Used for test case
     * @return
     */
    public int countAliveCell(){

        int count = 0;

        for (int x = 0; x < column; x++) {
            for (int y = 0; y < row; y++) {
                if (getCell(x,y).isAlive()){
                    count++;
                }
            }
        }

        return count;
    }

    /**
     * Set board
     * @param board
     */
    public void setBoard(ArrayList<ArrayList<Cell>> board) {
        this.board = board;
    }

}
