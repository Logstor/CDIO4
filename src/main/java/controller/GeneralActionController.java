package controller;

import model.board.Board;
import model.board.Field;
import model.player.Player;
import view.gui.Gui;

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

    public void movingPlayerForwardGUI(Player player, Board board, GuiController guiController,
                                        int prePosition, int finalPosition, int milliSecondsPrTokenMovement) {
        if (prePosition>finalPosition) {
            for (int i = prePosition+1; i<board.getBoard().length; i++){
                try {
                    Thread.sleep(milliSecondsPrTokenMovement);
                    guiController.movePlayer(player, i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i<=finalPosition; i++) {
                try {
                    Thread.sleep(milliSecondsPrTokenMovement);
                    guiController.movePlayer(player, i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {
            for (int i = prePosition + 1; i <= finalPosition; i++) {
                try {
                    Thread.sleep(milliSecondsPrTokenMovement);
                    guiController.movePlayer(player, i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
    
    /*
    ---------------------- Support Methods ----------------------
     */


}
