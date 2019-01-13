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

import javax.naming.ldap.Control;

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
        //Raffles the cup, but firstly it checks if player has lost

        if(!player.isHasLost()){

            controller.showMessage("Tryk for at slå med terningerne");

            cup.cupRoll();
            rollValue= cup.getCupValue();
        }

    }

    private void rafflePrint(Player player, GuiController controller){
        //Check if player.isHasLost = true, if so the player has lost and the method wont run

        if(!player.isHasLost()){


            // Sets the current position before they roll dice
            int position = player.getPosition();

            //GUI prints the message
            controller.showMessage("Du slog" + rollValue);

            //Updates the GUI position with faceValue of the dices
            player.updatePosition(rollValue);

            //Assigns the new position to the integer turnPos
            turnPos = player.getPosition();

            //Moves the player
            movePlayer(player, controller, position, turnPos);



        }

    }

    private void movePlayer (Player player, GuiController controller, int position, int newPositon) {

    }

    private void fieldAction () {

    }


}