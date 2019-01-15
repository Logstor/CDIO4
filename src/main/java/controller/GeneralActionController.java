package controller;

import model.board.Field;
import model.player.Player;

/**
 * @author Rasmus Sander Larsen
 * @date 15-01-2019
 */
public class GeneralActionController {

    /*
    -------------------------- Fields --------------------------
     */
    
    
    
    /*
    ----------------------- Constructor -------------------------
     */
    
    public GeneralActionController () {

    }
    
    /*
    ------------------------ Properties -------------------------
     */

    // <editor-folder desc="Properties"


    // </editor-folder>
    
    /*
    ---------------------- Public Methods -----------------------
     */

    public void updatePlayerBalanceInclGui(GuiController guiController, Player player, int updateBalanceWithThisAmount) {
        player.updateBalance(updateBalanceWithThisAmount);
        guiController.updateBalance(player, player.getAccount().getBalance());
    }

    public void buyField (Player player, Field fieldToBuy, GuiController guiController) {
        updatePlayerBalanceInclGui(guiController,player,-fieldToBuy.getFieldCost());
        player.addFieldToOwnedFields(fieldToBuy);
        guiController.setOwner(player,fieldToBuy);
    }
    
    /*
    ---------------------- Support Methods ----------------------
     */


}
