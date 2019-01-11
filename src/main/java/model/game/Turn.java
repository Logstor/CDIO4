package model.game;

import controller.Controller;
import model.board.Board;
import model.board.Field;
import model.board.FieldActionManager;
import model.cup.Cup;
import model.player.Player;

/**
 * @author Nikolaj Tscharn Wassmann
 * @date 09-01-2019
 */
public class Turn {

    /*
    ------------ Fields ------------------
    */

    private Board playingBoard = new Board();
    private FieldActionManager actionManager = new FieldActionManager();

    private Field turnField;

    private int prePos, turnPos, postPos;

    private int playerBalance, rollValue, boardPosition;

    private String fieldName, fieldActionText;



    /*
    --------- Public Methods ----------
    */
    //Controlleren og vores Chance kort skal også addes!!
    public void turn (Player player, Cup cup){



    }


    /*
    --------- Support Methods ---------
     */

    private void checkPrisonStat () {

    }

    private void setPrison (){

    }

    private void raffle() {

    }

    private void movePlayer () {

    }

    private void fieldAction () {

    }


}
