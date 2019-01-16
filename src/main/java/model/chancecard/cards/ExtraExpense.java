package model.chancecard.cards;

import model.chancecard.CardTypeEnum;
import model.chancecard.ChanceCard;

/**
 * @author Rasmus Sander Larsen
 * @date 16-01-2019
 */
public class ExtraExpense extends ChanceCard {

    /*
    -------------------------- Fields --------------------------
     */
    
    private int housePrice = 0, hotelPrice = 0;

    /*
    ----------------------- Constructor -------------------------
     */
    
    public ExtraExpense (CardTypeEnum cardType, String cardText, int housePrice, int hotelPrice) {
        super(cardType, cardText);

        this.housePrice = housePrice;
        this.hotelPrice = hotelPrice;
    }
    
    /*
    ------------------------ Properties -------------------------
     */

    // <editor-folder desc="Properties"


    // </editor-folder>
    
    /*
    ---------------------- Public Methods -----------------------
     */
    
    
    
    /*
    ---------------------- Support Methods ----------------------
     */


}
