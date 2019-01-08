package model.chancecard.cards;

import model.player.Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class MovingRelTest {

    @Test
    public void action() {
        MovingRel movingRel = new MovingRel("movingRel","Does It move","If it move the test is complete",4);
        Player player = new Player("jens",100,3);
        movingRel.action(player);

        assertEquals(7,player.getPosition());
    }
}