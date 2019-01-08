package model.board.fields;

import model.board.Field;
import model.player.Player;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class StartTest {

    @Test
    public void action() {
        Field start = new Start(1,"titel","dette er her",0, Color.red);
        Player player = new Player("jens",100,1);
        start.action(player);

        assertEquals("Du er landet på startfeltet og har modtaget 2 pengesedler.",start.getActionText());
    }
}