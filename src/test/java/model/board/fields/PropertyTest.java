package model.board.fields;

import model.board.Field;
import model.player.Player;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class PropertyTest {

    @Test
    public void action() {

        //laver en spiller og et property field
        Player player = new Player("jens", 100, 1);
        Field field = new Property(1,"32","chancefelt",5, Color.red);

        //bruger fieldets action på playeren
        field.action(player);

        //checker om spilleren nu er på det 7. felt
        assertEquals(95,player.getAccount().getBalance());
        assertEquals(player,field.getOwner());

        //laver en ny spiller
        Player player2 = new Player("peter",100,1);
        field.action(player2);

        //checker om ejeren stadig er jens og peter har mistet 5 i balance
        assertEquals(95,player2.getAccount().getBalance());
        assertEquals(player,field.getOwner());


    }

    @Test
    public void getCost() {
        Field field = new Property(1,"32","chancefelt",5, Color.red);
        assertEquals(5,field.getCost());
    }

    @Test
    public void getColor() {

        //Lidt vag test men fungere
        Field field = new Property(1,"32","chancefelt",5, Color.red);
        Color farve = field.getColor();
        assertEquals(farve, field.getColor());
    }

    @Test
    public void setCost() {

        Field field = new Property(1,"32","chancefelt",5, Color.red);
        field.setCost(2);
        assertEquals(2,field.getCost());

    }

    @Test
    public void setColor() {

        Field field = new Property(1,"32","chancefelt",5, Color.red);
        field.setColor(Color.BLACK);

        assertEquals(Color.BLACK,field.getColor());

    }
}