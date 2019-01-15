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
    
    public MoneyBankAction (ChanceCard chanceCard, GuiController guiController, HashMap<String,String> messageMap,
                            GeneralActionController generalActionController) {
        super(chanceCard,guiController,messageMap, generalActionController);
    }
    
    /*
    ------------------------ Properties -------------------------
     */

    // <editor-folder desc="Properties"


    // </editor-folder>
    
    /*
    ---------------------- Public Methods -----------------------
     */
    
    public void chanceCardAction (Player player) {
        showAndSetChanceCardOnGUi();
        generalActionController.updatePlayerBalanceInclGui(guiController,player,((MoneyBank)chanceCard).getMoneyToTransfer());

}
    
    /*
    ---------------------- Support Methods ----------------------
     */


}
