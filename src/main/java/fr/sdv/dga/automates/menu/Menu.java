package fr.sdv.dga.automates.menu;

import fr.sdv.dga.automates.game.Figures;
import fr.sdv.dga.automates.game.Grid;

import java.util.Scanner;

public class Menu {

    Scanner scanner = new Scanner(System.in);
    Grid grid;

    /**
     * Display menu, to choose game
     */
    public void menu() throws InterruptedException{

        int choice = -1;
        boolean flag = true;

        while(flag){
            System.out.println("Welcome to the game of life !");
            System.out.println("1 - Random game\n2 - Plane shape\n3 - Flashing shape\n4 - While everyone live\n5 - Quit");

            try{
                choice = scanner.nextInt();

            }catch (Exception e){
                System.out.println("You should choose number between 1 and 4.");
                scanner.nextLine();
            }


            switch (choice){
                case 1 ->{
                    randomGame();
                    break;
                }

                case 2 ->{
                    planeShapeGame();
                    break;
                }

                case 3 ->{
                    flashingShapeGame();
                    break;
                }

                case 4 ->{
                    whileEveryoneLive();
                    break;
                }
                case 5 ->{
                    flag = false;
                    break;
                }
                default -> {
                    break;
                }

            }

        }

        System.out.println("I hope you enjoyed the game !");

    }

    /**
     * Play with random cell
     * @throws InterruptedException
     */
    private void randomGame() throws InterruptedException{

        int column = askParameter("Number of column(s)");
        int row = askParameter("Number of row(s)");
        int generation = askParameter("Number of generation(s)");

        grid = new Grid(column, row, generation);

        grid.runGame();

    }

    /**
     * Play with plane shape
     */
    private void planeShapeGame() throws InterruptedException{
        grid = new Grid(Figures.PLANE);

        grid.runWhileCellsLive();
    }

    /**
     * Play with flashing shape
     * @throws InterruptedException
     */
    private void flashingShapeGame() throws InterruptedException{
        grid = new Grid(Figures.FLASHING);

        grid.runWhileCellsLive();

    }

    /**
     * Play while one cell live
     * @throws InterruptedException
     */
    private void whileEveryoneLive() throws InterruptedException{
        int column = askParameter("Number of column(s)");
        int row = askParameter("Number of row(s)");

         grid = new Grid(column, row,0);

         grid.runWhileCellsLive();
    }

    /**
     * Ask parameter for grid class
     * @param message
     * @return
     */
    private int askParameter(String message){

        int choice = -1;

        while(choice == -1){
            System.out.printf("%s : ", message);

            try{
                choice = scanner.nextInt();
            }catch (Exception e){
                System.out.println("You should choose number.");
                scanner.nextLine();
            }
        }

        return choice;
    }

}
