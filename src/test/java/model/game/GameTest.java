package model.game;

import model.player.Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void setStartPointsFromNoOfPlayer() {
        Game game = new Game();
        game.setStartPointsFromNoOfPlayer(3);
        Player player = new Player("jens",game.getSTARTPOINT(),1);
        assertEquals(18,player.getAccount().getBalance());

        game.setStartPointsFromNoOfPlayer(2);
        Player player2 = new Player("peter",game.getSTARTPOINT(),1);
        assertEquals(20,player2.getAccount().getBalance());
    }


    @Test
    public void getSTARTPOINT() {
        Game game = new Game();
        game.setStartPointsFromNoOfPlayer(3);
        assertEquals(18,game.getSTARTPOINT());
    }
}