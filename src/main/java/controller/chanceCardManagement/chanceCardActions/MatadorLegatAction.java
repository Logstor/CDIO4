package controller.ChanceCardManageMent.ChanceCardActions;

import controller.ChanceCardManageMent.ChanceCardAction;
import controller.GeneralActionController;
import controller.GuiController;
import model.chancecard.ChanceCard;
import model.chancecard.cards.MatadorLegat;
import model.player.Player;

import java.util.HashMap;

/**
 * @author Rasmus Sander Larsen
 * @date 16-01-2019
 */
public class MatadorLegatAction extends ChanceCardAction {

    /*
    -------------------------- Fields --------------------------
     */
    
    private final int LEGATLIMIT = 15000;
    private final int LEGATVALUE = 40000;
    
    /*
    ----------------------- Constructor -------------------------
     */
    
    public MatadorLegatAction (GuiController guiController, HashMap<String,String> messageMap,
                         GeneralActionController generalActionController) {
        super(guiController,messageMap, generalActionController);
    }
    
    /*
    ------------------------ Properties -------------------------
     */

    // <editor-folder desc="Properties"


    // </editor-folder>
    
    /*
    ---------------------- Public Methods -----------------------
     */

    /**
     * Gets totalPlayerValue and if it is under LEGATLIMIT (15.000) the player is grated the LEGATVALUE (40.000)
     * @param player The Player that the action is done to.
     * @param currentChanceCard The ChanceCard which action is done.
     */
    public void chanceCardAction (Player player, ChanceCard currentChanceCard) {
        int totalPlayerValue = player.calPlayerTotalValue();
        setDisplayMessageChanceCardOnGUi(currentChanceCard);

        if (totalPlayerValue <= LEGATLIMIT) {
            guiController.showMessage(messageMap.get("MatadorLegatGranted").
                    replace("%playerValue", String.valueOf(totalPlayerValue)));
            generalActionController.updatePlayerBalanceInclGui(guiController,player,LEGATVALUE);
        } else {
            guiController.showMessage(messageMap.get("MatadorLegatNOTGranted").
                    replace("%playerValue", String.valueOf(totalPlayerValue)));
        }

    }
    
    /*
    ---------------------- Support Methods ----------------------
     */


}
