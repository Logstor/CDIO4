package controller;

import model.board.Board;
import model.board.Field;
import model.board.FieldTypeEnum;
import model.board.fields.PropertyField;
import model.player.Player;

import java.util.ArrayList;
import java.util.HashMap;

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

    /**
     * Updates Player and Gui_Players Balance. Adds Field to Player ownedFields.
     * Sets Field and Gui_Fields owner. Sets the Field Border (DOTTED )to Player CarColor and LightGrey.
     * If Field is Boat, Player.NoOfBoatsOwned is updated with 1.
     * If Field is a Brewery, Player.NoOfBreweriesOwned is updated with 1.
     * @param player The Player that buys the Field.
     * @param fieldToBuy The Field that is bought.
     * @param guiController GuiController to manage GUI.
     */
    public void buyField (Player player, Field fieldToBuy, GuiController guiController) {
        updatePlayerBalanceInclGui(guiController,player,-fieldToBuy.getFieldCost());
        player.addFieldToOwnedFields(fieldToBuy);
        fieldToBuy.setFieldOwner(player);
        guiController.setOwner(player,fieldToBuy);
        guiController.setDottedBorderWithPlayerCarColor(player,fieldToBuy);
        if (fieldToBuy.getFieldType()==FieldTypeEnum.Boat) {
            player.updateNoOfBoatsOwned(1);
        }
        else if (fieldToBuy.getFieldType()==FieldTypeEnum.Brewery) {
            player.updateNoOfBreweriesOwned(1);
        }

    }
    
    /**
     * This method handles the event of the player paying rent to an owner of
     * a property field.
     * @param player The Player that landed on the field.
     * @param field The PropertyField the player landed on.
     * @param guiController The GuiController.
     */
    public void payPropertyRent(Player player, PropertyField field, GuiController guiController,
                                HashMap<String,String> messageMap)
    {
    	int rent = rentFromNoOfHouses(field);

    	// Display message to player
        guiController.showMessage(messageMap.get("PropertyFirst").replace("%name", field.getFieldOwner().getName()).
                                    replace("%rent",String.valueOf(rent)));

        // Update the owners balance
        updatePlayerBalanceInclGui(guiController, field.getFieldOwner(), rent);
        
        // Update the payers balance
		updatePlayerBalanceInclGui(guiController, player, -rent);
    }

    /**
     * This method
     * @param player The player who landed on the Field.
     * @param manualRent The rent which shall be paid.
     * @param currentField The Field the player landed on.
     * @param guiController The GuiController.
     * @param messageMap The Map of messages.
     */
    public void payManuelRent(Player player, int manualRent, Field currentField, GuiController guiController,
                              HashMap<String, String> messageMap)
    {

        // Update both players balance
        updatePlayerBalanceInclGui(guiController, player, -manualRent);
        updatePlayerBalanceInclGui(guiController, currentField.getFieldOwner(), manualRent);

        // Show message to player
        guiController.showMessage( messageMap.get("PayRentTo").replace("%rent", String.valueOf(manualRent))
                .replace("%fieldOwner", currentField.getFieldOwner().getName()) );
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
    public int rentFromNoOfHouses (PropertyField currentField) {
        
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
