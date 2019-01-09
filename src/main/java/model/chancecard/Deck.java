package model.chancecard;

import java.util.ArrayList;

/**
 * @author Rasmus Sander Larsen
 * @date 09-01-2019
 */
public class Deck {

    /*
    -------------------------- Fields --------------------------
     */

    private ArrayList<ChanceCard> chanceCardDeck;
    
    /*
    ----------------------- Constructor -------------------------
     */
    
    public Deck () {
        chanceCardDeck = new ArrayList<>();

    }
    
    /*
    ------------------------ Properties -------------------------
     */

    // <editor-folder desc="Properties"

    public ArrayList<ChanceCard> getChanceCardDeck() {
        return chanceCardDeck;
    }

    public void setChanceCardDeck(ArrayList<ChanceCard> chanceCardDeck) {
        this.chanceCardDeck = chanceCardDeck;
    }


    // </editor-folder>
    
    /*
    ---------------------- Public Methods -----------------------
     */
    
    
    
    /*
    ---------------------- Support Methods ----------------------
     */


}
