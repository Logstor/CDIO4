package controller.extraActionManagment.extraActions;

import controller.GeneralActionController;
import controller.GuiController;
import controller.extraActionManagment.ExtraAction;
import controller.extraActionManagment.ExtraActionType_Enum;
import model.board.Field;
import model.player.Player;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Rasmus Sander Larsen
 * @date 21-01-2019
 */
public class PledgeAction extends ExtraAction {

    /*
    -------------------------- Fields --------------------------
     */


    
    /*
    ----------------------- Constructor -------------------------
     */
    
    public PledgeAction (GuiController guiController, HashMap<String,String> messageMap,
                         GeneralActionController generalActionController) {
        super(guiController,messageMap,generalActionController);
        extraActionType = ExtraActionType_Enum.Pledge;
    }
    
    /*
    ------------------------ Properties -------------------------
     */


    
    /*
    ---------------------- Public Methods -----------------------
     */
    
    public void doExtraAction (Player player) {

        if (guiController.getLeftButtonPressed(messageMap.get("WantToPledgeFields?"),
                messageMap.get("Yes"),messageMap.get("No"))){
            pledgeField(player);
        }

    }

    /**
     * Returns true if Player Owns 1 or more Fields that isn't pledged.
     * @param player The player check is done on.
     * @return Checks if Player is valid to Pledge any of his owned fields.
     */
    public boolean checkIfPledgeIsValid (Player player) {

        ArrayList<Field> unPledgedFields = new ArrayList<>();
        for (Field field : player.getOwnedFields()) {
            if (!field.isPledged()){
                unPledgedFields.add(field);
            }
        }

        return unPledgedFields.size()>=1;
    }
    
    /*
    ---------------------- Support Methods ----------------------
     */

    private void pledgeField (Player player) {

        //region Finds the names for the Fields that the player owns and that isn't already pledged.
        ArrayList<String> nameOnFieldsToPledge = new ArrayList<>();
        for (Field field : player.getOwnedFields()) {
            if (!field.isPledged()){
                nameOnFieldsToPledge.add(field.getFieldName());
            }
        }
        //endregion

        // Asks what field the player wishes to pledge.
        String nameOnChosenField = guiController.getUserChoice(messageMap.get("WhatFieldToPledge?"),nameOnFieldsToPledge);

        //region Finds the Field the Player selected.
        Field[] selectedFieldArray = new Field[1];
        for (Field field : player.getOwnedFields()){
            if (field.getFieldName().equals(nameOnChosenField)){
                selectedFieldArray[0] = field;
            }
        }
        Field selectedField = selectedFieldArray[0];
        //endregion

        //region Asks if Player wants to go forward with the pledge of the Field.
        if (guiController.getLeftButtonPressed(messageMap.get("FullPledgeDesc")
                .replace("%name", player.getName())
                .replace("%fieldName", selectedField.getFieldName())
                .replace("%pledgePrice", String.valueOf((selectedField.getFieldCost()/2))),
                messageMap.get("Yes"), messageMap.get("No"))){
            //Gives the player the pledgePrice and updates balance, for GUI_Player and Player.
            generalActionController.updatePlayerBalanceInclGui(guiController,player,(selectedField.getFieldCost()/2));
            //Sets Fields as Pledged
            selectedField.setPledged(true);
            //Shows on the GUI that the Field is Pledged.
            addPledgedLabelToOwnerNameOnGUI(player,selectedField);
        }
        //endregion

        //region Player denied the pledge of the field.
        else {
            guiController.showMessage(messageMap.get("DeniedPledge")
                    .replace("%name", player.getName())
                    .replace("%fieldName", selectedField.getFieldName()));
        }
        //endregion
    }

    private void addPledgedLabelToOwnerNameOnGUI (Player player, Field currentField) {
        String originalPlayerName = player.getName();
        String playerNameWithPledgeLabel = player.getName() + messageMap.get("PledgedLabel");
        player.setName(playerNameWithPledgeLabel);
        guiController.setOwner(player,currentField);
        player.setName(originalPlayerName);
    }
}
