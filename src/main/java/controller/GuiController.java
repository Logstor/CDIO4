package controller;

import model.board.Field;
import model.player.Player;
import view.gui.Gui;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author Nikolaj Tscharn Wassmann
 * @date 10-01-2019
 */

public class GuiController {


    /*
    ------------ Fields ------------------
    */

    private Gui gui;


    /*
    --------- Constructors ---------------
     */

    public GuiController(Gui gui)
    {
        // Give same reference
        this.gui = gui;
    }


    /*
    --------- Public Methods ----------
    */


    /**
     * Shows a message and a dropdown menu, and returns the
     * choosen String.
     * @param message The information to the user
     * @param options The available options as ArrayList<String></String>
     * @return the choosen String as String
     */
    public String getUserChoice (String message, ArrayList<String> options) {

        String[] optionList = options.toArray( new String [options.size()]);

        // Return the String
        return gui.getUserChoice(message, optionList);
    }

    public void setOwner(Player player, Field field) {

        gui.setOwner(player, field);
    }

    /**
     * Sets the Field Border (PLAIN) to the color of the Players Car.
     * @param player The Player that the BorderColor is decided on.
     * @param theField The Field which gets it borders Colored.
     */
    public void setPlainBorderWithPlayerCarColor(Player player, Field theField) {

        gui.setPlainBorderWithPlayerCarColor(player,theField);

    }

    /**
     * Sets the Field Border (DOTTED) to the color of the Players Car and LightGrey.
     * @param player The Player that the BorderColor is decided on.
     * @param theField The Field which gets it borders Colored.
     */
    public void setDottedBorderWithPlayerCarColor(Player player, Field theField) {

        gui.setDottedBorderWithPlayerCarColor(player,theField);

    }



    public void addPlayer(Player player) {

        gui.addPlayer(player);

    }

    public void displayCCard() {

        gui.displayChanceCard();
    }

    public void setCCard(String cardText) {

        gui.setChanceCard(cardText);
    }

    public String getUserButton2(String message, String button1, String button2) {

        String choosen;
        //Display a message to the user and awaits response
        choosen=gui.getUserChoice(message, button1, button2);

        return choosen;
    }

    public void getUserButton1 (String message, String button){

        gui.getUserChoice(message, button);
    }

    public boolean getLeftButtonPressed (String message, String truebutton, String falsebutton){

        return gui.getLeftButtonPressed(message, truebutton, falsebutton);

    }

    public String getUserString(String message) {

        //Display message to the user
        return gui.getUserString(message);

    }

    public void getBGColor(Color color) {

        gui.getBackgroundColor(color);
    }

    public int getUserInteger(String message, int min, int max) {

        //Display a message and awaits integer response
        return gui.getUserInteger(message, min, max);
    }

    public void movePlayer(Player player, int theFieldIndex) {

        gui.movePlayer(player, theFieldIndex);
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
    
    public void showDice (int die1Value, int die2Value) {
        gui.setDice(die1Value,die2Value);
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
