package controller.ChanceCardManageMent;

import controller.GeneralActionController;
import controller.GuiController;
import model.chancecard.ChanceCard;
import model.player.Player;

import java.util.HashMap;

/**
 * @author Rasmus Sander Larsen
 * @date 15-01-2019
 */
public abstract class ChanceCardAction {

    /*
    -------------------------- Fields --------------------------
     */

    protected ChanceCard chanceCard;
    protected GuiController guiController;
    protected HashMap<String,String> messageMap;
    protected GeneralActionController generalActionController;
    
    /*
    ----------------------- Constructor -------------------------
     */
    
    public ChanceCardAction (ChanceCard chanceCard, GuiController guiController,
                             HashMap<String,String> messageMap, GeneralActionController generalActionController) {
        this.chanceCard = chanceCard;
        this.guiController = guiController;
        this.messageMap = messageMap;
        this.generalActionController = generalActionController;
    }
    
    /*
    ------------------------ Properties -------------------------
     */

    // <editor-folder desc="Properties"


    // </editor-folder>
    
    /*
    ---------------------- Public Methods -----------------------
     */
    
    protected abstract void chanceCardAction (Player player);

    public void showAndSetChanceCardOnGUi () {
        StringBuilder chanceCardToDisplayBuilder = new StringBuilder();
        chanceCardToDisplayBuilder.append(messageMap.get("ChanceCardTop") + "\n");
        chanceCardToDisplayBuilder.append(chanceCard.getCardText());

        guiController.setCCard(chanceCardToDisplayBuilder.toString());
        guiController.showMessage(messageMap.get("YouDrawedChanceCard"));
    }
    
    /*
    ---------------------- Support Methods ----------------------
     */


}
