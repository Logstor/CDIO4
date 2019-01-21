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
    
    private int taxHousePrice, taxHotelPrice;

    /*
    ----------------------- Constructor -------------------------
     */
    
    public ExtraExpense (CardTypeEnum cardType, String cardText, int taxHousePrice, int taxHotelPrice) {
        super(cardType, cardText);

        this.taxHousePrice = taxHousePrice;
        this.taxHotelPrice = taxHotelPrice;
    }
    
    /*
    ------------------------ Properties -------------------------
     */

    // <editor-folder desc="Properties"

    public int getTaxHousePrice() {
        return taxHousePrice;
    }

    public void setTaxHousePrice(int taxHousePrice) {
        this.taxHousePrice = taxHousePrice;
    }

    public int getTaxHotelPrice() {
        return taxHotelPrice;
    }

    public void setTaxHotelPrice(int taxHotelPrice) {
        this.taxHotelPrice = taxHotelPrice;
    }


    // </editor-folder>
    
    /*
    ---------------------- Public Methods -----------------------
     */
    
    
    
    /*
    ---------------------- Support Methods ----------------------
     */


}
