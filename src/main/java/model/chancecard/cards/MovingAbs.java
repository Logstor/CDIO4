package model.chancecard.cards;

import model.chancecard.ChanceCard;
import model.chancecard.CardTypeEnum;
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
    
    public MovingAbs(CardTypeEnum cardType, String title, int absFieldNo) {
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

    public String toString () {
        StringBuilder toStringBuilder = new StringBuilder();

        toStringBuilder.append(super.toString());
        toStringBuilder.append("ChanceCard Moving to FieldNo: " + absFieldNo);

        return toStringBuilder.toString();
    }
    /*
    ---------------------- Support Methods ----------------------
     */


}
