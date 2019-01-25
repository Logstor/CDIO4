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
public class UnPledgeAction extends ExtraAction {

    /*
    -------------------------- Fields --------------------------
     */

    
    /*
    ----------------------- Constructor -------------------------
     */
    
    public UnPledgeAction (GuiController guiController, HashMap<String,String> messageMap,
                           GeneralActionController generalActionController) {
       super(guiController,messageMap,generalActionController);
       extraActionType = ExtraActionType_Enum.UnPledge;
    }
    
    /*
    ------------------------ Properties -------------------------
     */

    
    /*
    ---------------------- Public Methods -----------------------
     */
    
    public void doExtraAction (Player player) {

        if (guiController.getLeftButtonPressed(messageMap.get("WantToUnPledgeField?")
                ,messageMap.get("Yes"),messageMap.get("No"))){

            unPledge(player);
        }
    }

    public boolean checkIfUnPledgeIsValid (Player player) {
        ArrayList<Field> pledgedOwnedFields = new ArrayList<>();
        for (Field field : player.getOwnedFields()) {
            if (field.isPledged()) {
                pledgedOwnedFields.add(field);
            }
        }
        return pledgedOwnedFields.size() >=1;
    }

    /*
    ---------------------- Support Methods ----------------------
     */

    private void unPledge (Player player) {
        //region Finds the Fields that is possible to UnPledge.
        ArrayList<Field> fieldsToUnPledge = new ArrayList<>();
        for (Field field : player.getOwnedFields()) {
            if (field.isPledged()) {
                fieldsToUnPledge.add(field);
            }
        }
        //endregion

        //Asks what Field the player wishes to UnPledge.
        String nameOnChosenField = guiController.getUserChoiceFields(messageMap.get("WhatFieldToUnPledge?"), fieldsToUnPledge);

        //region Find the selected Field
        Field[] chosenFieldArray = new Field[1];
        for (Field field : fieldsToUnPledge) {
            if (field.getFieldName().equals(nameOnChosenField)){
                chosenFieldArray[0] = field;
            }
        }
        Field selectedField = chosenFieldArray[0];

        //endregion

        //region Finds the UnPledgePrice

        int unPledgePrice = calUnPledgePrice(selectedField);

        //endregion


        //region Present Player with the full description of the UnPledge of the selected Field.

        if (guiController.getLeftButtonPressed(messageMap.get("FullUnPledgeDesc")
                .replace("%name", player.getName())
                .replace("%fieldName", selectedField.getFieldName())
                .replace("%unPledgePrice", String.valueOf(unPledgePrice)),
                messageMap.get("Yes"), messageMap.get("No"))) {

            //Sets Field.isPledged to false and updates Player and Gui_Player with minus unPledgePrice.
            selectedField.setPledged(false);
            generalActionController.updatePlayerBalanceInclGui(guiController,player,-unPledgePrice);
            //Removes PledgedLabel from GUI_Field.
            guiController.setOwner(player,selectedField);
        }

        //region Player denied to UnPledge the Field

        else {
            guiController.showMessage(messageMap.get("DeniedUnPledge")
                    .replace("%name", player.getName())
                    .replace("%fieldName", selectedField.getFieldName()));
        }

        //endregion

    }

    /**
     * Returns the price to UnPledge the Field. Which is half of the Field.Cost + 10% in rent.
     * @param field the Field the price is calculated from.
     * @return return an int. In MATADOR money, rounded with 100, and always rounding down.
     * TODO: SKAL LAVES TIL MATADOR MONEY
     */
    private int calUnPledgePrice (Field field) {

        return (int)((field.getFieldCost()/2*1.1)-((field.getFieldCost()/2*1.1)%100));
    }
}
