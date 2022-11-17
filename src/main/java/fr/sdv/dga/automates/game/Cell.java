package fr.sdv.dga.automates.game;

import java.util.Random;

public class Cell {

    private int CELLS_TO_BORN=3, CELLS_TO_LIVE=2;

    private int x, y;
    private Boolean isAlive;

    /**
     * Each Cell created is set with random isAlive
     * @param x
     * @param y
     */
    public Cell(int x, int y){

        if (x < 0 || y < 0){
            throw new IllegalArgumentException("x and y must be higher or equal to 0");
        }

        this.x = x;
        this.y = y;
        this.isAlive = this.initIsAlive();
    }

    /**
     * Init each attributes
     * @param x
     * @param y
     * @param isAlive
     */
    public Cell(int x, int y, boolean isAlive){

        if (x < 0 || y < 0){
            throw new IllegalArgumentException("x and y must be higher or equal to 0");
        }

        this.x = x;
        this.y = y;
        this.isAlive = isAlive;
    }

    /**
     * Random value for isAlive
     * @return
     */
    private Boolean initIsAlive(){
        Random rd = new Random();
        return rd.nextBoolean();
    }

    /**
     * Check if both x and y are the same as the current cell
     * @param x
     * @param y
     * @return
     */
    public Boolean isSameCoordinates(int x, int y){
        return x == this.x && y == this.y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Boolean isAlive() {
        return isAlive;
    }

    public void setAlive(Boolean alive) {
        isAlive = alive;
    }

    /**
     * Translate boolean value to X or space char
     * @return
     */
    public String draw(){
        return isAlive ? "*" :" ";
    }

    public String toString(){
        return "x : "+x+" y : "+y+" "+draw()+"\n";
    }

    /**
     * Rules if cell live or dead
     *
     * Live :
     * neighbors == 2
     *
     * Born :
     * neighbors == 3
     *
     * Die when :
     * neighbors > 3 or neighbors < 2
     *
     * @param neighbors
     */
    public boolean rule(int neighbors){

        boolean flag = this.isAlive;

        if (isAlive){
            if ( neighbors < CELLS_TO_LIVE || neighbors > CELLS_TO_BORN )
                flag = false;
        } else{
            if(neighbors == CELLS_TO_BORN)
                flag = true;
        }

        return flag;
    }
}
