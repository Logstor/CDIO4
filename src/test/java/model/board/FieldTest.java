package model.board;

/**
 * @author Søren Poulsen
 * @date 16-01-2019
 */

import model.board.fields.PropertyField;
import model.player.Player;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class FieldTest {

    @Test
    public void getFieldNo() {
        Field field = new PropertyField(5,FieldTypeEnum.Property,"Test3","tester",2000, Color.blue, 200,1000,400,700,1000,1300,1700);
        assertEquals(field.getFieldNo(),5);

    }

    @Test
    public void setFieldNo() {
        Field field = new PropertyField(5,FieldTypeEnum.Property,"Test3","tester",2000, Color.blue, 200,1000,400,700,1000,1300,1700);
        field.setFieldNo(3);
        assertEquals(field.getFieldNo(),3);
    }

    @Test
    public void getFieldName() {
        Field field = new PropertyField(5,FieldTypeEnum.Property,"Test3","tester",2000, Color.blue, 200,1000,400,700,1000,1300,1700);
        assertEquals(field.getFieldName(),"Test3");
    }

    @Test
    public void setFieldName() {
        Field field = new PropertyField(5,FieldTypeEnum.Property,"Test3","tester",2000, Color.blue, 200,1000,400,700,1000,1300,1700);
        field.setFieldName("Det virker");
        assertEquals(field.getFieldName(),"Det virker");
    }

    @Test
    public void getFieldType() {
        Field field = new PropertyField(5,FieldTypeEnum.Property,"Test3","tester",2000, Color.blue, 200,1000,400,700,1000,1300,1700);
        assertEquals(field.getFieldType(),FieldTypeEnum.Property);
    }

    @Test
    public void setFieldType() {
        Field field = new PropertyField(5,FieldTypeEnum.Property,"Test3","tester",2000, Color.blue, 200,1000,400,700,1000,1300,1700);
        field.setFieldType(FieldTypeEnum.Boat);
        assertEquals(field.getFieldType(),FieldTypeEnum.Boat);
    }

    @Test
    public void getFieldCost() {
        Field field = new PropertyField(5,FieldTypeEnum.Property,"Test3","tester",2000, Color.blue, 200,1000,400,700,1000,1300,1700);
        assertEquals(field.getFieldCost(),2000);
    }

    @Test
    public void setFieldCost() {
        Field field = new PropertyField(5,FieldTypeEnum.Property,"Test3","tester",2000, Color.blue, 200,1000,400,700,1000,1300,1700);
        field.setFieldCost(4000);
        assertEquals(field.getFieldCost(),4000);
    }

    @Test
    public void getFieldColor() {
        Field field = new PropertyField(5,FieldTypeEnum.Property,"Test3","tester",2000, Color.blue, 200,1000,400,700,1000,1300,1700);
        assertEquals(field.getFieldColor(),Color.blue);
    }

    @Test
    public void setFieldColor() {
        Field field = new PropertyField(5,FieldTypeEnum.Property,"Test3","tester",2000, Color.blue, 200,1000,400,700,1000,1300,1700);
        field.setFieldColor(Color.black);
        assertEquals(field.getFieldColor(),Color.black);
    }

    @Test
    public void getActionText() {
        Field field = new PropertyField(5,FieldTypeEnum.Property,"Test3","tester",2000, Color.blue, 200,1000,400,700,1000,1300,1700);
        field.setActionText("hej");
        assertEquals(field.getActionText(),"hej");
    }

    @Test
    public void setActionText() {
        Field field = new PropertyField(5,FieldTypeEnum.Property,"Test3","tester",2000, Color.blue, 200,1000,400,700,1000,1300,1700);
        field.setActionText("Test1");
        assertEquals(field.getActionText(),"Test1");
    }

    @Test
    public void getFieldOwner() {
        Player player = new Player("Tester",Color.blue,15000,0);
        Field field = new PropertyField(5,FieldTypeEnum.Property,"Test3","tester",2000, Color.blue, 200,1000,400,700,1000,1300,1700);
        field.setFieldOwner(player);
        assertEquals(field.getFieldOwner(),player);
    }

    @Test
    public void setFieldOwner() {
        Player player = new Player("Tester",Color.blue,15000,0);
        Field field = new PropertyField(5,FieldTypeEnum.Property,"Test3","tester",2000, Color.blue, 200,1000,400,700,1000,1300,1700);
        field.setFieldOwner(player);
        assertEquals(field.getFieldOwner(),player);
    }

    @Test
    public void getFieldDescription() {
        Field field = new PropertyField(5,FieldTypeEnum.Property,"Test3","tester",2000, Color.blue, 200,1000,400,700,1000,1300,1700);
        assertEquals(field.getFieldDescription(),"tester");

    }

    @Test
    public void setFieldDescription() {
        Field field = new PropertyField(5,FieldTypeEnum.Property,"Test3","tester",2000, Color.blue, 200,1000,400,700,1000,1300,1700);
        field.setFieldDescription("go go bro");
        assertEquals(field.getFieldDescription(),"go go bro");


    }

    @Test
    public void isForSale() {
        Field field = new PropertyField(5,FieldTypeEnum.Property,"Test3","tester",2000, Color.blue, 200,1000,400,700,1000,1300,1700);
        assertEquals(field.isForSale(),true);
    }

    @Test
    public void setForSale() {
        Field field = new PropertyField(5,FieldTypeEnum.Property,"Test3","tester",2000, Color.blue, 200,1000,400,700,1000,1300,1700);
        field.setForSale(false);
        assertEquals(field.isForSale(),false);
    }
}