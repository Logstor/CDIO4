package model.board;

import controller.GuiController;
import model.board.fields.PropertyField;
import model.cup.Cup;
import model.chancecard.Deck;
import model.player.Account;
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
            case "Property":
                propertyFieldAction(player, (PropertyField)field, guiController, messageMap);
                break;
            case "Start":
                break;
            case "ChanceCard":
                break;
            case "Tax":
                break;
            case "Prison":
                break;
            case "Boat":
                break;
            case "Parking":
                break;
            case "Brewery":
                break;
        }

        //endregion
    }

    /*
    --------- Support Methods ---------
     */

    private void taxFieldAction(Player player, Account account, GuiController guiController){

        if(player.getPosition()==5 || player.getPosition()==39) {
            guiController.showMessage(player + "Du er landet på et skattefelt");
            guiController.showMessage("Du skal betale 4000 i skat");
            if (account.getBalance() > 4000) {
                guiController.updateBalance(player, field.getFieldCost());
            } else if (account.getBalance() < 4000) {
                player.setHasLost(true);
            }
        }
    }

    /**
     * This method lets you purchase a boatField, and pays the rent if the field is already owned by a player.
     * The rent is transfered to the player, that owns the field
     * @param player Player
     * @param guiController GuiController
     * @param messageMap Hashmap
     * @param field Field
     */
    private void boatFieldAction(Player player, GuiController guiController, HashMap <String, String> messageMap, Field field){

        //region Check if the boatField has a owner, if false:
        if (field.getFieldOwner() == null) {
            guiController.showMessage(messageMap.get("BoatMessage"));
            guiController.getUserButton2("Vil du købe dette felt for: " + field.getFieldCost(), "Ja", "Nej");

            // If the player wants to purchase a boatField
            if (true) {
                player.updateBalance(field.getFieldCost());
                player.setBoatsOwned(+1);
            }
        }
        //endregion

        //region if the there is a owner to the boat field:
        if (field.getFieldOwner() != null) {
            // runs the different cases if the player owns 1,2,3, or 4 boats:
            switch (player.getBoatsOwned()) {
                case 1:
                    player.updateBalance(-500);
                    guiController.showMessage(field.getFieldOwner() + messageMap.get("BoatsOwned1"));
                    field.getFieldOwner().updateBalance(+500);
                    break;

                case 2:
                    player.updateBalance(-1000);
                    guiController.showMessage(messageMap.get("BoatsOwned2"));
                    field.getFieldOwner().updateBalance(+1000);
                    break;

                case 3:
                    player.updateBalance(-2000);
                    guiController.showMessage(messageMap.get("BoatsOwned3"));
                    field.getFieldOwner().updateBalance(+2000);
                    break;

                case 4:
                    player.updateBalance(-4000);
                    guiController.showMessage(messageMap.get("BoatsOwned4"));
                    field.getFieldOwner().updateBalance(+4000);
                    break;
            }
        }
        //endregion
    }

    private void breweryFieldAction(Player player, GuiController guiController){

    }

    private void breweryFieldAction(Player player, Field currentField, Cup cup, GuiController guiController,
                                    HashMap<String,String> messageMap) {
        if (currentField.getFieldOwner() == null) {
            buyField(player, currentField, guiController);
        } else {
            int rentFromCupValue = rentFromCupValue(player, cup);
            payManuelRent(player, rentFromCupValue, currentField, guiController, messageMap);
        }
    }

    /**
     *
     * @param player Player
     * @param guiController GuiController
     * @param deck Deck
     */
    private void chanceFieldAction (Player player, GuiController guiController, Deck deck) {

        guiController.displayCCard();

    }

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

    private int rentFromCupValue (Player player, Cup cup) {
        int rentFromCupValue;
        switch (player.getBreweriesOwned()) {
            case 1:
                rentFromCupValue = 100*cup.getCupValue();
                break;
            case 2:
                rentFromCupValue = 200*cup.getCupValue();
                break;
            default:
                rentFromCupValue = 0;
                break;
        }
        return rentFromCupValue;
    }
}
