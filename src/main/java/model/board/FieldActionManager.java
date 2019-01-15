package model.board;

import controller.GuiController;
import model.board.fields.PropertyField;
import model.player.Player;

import java.util.HashMap;

/**
 * @author Nikolaj Tscharn Wassmann
 * @date 09-01-2019
 */

public class FieldActionManager {

    /*
    ------------ Fields ------------------
    */

    private Field field;





    /*
    --------- Public Methods ----------
    */

    public void fieldAction(Player player, Field field, GuiController guiController,
                            HashMap<String, String> messageMap)
    {
        //region Find Field Type

        switch(field.getFieldType())
        {
            case Property:
                propertyFieldAction(player, (PropertyField)field, guiController, messageMap);
                break;
            case Start:
                break;
            case ChanceCard:
                break;
            case Tax:
                break;
            case Prison:
                break;
            case Boat:
                break;
            case Parking:
                break;
            case Brewery:
                break;
        }

        //endregion
    }

    /*
    --------- Support Methods ---------
     */

    private void parkingFieldAction (Player player, GuiController guiController) {
        if (player.getPosition()==21) {
            guiController.showMessage(player + "Du er landet på Gratis Parkering");
            guiController.showMessage("Her må du parkere gratis");
        }
    }

    public void prisonFieldAction (Player player, GuiController guiController){
        if(field.getFieldNo()==11) {
            if (player.getPrisonStat() > 0) {
                guiController.getUserButton1("Du sidder i fængsel og skal betale 1000" + player.getName(), "Betal 1000");
                guiController.updateBalance(player, -1000);
                player.setPrisonStat(0);
            } else {
                guiController.showMessage("Du er på besøg i fængslet");
            }
        }
    }

    /**
     *
     * @param player
     * @param property
     * @param guiController
     * @param messageMap
     */
    private void propertyFieldAction (Player player, PropertyField property, GuiController guiController,
                                      HashMap<String, String> messageMap)
    {
        //region Buying Sequence
        if (property.getFieldOwner() == null)
        {
            buyField(player, property, guiController);
        }
        //endregion

        //region Pay Rent
        else
        {
            guiController.showMessage(messageMap.get("PropertyFirst").replace("%name", property.getFieldOwner().getName()));

            // Update both players balance
            property.getFieldOwner().updateBalance(property.getFieldRent());
            guiController.updateBalance(property.getFieldOwner(), property.getFieldOwner().getAccount().getBalance());

            player.updateBalance(-property.getFieldRent());
            guiController.updateBalance(player, player.getAccount().getBalance());
        }
        //endregion
    }


    private void startField (Player player, GuiController guiController){

        if(player.getPosition()==0){
            guiController.updateBalance(player, +4000);
        }

    }
    private void buyField (Player player, Field currentField, GuiController guiController) {

        currentField.setFieldOwner(player);
        player.updateBalance(-currentField.fieldCost);
        guiController.setOwner(player,currentField);

    }

    private void payPropertyRent (Player player, PropertyField currentField, GuiController guiController,
                          HashMap<String,String> messageMap) {

        int rentFromNoOfHouses = rentFromNoOfHouses(currentField);

        player.updateBalance(-rentFromNoOfHouses);
        currentField.getFieldOwner().updateBalance(rentFromNoOfHouses);
        guiController.showMessage(messageMap.get("PayRentTo") + " " +currentField.getFieldOwner().getName() + ".\n" +
                messageMap.get("RentIs") + rentFromNoOfHouses);
    }

    /**
     * Finds the right rent from the number of houses on the PropertyField.
     * @param currentField PropertyField:
     * @return Int: Correct rent.
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
