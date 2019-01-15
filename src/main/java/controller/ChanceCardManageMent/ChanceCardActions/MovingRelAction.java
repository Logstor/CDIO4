package controller.ChanceCardManageMent.ChanceCardActions;

import controller.ChanceCardManageMent.ChanceCardAction;
import controller.GeneralActionController;
import controller.GuiController;
import javafx.print.PageLayout;
import model.chancecard.ChanceCard;
import model.chancecard.cards.MovingRel;
import model.player.Player;

import java.util.HashMap;

/**
 * @author Rasmus Sander Larsen
 * @date 15-01-2019
 */
public class MovingRelAction extends ChanceCardAction {

    /*
    -------------------------- Fields --------------------------
     */
    
    
    
    /*
    ----------------------- Constructor -------------------------
     */
    
    public MovingRelAction (GuiController guiController, HashMap<String,String> messageMap,
                            GeneralActionController generalActionController) {
        super(guiController,messageMap,generalActionController);
    }
    
    /*
    ------------------------ Properties -------------------------
     */

    // <editor-folder desc="Properties"


    // </editor-folder>
    
    /*
    ---------------------- Public Methods -----------------------
     */
    
    public void chanceCardAction (Player player, ChanceCard currentChanceCard) {
        showAndSetChanceCardOnGUi(currentChanceCard);
        player.updatePosition(((MovingRel) currentChanceCard).getRelMovement());
    }
    
    /*
    ---------------------- Support Methods ----------------------
     */


}
