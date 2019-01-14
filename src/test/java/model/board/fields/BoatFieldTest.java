package model.board.fields;

import model.player.Player;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class BoatFieldTest {

    @Test
    public void fieldAction() {
        Player player = new Player("Test", Color.blue, 25000,1);
        Player player2 = new Player("Test2", Color.black, 15000,1);

        BoatField boatField = new BoatField(5, "boat", "båd","båden",4000, Color.magenta);

        player.setBoatsOwned(2);
        boatField.fieldAction(player);

        assertEquals(player.getBoatsOwned(),3);
        assertEquals(player.getAccount().getBalance(), 21000);

        boatField.fieldAction(player2);
        assertEquals(player2.getAccount().getBalance(),13000);
    }
}