package model.chancecard.cards;

import model.chancecard.ChanceCard;
import model.player.Player;

/**
 * @author Rasmus Sander Larsen
 * @date 09-01-2019
 */
public class MovingAbs extends ChanceCard {

    /*
    -------------------------- Fields --------------------------
     */
    
    private int absFieldNo;
    
    /*
    ----------------------- Constructor -------------------------
     */
    
    public MovingAbs(String cardType, String title, int absFieldNo) {
        super(cardType, title);
        this.absFieldNo=absFieldNo;
    }
    
    /*
    ------------------------ Properties -------------------------
     */

    // <editor-folder desc="Properties"

    public int getAbsFieldNo() {
        return absFieldNo;
    }

    public void setAbsFieldNo(int absFieldNo) {
        this.absFieldNo = absFieldNo;
    }


    // </editor-folder>
    
    /*
    ---------------------- Public Methods -----------------------
     */

    public void cardAction(Player player) {

        int prePosition = player.getPosition();
        int finalPositionUpdate;
        if (prePosition>absFieldNo) {
            finalPositionUpdate = absFieldNo + (39-prePosition) + 1; // +1 for 0 in index
        } else {
            finalPositionUpdate= absFieldNo-prePosition;
        }
        player.updatePosition(finalPositionUpdate);
    }
    
    
    /*
    ---------------------- Support Methods ----------------------
     */


}
