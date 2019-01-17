package controller.chanceCardManagement.chanceCardActions;

import controller.chanceCardManagement.ChanceCardAction;
import controller.GeneralActionController;
import controller.GuiController;
import model.board.Board;
import model.chancecard.ChanceCard;
import model.player.Player;

import java.util.HashMap;

/**
 * @author Rasmus Sander Larsen
 * @date 16-01-2019
 */
public class ToPrisonAction extends ChanceCardAction {

    /*
    -------------------------- Fields --------------------------
     */

    private Board board;
    
    /*
    ----------------------- Constructor -------------------------
     */
    
    public ToPrisonAction(GuiController guiController, HashMap<String,String> messageMap,
                          GeneralActionController generalActionController, Board board) {
        super(guiController, messageMap, generalActionController);
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
     * Player position is updated to so it matches FieldNo 11 (PositionIndex = 10).
     * GUI_Player is moved to FieldNo 11 and Player.prisonStats is set to 1.
     * @param player The Player that the action is done to.
     * @param currentChanceCard The ChanceCard which action is done.
     */
    public void chanceCardAction (Player player, ChanceCard currentChanceCard) {
        setDisplayMessageChanceCardOnGUi(currentChanceCard);
        player.setPrisonStat(1);

        int currentPosition, movesToUpdatePositionWith, prisonFieldIndex = 10;

        currentPosition = player.getPosition();
        if (currentPosition>prisonFieldIndex) {
            movesToUpdatePositionWith = -1*(currentPosition-prisonFieldIndex);
        } else {
            movesToUpdatePositionWith = prisonFieldIndex - currentPosition;
        }

        // Player position is updated with "movesToUpDatePositionWith" which is calculated from players CurrentPosition.
        player.updatePosition(movesToUpdatePositionWith);
        guiController.movePlayer(player, player.getPosition());
    }
    
    /*
    ---------------------- Support Methods ----------------------
     */


}
