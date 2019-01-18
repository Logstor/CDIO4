package controller.chanceCardManagement.chanceCardActions;

import controller.GeneralActionController;
import controller.GuiController;
import controller.chanceCardManagement.ChanceCardAction;
import controller.fieldManagement.fieldActions.ChanceAction;
import model.chancecard.ChanceCard;
import model.player.Player;

import java.util.HashMap;

/**
 * @author Rasmus Sander Larsen
 * @date 18-01-2019
 */
public class OutOfPrisonAction extends ChanceCardAction {

    /*
    -------------------------- Fields --------------------------
     */
    
    
    
    /*
    ----------------------- Constructor -------------------------
     */

    public OutOfPrisonAction ( GuiController guiController, HashMap<String,String> messageMap,
    GeneralActionController generalActionController) {
        super(guiController, messageMap, generalActionController);

    }

    
    /*
    ------------------------ Properties -------------------------
     */

    // <editor-folder desc="Properties"


    // </editor-folder>
    
    /*
    ---------------------- Public Methods -----------------------
     */

    public void chanceCardAction (Player player, ChanceCard currentCard) {
        // Set, Display and Message ChanceCard´text.
        setDisplayMessageChanceCardOnGUi(currentCard);

        //Updates Player PrisonCard.
        player.setPrisonCard(player.getPrisonCard() + 1);

    }
    
    /*
    ---------------------- Support Methods ----------------------
     */


}
