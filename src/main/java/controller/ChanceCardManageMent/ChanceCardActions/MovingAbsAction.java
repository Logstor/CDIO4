package controller.ChanceCardManageMent.ChanceCardActions;

import controller.ChanceCardManageMent.ChanceCardAction;
import controller.GeneralActionController;
import controller.GuiController;
import model.board.Board;
import model.chancecard.ChanceCard;
import model.chancecard.cards.MovingAbs;
import model.player.Player;

import java.util.HashMap;

/**
 * @author Rasmus Sander Larsen
 * @date 15-01-2019
 */
public class MovingAbsAction extends ChanceCardAction {

    /*
    -------------------------- Fields --------------------------
     */

    private Board board;
    
    /*
    ----------------------- Constructor -------------------------
     */
    
    public MovingAbsAction (GuiController guiController, HashMap<String,String> messageMap,
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
     *
     * @param player The Player that the action is done to.
     * @param currentCard ChanceCard passed into MovingAbs
     */
    public void chanceCardAction (Player player, ChanceCard currentCard) {
        // Set, Display and ShowMessage ChanceCard
        setDisplayMessageChanceCardOnGUi(currentCard);

        // Local Variable
        int prePosition, finalPosition, finalPositionUpdate;

        prePosition = player.getPosition();
        finalPosition = ((MovingAbs) currentCard).getAbsFieldNo();
        if (prePosition>finalPosition) {
            int diffPassed = 39-prePosition;
            finalPositionUpdate = finalPosition + diffPassed + 1; // +1 for 0 in index
        } else {
            finalPositionUpdate = (finalPosition-1)-prePosition;
        }

        // Do actions.
        player.updatePosition(finalPositionUpdate);
        generalActionController.movingPlayerForwardGUI(player,board,guiController,prePosition,finalPosition,250);
    }
    
    /*
    ---------------------- Support Methods ----------------------
     */


}
