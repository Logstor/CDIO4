package model.board;

import controller.GuiController;
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

    private void propertyFieldAction (Player player, GuiController guiController){

    }


    private void startField (Player player, GuiController guiController){

        if(player.getPosition()==0){
            guiController.updateBalance(player, +4000);
        }

    }

}
