package controller;

import model.board.Board;
import model.board.Field;
import model.board.fields.PropertyField;
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
    
    /**
     * This method handles the event of the player paying rent to an owner of
     * a property field.
     * @param player The Player that landed on the field.
     * @param field The PropertyField the player landed on.
     * @param guiController The GuiController.
     */
    public void payPropertyRent(Player player, PropertyField field, GuiController guiController)
    {
    	int rent = rentFromNoOfHouses(field);
    	
        // Update the owners balance
        updatePlayerBalanceInclGui(guiController, field.getFieldOwner(), rent);
        
        // Update the payers balance
		updatePlayerBalanceInclGui(guiController, player, -rent);
    }
    
    public void payManuelRent(Player player, Field field, GuiController guiController)
    {
    
    }

    public void movingPlayerForwardGUI(Player player, Board board, GuiController guiController,
                                        int prePosition, int finalPosition, int milliSecondsPrTokenMovement)
    {
        if (prePosition>finalPosition)
        {
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
        }
        
        else {
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

    public void movingPlayerBackwardGUI(Player player, Board board, GuiController guiController,
                                         int prePosition, int finalPosition, int milliSecondsPrTokenMovement) {

        if (prePosition<finalPosition && ((finalPosition-prePosition)+finalPosition>=24)) {

            for (int i = prePosition - 1; i >= 0; i--) {
                try {
                    Thread.sleep(milliSecondsPrTokenMovement);
                    guiController.movePlayer(player, i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for (int i = board.getBoard().length - 1; i >= finalPosition; i--) {
                try {
                    Thread.sleep(milliSecondsPrTokenMovement);
                    guiController.movePlayer(player, i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        } else {

            for (int i = prePosition - 1; i >= finalPosition; i--) {
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
    
    /**
     * This method takes the amount of houses build on the PropertyField into
     * consideration, when it returns the rent.
     * @param currentField The PropertyField.
     * @return The current rent as an int.
     */
    private int rentFromNoOfHouses (PropertyField currentField) {
        
        int rentFromNoOfHouses;
        
        switch (currentField.getNoOfHousesOnProperty()) {
            case 0:
                rentFromNoOfHouses = currentField.getFieldRent();
                break;
            case 1:
                rentFromNoOfHouses = currentField.getField1HouseRent();
                break;
            case 2:
                rentFromNoOfHouses = currentField.getField2HouseRent();
                break;
            case 3:
                rentFromNoOfHouses = currentField.getField3HouseRent();
                break;
            case 4:
                rentFromNoOfHouses = currentField.getField4HouseRent();
                break;
            case 5:
                rentFromNoOfHouses = currentField.getField5HouseRent();
                break;
            default:
                rentFromNoOfHouses = 0;
                break;
            
        }
        return rentFromNoOfHouses;
    }


}
