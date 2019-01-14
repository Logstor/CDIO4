package model.board;

import controller.GuiController;
import model.player.Account;
import model.player.Player;

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

    private void boatFieldAction(Player player, GuiController guiController){



    }

    private void breweryFieldAction(Player player, GuiController guiController){

    }

    private void chanceFieldAction (Player player, GuiController guiController) {

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
