package controller.ChanceCardManageMent.ChanceCardActions;

import controller.ChanceCardManageMent.ChanceCardAction;
import controller.GeneralActionController;
import controller.GuiController;
import model.chancecard.CardTypeEnum;
import model.chancecard.ChanceCard;
import model.chancecard.cards.MoneyBank;
import model.player.Player;
import view.gui.Gui;

import java.util.HashMap;
import java.util.SplittableRandom;

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
    
    public void chanceCardAction (Player player, ChanceCard currentChanceCard) {
        showAndSetChanceCardOnGUi(currentChanceCard);
        generalActionController.updatePlayerBalanceInclGui(guiController,player,((MoneyBank) currentChanceCard).getMoneyToTransfer());

}
    
    /*
    ---------------------- Support Methods ----------------------
     */


}
