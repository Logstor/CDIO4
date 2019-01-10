package controller;

import model.board.Field;
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

    public Controller (Field[] fields){

        //Creates the Graphic User Interface using the Field array
        gui = new Gui(fields);
    }


    /*
    --------- Public Methods ----------
    */

    public void setOwner (Player player, Field field){

        gui.setFieldOwner(player, field);
    }

    public void addPlayer (Player player){

        gui.addPlayer(player);

    }

    public void displayCCard () {

        gui.displayChanceCard();
    }

    public void getUserButtonPressed (String string, String button1, String button2){

        //Display a message to the user and awaits response
        gui.getUserChoice(string, button1, button2);
    }


    public void setTextColor (Color Tcolor){

        gui.setTextColor(Tcolor);

    }

    public void setBGColor (Color BGcolor){

        gui.setBackgroundColor(BGcolor);

    }















}
