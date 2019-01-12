package model.game;

import controller.GuiController;
import controller.MainControl;
import model.board.Board;
import model.board.Field;
import model.board.FieldActionManager;
import model.chancecard.Deck;
import model.cup.Cup;
import model.cup.Die;
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
    public void turn (Player player, Cup cup, GuiController controller, Deck deck){




    }


    /*
    --------- Support Methods ---------
     */

    private void checkPrisonStat () {

    }

    private void setPrison (){

    }

    private void raffle(Player player, Cup cup, GuiController controller) {

        if(!player.isHasLost()){

            controller.showMessage("Tryk for at slå med terningerne");

            cup.cupRoll();
            rollValue= cup.getCupValue();
        }

    }

    private void movePlayer () {

    }

    private void fieldAction () {

    }


}
