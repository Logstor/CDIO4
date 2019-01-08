package model.chancecard;

import model.player.Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class DeckTest {

    @Test
    public void createDeck() {
        Deck deck = new Deck(3);
        assertEquals(30, deck.getTotalCardsInDeck());

    }


    /*
    Testen her under har 4% chance for a fejle, dette er ikke en fejl,
    men blot sandsynligheden for kortet bliver samme plasering 2 gange i træk efter shuffel.
     */
    @Test
    public void shuffleDeck() {

        Deck deck = new Deck(1);
        Card cardIndex1;
        cardIndex1 = deck.getChanceDeck().get(0);
        deck.shuffleDeck(1);
        Card cardIndex2;
        cardIndex2 = deck.getChanceDeck().get(0);

        if (cardIndex1.title.equals(cardIndex2.title)) {
            deck.shuffleDeck(1);
            cardIndex2 = deck.getChanceDeck().get(0);
        }

        assertNotEquals(cardIndex1.title, cardIndex2.title);

    }

    @Test
    public void drawCard() {

        Deck deck = new Deck(2);
        Card cardindex1;
        cardindex1 = deck.getChanceDeck().get(0);
        Player player = new Player("jens",10,1);
        deck.drawCard();
        Card cardIndexLast;
        cardIndexLast = deck.getChanceDeck().get(deck.getChanceDeck().size()-1);

        assertEquals(cardindex1.title, cardIndexLast.title);



    }
}