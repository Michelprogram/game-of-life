package fr.sdv.dga.automates;

public class App {
    public static void main(String[] args) {
        Menu app = new Menu();

        try{
            app.menu();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
