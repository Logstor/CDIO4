package controller.ChanceCardManageMent;

import controller.ChanceCardManageMent.ChanceCardActions.MoneyBankAction;
import controller.GeneralActionController;
import controller.GuiController;
import javafx.print.PageLayout;
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

    private ChanceCard currentChanceCard;
    // ChanceCardActions
    private MoneyBankAction moneyBankAction;

    private GuiController guiController;
    private GeneralActionController generalActionController;
    private HashMap<String,String> messageMap;
    
    /*
    ----------------------- Constructor -------------------------
     */
    
    public ChanceCardController (ChanceCard currentChanceCard, GeneralActionController generalActionController,
                                 GuiController guiController,HashMap<String,String> messageMap) {
        this.generalActionController = generalActionController;
        this.currentChanceCard = currentChanceCard;
        this.guiController = guiController;
        this.messageMap = messageMap;


        // ActionClasses
        moneyBankAction = new MoneyBankAction(currentChanceCard,guiController,messageMap,generalActionController);
    }
    
    /*
    ------------------------ Properties -------------------------
     */

    // <editor-folder desc="Properties"


    // </editor-folder>
    
    /*
    ---------------------- Public Methods -----------------------
     */

    public void doChanceCardActionFromCardType (Player player) {
        switch (currentChanceCard.getCardType()) {
            case moneyBank:
                moneyBankAction.chanceCardAction(player);
                break;
            case movingAbs:

            default:
                break;

        }
    }


    /*
    ---------------------- Support Methods ----------------------
     */


}
