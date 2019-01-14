package model.board;

import controller.GuiController;
import model.board.fields.PropertyField;
import model.cup.Cup;
import model.player.Account;
import model.player.Player;

import java.lang.management.PlatformLoggingMXBean;
import java.util.HashMap;

/**
 * @author Nikolaj Tscharn Wassmann
 * @date 09-01-2019
 */

public class FieldActionManager {

    /*
    ------------ Fields ------------------
    */



    /*
    --------- Public Methods ----------
    */



    /*
    --------- Support Methods ---------
     */

    private void taxFieldAction(Player player, Account account, GuiController guiController){

        guiController.showMessage(player + "Du er landet på et skattefelt");
        guiController.showMessage("Du skal betale 4000 i skat");
        if(account.getBalance()>4000) {
            guiController.updateBalance(player, -4000);
        }
        else if(account.getBalance()<4000){

        }
    }

    private void boatFieldAction(Player player, int position){

    }

    private void breweryFieldAction(Player player, Field currentField, Cup cup, GuiController guiController){
        if (currentField.getFieldOwner() == null) {
            buyField(player,currentField,guiController);
        } else{

        }

    }

    private void chanceFieldAction (Player player, int position) {

    }

    private void parkingFieldAction (Player player, GuiController guiController){

        guiController.showMessage(player + "Du er landet på Gratis Parkering");
        guiController.showMessage("Her må du parkere gratis");
    }

    public void prisonFieldAction (Player player, GuiController guiController){

        if(player.isInPrison()){
            guiController.getUserButton1("Du sidder i fængsel og skal betale 1000" + player.getName(), "Betal 1000");
            guiController.updateBalance(player,-1000);
            player.setInPrison(false);
        }

    }

    private void propertyFieldAction (Player player, int position){

    }


    private void startField (Player player, GuiController guiController){

        //Hvis vi antager at startfeltet er 0 i Field Arrayet
        if(player.getPosition()==0){
            guiController.updateBalance(player, +4000);
        }

    }

    private void buyField (Player player, Field currentField, GuiController guiController) {

        currentField.setFieldOwner(player);
        player.updateBalance(-currentField.fieldCost);
        guiController.setOwner(player,currentField);

    }

    private void payManuelRent (Player player, int manuelRent, Field currentField, GuiController guiController,
                                HashMap<String,String> messageMap) {

        player.updateBalance(-manuelRent);
        currentField.getFieldOwner().updateBalance(manuelRent);
        guiController.showMessage(messageMap.get("PayRentTo") + " " +currentField.getFieldOwner().getName() + ".\n" +
                messageMap.get("RentIs")+ " " + manuelRent);
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

        switch (currentField.getNoOfHouseOnPropery()) {
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
