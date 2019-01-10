package model.chancecard.cards;

import model.chancecard.ChanceCard;
import model.player.Player;

/**
 * @author Rasmus Sander Larsen
 * @date 09-01-2019
 */
public class MoneyBank extends ChanceCard {

    /*
    -------------------------- Fields --------------------------
     */
    
    private int moneyToTransfer;
    
    /*
    ----------------------- Constructor -------------------------
     */
    
    public MoneyBank (String cardType, String cardText, int moneyToTransfer) {
        super(cardType, cardText);
        this.moneyToTransfer = moneyToTransfer;

    }
    
    /*
    ------------------------ Properties -------------------------
     */

    // <editor-folder desc="Properties"

    public int getMoneyToTransfer() {
        return moneyToTransfer;
    }

    public void setMoneyToTransfer(int moneyToTransfer) {
        this.moneyToTransfer = moneyToTransfer;
    }


    // </editor-folder>
    
    /*
    ---------------------- Public Methods -----------------------
     */

    public String toString () {
        StringBuilder toStringBuilder = new StringBuilder();

        toStringBuilder.append(super.toString());
        toStringBuilder.append("ChanceCard MoneyToTransfer: " + moneyToTransfer);

        return toStringBuilder.toString();
    }


    public void cardAction (Player player) {

        player.updateBalance(moneyToTransfer);

    }
    
    /*
    ---------------------- Support Methods ----------------------
     */


}
