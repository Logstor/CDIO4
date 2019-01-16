package model.player;

import model.board.Field;
import model.board.FieldTypeEnum;
import model.board.fields.BoatField;
import model.board.fields.BreweryField;
import model.board.fields.PropertyField;
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


    @Test
    public void getNoOfBreweriesOwned() {
        Player player = new Player("Test", Color.blue, 3000,0);
        player.setNoOfBreweriesOwned(2);
        assertEquals(player.getNoOfBreweriesOwned(),2);
    }

    @Test
    public void setNoOfBreweriesOwned() {
        Player player = new Player("Test", Color.blue, 3000,0);
        player.setNoOfBreweriesOwned(1);
        assertEquals(player.getNoOfBreweriesOwned(),1);
    }

    @Test
    public void getNoOfBoatsOwned() {
        Player player = new Player("Test", Color.blue, 3000,0);
        player.setNoOfBoatsOwned(2);
        assertEquals(player.getNoOfBoatsOwned(),2);

        BoatField field = new BoatField(5, FieldTypeEnum.Boat, "Boat2","Test",4000,Color.blue);
        field.fieldAction(player);
        player.updateNoOfBoatsOwned(1);
        assertEquals(player.getNoOfBoatsOwned(),3);

    }

    @Test
    public void setNoOfBoatsOwned() {
        Player player = new Player("Test", Color.blue, 3000,0);
        player.setNoOfBoatsOwned(2);
        assertEquals(player.getNoOfBoatsOwned(),2);
    }

    @Test
    public void getPrisonStat() {
        Player player = new Player("Test", Color.blue, 3000,0);
        player.setPrisonStat(2);
        assertEquals(player.getPrisonStat(),2);
    }

    @Test
    public void setPrisonStat() {
        Player player = new Player("Test", Color.blue, 3000,0);
        player.setPrisonStat(3);
        assertEquals(player.getPrisonStat(),3);
    }


    @Test
    public void getTotalPosition() {
        Player player = new Player("Test", Color.blue, 3000,0);
        player.setTotalPosition(20);
        assertEquals(player.getTotalPosition(),20);
    }

    @Test
    public void setTotalPosition() {
        Player player = new Player("Test", Color.blue, 3000,0);
        player.setTotalPosition(30);
        assertEquals(player.getTotalPosition(),30);
    }

    @Test
    public void updateNoOfBoatsOwned() {
        Player player = new Player("Test", Color.blue, 3000,0);
        player.setNoOfBoatsOwned(1);
        BoatField field = new BoatField(3,FieldTypeEnum.Boat,"Test2","testen",4000,Color.blue);
        field.fieldAction(player);
        player.updateNoOfBoatsOwned(1);
        assertEquals(player.getNoOfBoatsOwned(),2);

    }

    @Test
    public void updateNoOfBreweriesOwned() {
        Player player = new Player("Test", Color.blue, 3000,0);
        player.setNoOfBreweriesOwned(1);
        BreweryField field = new BreweryField(3,FieldTypeEnum.Brewery,"Test2","testen",4000,Color.blue);
        field.fieldAction(player);
        player.updateNoOfBreweriesOwned(1);
        assertEquals(player.getNoOfBreweriesOwned(),2);
    }

    @Test
    public void addFieldToOwnedFields() {
        Player player = new Player("Test", Color.blue, 3000,0);
        PropertyField field = new PropertyField(5,FieldTypeEnum.Property,"Test3","tester",2000,Color.blue, 200,1000,400,700,1000,1300,1700);
        PropertyField field2 = new PropertyField(5,FieldTypeEnum.Property,"Test3","tester",2000,Color.blue, 200,1000,400,700,1000,1300,1700);
        player.addFieldToOwnedFields(field);
        assertEquals(player.getOwnedFields().size(),1);
        player.addFieldToOwnedFields(field2);
        assertEquals(player.getOwnedFields().size(),2);
    }

    @Test
    public void removeFieldFromOwnedFields() {
        Player player = new Player("Test", Color.blue, 3000,0);
        PropertyField field = new PropertyField(5,FieldTypeEnum.Property,"Test3","tester",2000,Color.blue, 200,1000,400,700,1000,1300,1700);
        player.addFieldToOwnedFields(field);
        assertEquals(player.getOwnedFields().size(),1);
        player.removeFieldFromOwnedFields(field);
        assertEquals(player.getOwnedFields().size(),0);
    }

    @Test
    public void calPlayerTotalValue() {
        Player player = new Player("Test", Color.blue, 1000,0);
        PropertyField field = new PropertyField(5,FieldTypeEnum.Property,"Test3","tester",2000,Color.blue, 200,1000,400,700,1000,1300,1700);
        PropertyField field2 = new PropertyField(5,FieldTypeEnum.Property,"Test3","tester",3000,Color.blue, 200,1000,400,700,1000,1300,1700);
        player.addFieldToOwnedFields(field);
        assertEquals(player.calPlayerTotalValue(),2000);
        player.addFieldToOwnedFields(field2);
        assertEquals(player.calPlayerTotalValue(),3500);
    }
}