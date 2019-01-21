package model.player;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class TokenTest {

    @Test
    public void getCarType() {
        Token token = new Token(Color.blue);
        assertEquals(token.getCarType(),"Bil");
    }

    @Test
    public void setCarType() {
        Token token = new Token(Color.blue);
        token.setCarType("Space ship");
        assertEquals(token.getCarType(),"Space ship");
    }

    /* Anvendes ikke
    @Test
    public void getCarPattern() {
    }

    @Test
    public void setCarPattern() {
    }
    */

    @Test
    public void getCarColor() {
        Token token = new Token(Color.blue);
        assertEquals(token.getCarColor(),Color.blue);
    }

    @Test
    public void setCarColor() {
        Token token = new Token(Color.blue);
        token.setCarColor(Color.RED);
        assertEquals(token.getCarColor(),Color.RED);
    }

    @Test
    public void getPatternColor() {
        Token token = new Token(Color.RED);
        assertEquals(token.getPatternColor(),Color.RED);
    }

    @Test
    public void setPatternColor() {
        Token token = new Token(Color.blue);
        token.setPatternColor(Color.YELLOW);
        assertEquals(token.getPatternColor(),Color.YELLOW);
    }
}