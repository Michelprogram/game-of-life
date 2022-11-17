package fr.sdv.dga.automates;

import fr.sdv.dga.automates.menu.Menu;

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
