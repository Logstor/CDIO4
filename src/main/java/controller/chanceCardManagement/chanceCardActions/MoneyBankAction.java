package controller.chanceCardManagement.chanceCardActions;

import controller.chanceCardManagement.ChanceCardAction;
import controller.GeneralActionController;
import controller.GuiController;
import model.chancecard.ChanceCard;
import model.chancecard.cards.MoneyBank;
import model.player.Player;

import java.util.HashMap;

/**
 * @author Rasmus Sander Larsen
 * @date 15-01-2019
 */
public class MoneyBankAction extends ChanceCardAction {

    /*
    -------------------------- Fields --------------------------
     */

    
    /*
    ----------------------- Constructor -------------------------
     */
    
    public MoneyBankAction (GuiController guiController, HashMap<String,String> messageMap,
                            GeneralActionController generalActionController) {
        super(guiController,messageMap, generalActionController);
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
     * Gives or Takes the amount specified in moneyToTransfer:int from ChanceCard (MoneyBank).
     * @param player The Player that the action is done to.
     * @param currentChanceCard The ChanceCard which action is done.
     */
    public void chanceCardAction (Player player, ChanceCard currentChanceCard) {
        setDisplayMessageChanceCardOnGUi(currentChanceCard);
        generalActionController.updatePlayerBalanceInclGui(guiController,player,((MoneyBank) currentChanceCard).getMoneyToTransfer());

}
    
    /*
    ---------------------- Support Methods ----------------------
     */


}
