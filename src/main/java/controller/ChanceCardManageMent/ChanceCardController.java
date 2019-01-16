package controller.ChanceCardManageMent;

import controller.ChanceCardManageMent.ChanceCardActions.*;
import controller.GeneralActionController;
import controller.GuiController;
import model.board.Board;
import model.chancecard.ChanceCard;
import model.player.Player;

import java.util.HashMap;

/**
 * @author Rasmus Sander Larsen
 * @date 15-01-2019
 */
public class ChanceCardController {

    /*
    -------------------------- Fields --------------------------
     */

    private GuiController guiController;
    private GeneralActionController generalActionController;
    private HashMap<String,String> messageMap;
    private Board board;
    
    /*
    ----------------------- Constructor -------------------------
     */
    
    public ChanceCardController (GeneralActionController generalActionController, Board board,
                                 GuiController guiController, HashMap<String,String> messageMap) {
        this.generalActionController = generalActionController;
        this.guiController = guiController;
        this.messageMap = messageMap;
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
     * Runs the chanceCardAction matching the cardType of inputted ChanceCard.
     * @param player This is the player that does the CardAction.
     * @param currentChanceCard This is the ChanceCard the Action is done for.
     */
    public void doChanceCardActionFromCardType (Player player, ChanceCard currentChanceCard) {
        switch (currentChanceCard.getCardType()) {
            case moneyBank:
                MoneyBankAction moneyBankAction = new MoneyBankAction(guiController,messageMap,generalActionController);
                moneyBankAction.chanceCardAction(player,currentChanceCard);
                break;
            case movingAbs:
                MovingAbsAction movingAbsAction = new MovingAbsAction(guiController,messageMap,generalActionController,board);
                movingAbsAction.chanceCardAction(player,currentChanceCard);
                break;
            case movingRel:
                MovingRelAction movingRelAction = new MovingRelAction(guiController, messageMap,generalActionController,board);
                movingRelAction.chanceCardAction(player, currentChanceCard);
                break;
            case matadorLegat:
                MatadorLegatAction matadorLegatAction = new MatadorLegatAction(guiController,messageMap,generalActionController);
                matadorLegatAction.chanceCardAction(player,currentChanceCard);
                break;
            case toPrison:
                ToPrisonAction toPrisonAction = new ToPrisonAction(guiController,messageMap,generalActionController,board);
                toPrisonAction.chanceCardAction(player,currentChanceCard);
                break;
            default:
                break;

        }
    }

    /*
    ---------------------- Support Methods ----------------------
     */
}
