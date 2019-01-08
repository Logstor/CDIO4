package model.board;

import model.board.fields.Chancefield;
import model.board.fields.Property;
import model.chancecard.Deck;
import model.player.Player;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class FieldTest {

    @Test
    public void getColor() {
        Field field = new Property(1,"32","chancefelt",5, Color.BLACK);
        field.setColor(Color.BLACK);
        assertEquals(Color.BLACK,field.getColor());
    }

    @Test
    public void setColor() {
        Field field = new Property(1,"32","chancefelt",5, Color.red);
        field.setColor(Color.red);
        assertEquals(Color.red,field.getColor());
    }

    @Test
    public void getFieldNumber() {
        Field field = new Property(2,"32","chancefelt",5, Color.red);
        assertEquals(2,field.getFieldNumber());
    }

    @Test
    public void setFieldNumber() {
        Field field = new Property(1,"32","chancefelt",5, Color.red);
        field.setFieldNumber(10);
        assertEquals(10,field.getFieldNumber());
    }

    @Test
    public void getTitle() {
        Field field = new Property(1,"Jens er sej","chancefelt",5, Color.red);
        assertEquals("Jens er sej",field.getTitle());
    }

    @Test
    public void setTitle() {
        Field field = new Property(1,"32","chancefelt",5, Color.red);
        field.setTitle("Jens lugter");
        assertEquals("Jens lugter",field.getTitle());
    }

    @Test
    public void getDescription() {
        Field field = new Property(1,"32","chancefelt",5, Color.red);
        assertEquals("chancefelt", field.getDescription());
    }

    @Test
    public void setDescription() {
        Field field = new Property(1,"32","chancefelt",5, Color.red);
        field.setDescription("Hej med dig");
        assertEquals("Hej med dig", field.getDescription());
    }

    @Test
    public void getOwner() {
        Player player = new Player("jens",100,1);
        Field field = new Property(3,"32","chancefelt",5, Color.red);
        field.action(player);

        assertEquals(player, field.getOwner());
    }

    @Test
    public void setOwner() {

        Player player = new Player("jens",100,1);
        Field field = new Property(3,"32","chancefelt",5, Color.red);
        field.setOwner(player);

        assertEquals(player, field.getOwner());

    }

    @Test
    public void getCost() {

        Field field = new Property(3,"32","chancefelt",5, Color.red);
        assertEquals(5,field.getCost());

    }

    @Test
    public void setCost() {
        Field field = new Property(3,"32","chancefelt",5, Color.red);
        field.setCost(2);
        assertEquals(2,field.getCost());
    }

    @Test
    public void action() {

        Field field = new Property(3,"32","chancefelt",5, Color.red);
        Player player = new Player("jens",100,1);
        field.action(player);

        assertEquals(player,field.getOwner());

    }

    @Test
    public void action1() {

        Field field = new Chancefield(3,"32","chancefelt",0, Color.red);
        Player player = new Player("jens",100,15);
        Deck deck = new Deck(1);

        field.action(player, deck);

        assertEquals(0,player.getPosition());


    }
}