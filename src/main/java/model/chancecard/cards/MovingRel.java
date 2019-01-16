package model.chancecard.cards;

import model.chancecard.ChanceCard;
import model.chancecard.CardTypeEnum;
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
    
    public MovingRel (CardTypeEnum cardType, String title, int relMovement) {
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

    public String toString () {
        StringBuilder toStringBuilder = new StringBuilder();

        toStringBuilder.append(super.toString());
        toStringBuilder.append("ChanceCard Number Of Fields to be moved: " + relMovement);

        return toStringBuilder.toString();

    }

    public void cardAction (Player player) {
        player.updatePosition(relMovement);
    }
    
    /*
    ---------------------- Support Methods ----------------------
     */


}
