package model.chancecard.cards;

import model.chancecard.ChanceCard;
import model.player.Player;

/**
 * @author Rasmus Sander Larsen
 * @date 09-01-2019
 */
public class MovingRel extends ChanceCard {

    /*
    -------------------------- Fields --------------------------
     */

    private int relMovement;
    
    
    /*
    ----------------------- Constructor -------------------------
     */
    
    public MovingRel (String cardType, String title, int relMovement) {
        super(cardType, title);
        this.relMovement = relMovement;
    }
    
    /*
    ------------------------ Properties -------------------------
     */

    // <editor-folder desc="Properties"

    public int getRelMovement() {
        return relMovement;
    }

    public void setRelMovement(int relMovement) {
        this.relMovement = relMovement;
    }


    // </editor-folder>
    
    /*
    ---------------------- Public Methods -----------------------
     */
    
    public void cardAction (Player player) {
        player.updatePosition(relMovement);
    }
    
    /*
    ---------------------- Support Methods ----------------------
     */


}
