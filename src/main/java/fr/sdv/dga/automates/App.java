package fr.sdv.dga.automates;

public class App {
    public static void main(String[] args) {


        int COLUMN = 5, ROW = 5, GENERATION = 2;

        Grid randomGrid = new Grid(COLUMN,ROW, GENERATION);
        Grid shapePlaneur = new Grid(Figures.PLANEUR), shapeClignotant = new Grid(Figures.CLIGNOTANT);

        try{
            randomGrid.runWhileCellsLive();
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
