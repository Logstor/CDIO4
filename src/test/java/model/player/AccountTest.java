package model.player;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class AccountTest {

    @Test
    public void getBalance() {
        Player player = new Player("Test", Color.blue, 3000,1);
        assertEquals(player.getAccount().getBalance(),3000);
    }

    @Test
    public void setBalance() {
        Player player = new Player("Test", Color.blue, 3000,1);
        player.getAccount().setBalance(2000);
        assertEquals(player.getAccount().getBalance(),2000);
    }

    @Test
    public void updateBalance() {
        Player player = new Player("Test", Color.blue, 3000,1);;
        player.getAccount().updateBalance(1000);
        assertEquals(player.getAccount().getBalance(),4000);

    }
}