package controller.ChanceCardManageMent;

import controller.ChanceCardManageMent.ChanceCardActions.MoneyBankAction;
import controller.ChanceCardManageMent.ChanceCardActions.MovingRelAction;
import controller.GeneralActionController;
import controller.GuiController;
import javafx.print.PageLayout;
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

    // ChanceCardActions
    private MoneyBankAction moneyBankAction;
    private MovingRelAction movingRelAction;

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


        // ActionClasses
        moneyBankAction = new MoneyBankAction(guiController,messageMap,generalActionController);
        movingRelAction = new MovingRelAction(guiController, messageMap,generalActionController,board);
    }
    
    /*
    ------------------------ Properties -------------------------
     */

    // <editor-folder desc="Properties"


    // </editor-folder>
    
    /*
    ---------------------- Public Methods -----------------------
     */

    public void doChanceCardActionFromCardType (Player player, ChanceCard currentChanceCard) {
        switch (currentChanceCard.getCardType()) {
            case moneyBank:
                moneyBankAction.chanceCardAction(player,currentChanceCard);
                break;
            case movingAbs:

                break;
            case movingRel:
                movingRelAction.chanceCardAction(player, currentChanceCard);
                break;
            default:
                break;

        }
    }


    /*
    ---------------------- Support Methods ----------------------
     */


}
