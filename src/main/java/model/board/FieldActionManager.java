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

    private void breweryFieldAction(Player player, int position){

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

}
