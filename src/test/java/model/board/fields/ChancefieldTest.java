package model.board.fields;

import model.board.Field;
import model.chancecard.Card;
import model.chancecard.Deck;
import model.player.Player;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class ChancefieldTest {

    @Test
    public void action() {

        Deck deck = new Deck(1);
        Player player = new Player("jens",100,10);
        Field field = new Chancefield(1,"32","chancefelt",0, Color.red);
        field.action(player,deck);
        assertEquals(0,player.getPosition());

    }



}