package model.player;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void isHasLost() {
        Player player = new Player("Test", Color.blue, 3000,1);
        player.setHasLost(true);
        assertEquals(player.isHasLost(),true);
    }

    @Test
    public void setHasLost() {
        Player player = new Player("Test", Color.blue, 3000,1);
        player.setHasLost(false);
        assertEquals(player.isHasLost(),false);
    }

    @Test
    public void getAccount() {
        Player player = new Player("Test", Color.blue, 3000,1);
        assertEquals(player.getAccount().getBalance(),3000);

    }
/*
Aint in use
    @Test
    public void setAccount() {

    }
*/
    @Test
    public void getName() {
        Player player = new Player("Test", Color.blue, 3000,1);
        assertEquals(player.getName(),"Test");
    }

    @Test
    public void setName() {
        Player player = new Player("Test", Color.blue, 3000,1);
        player.setName("Test2");
        assertEquals(player.getName(),"Test2");
    }

    @Test
    public void getBreweriesOwned() {
        Player player = new Player("Test", Color.blue, 3000,1);
        player.setBreweriesOwned(1);
        assertEquals(player.getBreweriesOwned(),1);
    }

    @Test
    public void setBreweriesOwned() {
        Player player = new Player("Test", Color.blue, 3000,1);
        player.setBreweriesOwned(2);
        assertEquals(player.getBreweriesOwned(),2);
    }

    @Test
    public void getBoatsOwned() {
        Player player = new Player("Test", Color.blue, 3000,1);
        player.setBoatsOwned(2);
        assertEquals(player.getBoatsOwned(),2);
    }

    @Test
    public void setBoatsOwned() {
        Player player = new Player("Test", Color.blue, 3000,1);
        player.setBoatsOwned(2);
        assertEquals(player.getBoatsOwned(),2);
    }

    @Test
    public void isInPrison() {
        Player player = new Player("Test", Color.blue, 3000,1);
        assertEquals(player.isInPrison(),false);

    }

    @Test
    public void setInPrison() {
        Player player = new Player("Test", Color.blue, 3000,1);
        player.setInPrison(true);
        assertEquals(player.isInPrison(),true);

    }

    @Test
    public void getPosition() {
        Player player = new Player("Test", Color.blue, 3000,0);
        player.updatePosition(5);
        assertEquals(player.getPosition(),5);
    }

    @Test
    public void setPosition() {
        Player player = new Player("Test", Color.blue, 3000,1);
        player.setPosition(11);
        assertEquals(player.getPosition(),11);
    }

    @Test
    public void getToken() {
        Player player = new Player("Test", Color.blue, 3000,1);
        Token token = new Token(Color.blue);
        player.setToken(token);
        assertEquals(player.getToken().getCarColor(),Color.blue);
    }

    @Test
    public void setToken() {
        Player player = new Player("Test", Color.blue, 3000,1);
        Token token = new Token(Color.red);
        player.setToken(token);
        assertEquals(player.getToken().getCarColor(),Color.red);
    }

    @Test
    public void updateBalance() {
        Player player = new Player("Test", Color.blue, 3000,1);
        player.updateBalance(500);
        assertEquals(player.getAccount().getBalance(),3500);
        player.updateBalance(-3000);
        assertEquals(player.getAccount().getBalance(),500);

        /*
        player.updateBalance(-600);
        assertEquals(player.getAccount().getBalance(),0);
        */
    }

    @Test
    public void updatePosition() {
        Player player = new Player("Test", Color.blue, 3000,0);
        player.updatePosition(11);
        assertEquals(player.getPosition(),11);
        player.updatePosition(-3);
        assertEquals(player.getPosition(),8);
        player.updatePosition(34);
        assertEquals(player.getPosition(),2);
        player.updatePosition(-5);
        assertEquals(player.getPosition(),37);
    }
}