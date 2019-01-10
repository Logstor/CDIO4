package model.chancecard;

import java.util.ArrayList;
import java.util.Collections;

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

    /**
     * Method shuffles the deck of ChanceCard.
     * @param noOfShuffles decides the number of times the deck is shuffled.
     */
    public void shuffleDeck (int noOfShuffles) {
        for (int i = 1; i <= noOfShuffles; i++) {
            Collections.shuffle(chanceCardDeck);
        }
    }

    /**
     * Method draws the top chanceCard in the deck and puts it to the bottom.
     * @return The top ChanceCard in the deck.
     */
    public ChanceCard drawChanceCard () {
        ChanceCard drawedChanceCard = chanceCardDeck.get(0);
        chanceCardDeck.remove(0);
        chanceCardDeck.add(drawedChanceCard);

        return drawedChanceCard;
    }
    
    /*
    ---------------------- Support Methods ----------------------
     */


}
