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

    protected GuiController guiController;
    protected HashMap<String,String> messageMap;
    protected GeneralActionController generalActionController;
    
    /*
    ----------------------- Constructor -------------------------
     */
    
    public ChanceCardAction (GuiController guiController, HashMap<String,String> messageMap,
                             GeneralActionController generalActionController) {
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
    
    protected abstract void chanceCardAction (Player player, ChanceCard currentChanceCard);

    /**
     * Show "YourDrawedChanceCard" on Gui and sets "ChanceCardTop" and ChanceCardText at GUI ChanceCardField.
     * @param currentChanceCard This is the ChanceCard that is showed.
     */
    public void showAndSetChanceCardOnGUi (ChanceCard currentChanceCard) {
        StringBuilder chanceCardToDisplayBuilder = new StringBuilder();
        chanceCardToDisplayBuilder.append(messageMap.get("ChanceCardTop") + "\n");
        chanceCardToDisplayBuilder.append(currentChanceCard.getCardText());

        guiController.setCCard(chanceCardToDisplayBuilder.toString());
        guiController.showMessage(messageMap.get("YouDrawedChanceCard"));
    }
    
    /*
    ---------------------- Support Methods ----------------------
     */


}
