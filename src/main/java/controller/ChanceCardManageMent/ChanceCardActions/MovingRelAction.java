package controller.ChanceCardManageMent.ChanceCardActions;

import controller.ChanceCardManageMent.ChanceCardAction;
import controller.GeneralActionController;
import controller.GuiController;
import model.board.Board;
import model.chancecard.ChanceCard;
import model.chancecard.cards.MovingRel;
import model.player.Player;

import java.util.HashMap;

/**
 * @author Rasmus Sander Larsen
 * @date 15-01-2019
 */
public class MovingRelAction extends ChanceCardAction {

    /*
    -------------------------- Fields --------------------------
     */

    private Board board;
    private int prePosition = 0;
    private int postPosition = 0;
    
    
    /*
    ----------------------- Constructor -------------------------
     */
    
    public MovingRelAction (GuiController guiController, HashMap<String,String> messageMap,
                            GeneralActionController generalActionController, Board board) {
        super(guiController,messageMap,generalActionController);
        this.board = board;

    }
    
    /*
    ------------------------ Properties -------------------------
     */

    // <editor-folder desc="Properties"


    // </editor-folder>
    
    /*
    ---------------------- Public Methods -----------------------
     */

    /**
     * Show "YourDrawedChanceCard" on Gui and sets "ChanceCardTop" and ChanceCardText at GUI ChanceCardField.
     * Moves the Player back- or forwards depending on the value of relMovement:int in ChanceCard (MovingRel).
     * @param player The Player that the action is done to.
     * @param currentChanceCard The ChanceCard which action is done.
     */
    public void chanceCardAction (Player player, ChanceCard currentChanceCard) {
        showAndSetChanceCardOnGUi(currentChanceCard);
        prePosition = player.getPosition();
        player.updatePosition(((MovingRel) currentChanceCard).getRelMovement());
        postPosition = player.getPosition();

        if (((MovingRel) currentChanceCard).getRelMovement() > 0) // ChanceCardMovement is forward
        {
            generalActionController.movingPlayerForwardGUI(player, board, guiController,
                    prePosition, postPosition, 500);
        }
        else // ChanceCardMovement is backwards
        {
            generalActionController.movingPlayerBackwardGUI(player, board,guiController,
                    prePosition, postPosition, 500);
        }
    }
    
    /*
    ---------------------- Support Methods ----------------------
     */


}
