package model.chancecard;

import model.player.Player;

/**
 * @author Rasmus Sander Larsen
 * @date 09-01-2019
 */
public abstract class ChanceCard {

    /*
    -------------------------- Fields --------------------------
     */

    protected ChanceCardEnum cardType;
    protected String cardText;

    
    /*
    ----------------------- Constructor -------------------------
     */

    protected ChanceCard (ChanceCardEnum cardType, String cardText) {

        this.cardType=cardType;
        this.cardText = cardText;

    }
    
    /*
    ------------------------ Properties -------------------------
     */

    // <editor-folder desc="Properties"


    public String getCardText() {
        return cardText;
    }

    public void setCardText(String cardText) {
        this.cardText = cardText;
    }

    public ChanceCardEnum getCardType() {
        return cardType;
    }

    public void setCardType(ChanceCardEnum cardType) {
        this.cardType = cardType;
    }

// </editor-folder>
    
    /*
    ---------------------- Public Methods -----------------------
     */

    public String toString () {
        StringBuilder toStringBuilder = new StringBuilder();

        toStringBuilder.append("~~~~~~~~~~~~ ChanceCard Info ~~~~~~~~~~~~\n");
        toStringBuilder.append("ChanceCard Type: " + cardType + "\n");
        toStringBuilder.append("ChanceCard Text: " + cardText + "\n");

        return toStringBuilder.toString();

    }

    protected abstract void cardAction(Player player);
    
    /*
    ---------------------- Support Methods ----------------------
     */


}
