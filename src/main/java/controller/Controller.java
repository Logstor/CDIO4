package controller;

import gui_tests.PlayerTest;
import model.board.Field;
import model.cup.Cup;
import model.player.Player;
import view.gui.Gui;

import java.awt.*;

/**
 * @author Nikolaj Tscharn Wassmann
 * @date 10-01-2019
 */

public class Controller {


    /*
    ------------ Fields ------------------
    */

    private Gui gui;


    /*
    --------- Constructors ---------------
     */

    public Controller(Field[] fields) {

        //Creates the Graphic User Interface using the Field array
        gui = new Gui(fields);
    }


    /*
    --------- Public Methods ----------
    */

    public void setOwner(Player player, Field field) {

        gui.setFieldOwner(player, field);
    }

    public void addPlayer(Player player) {

        gui.addPlayer(player);

    }

    public void displayCCard() {

        gui.displayChanceCard();
    }

    public void setCCard(String cardtext) {

        gui.setChanceCard(cardtext);
    }


    public void getUserButton2(String message, String button1, String button2) {

        //Display a message to the user and awaits response
        gui.getUserChoice(message, button1, button2);
    }

    public void getUserButton1 (String message, String button){

        gui.getUserChoice(message, button);
    }

    public void getUserString(String message) {

        //Display message to the user
        gui.getUserString(message);
    }

    public void getBGColor(Color color) {

        gui.getBackgroundColor(color);
    }

    public void getUserInteger(String message, int min, int max) {

        //Display a message and awaits integer response
        gui.getUserInteger(message, min, max);
    }

    public void movePlayer(Player player, int move) {

        gui.movePlayer(player, move);
    }

    public void WinnerMode() throws InterruptedException {

        gui.partyMode();
    }

    public void updateBalance(Player player, int balance) {

        gui.updatePlayerBalance(player, balance);
    }

    public void showMessage(String message) {

        gui.showMessage(message);
    }

    public void setDie(int value) {

        gui.setDie(value);
    }

    public void getTextColor(Color color) {

        gui.getTextColor(color);
    }


    public void setTextColor(Color Tcolor) {

        gui.setTextColor(Tcolor);

    }

    public void setBGColor(Color BGcolor) {

        gui.setBackgroundColor(BGcolor);

    }


}
