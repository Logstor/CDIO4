package model.board.fields;

import model.board.Field;

import java.awt.*;

/**
 * @author Rasmus Sander Larsen
 * @date 07-01-2019
 */
public class FieldAdder {

    /*
    -------------------------- Fields --------------------------
     */

    private Field[] tempArrayOfFields;
    private int fieldIndexCounter = 0;

    /*
    ----------------------- Constructor -------------------------
     */

    public FieldAdder(Field[] arrayOfFields) {
        tempArrayOfFields=arrayOfFields;
    }
    
    /*
    ------------------------ Properties -------------------------
     */

    // <editor-folder desc="Properties"


    // </editor-folder>
    
    /*
    ---------------------- Public Methods -----------------------
     */
    
    public void addFieldFromFieldType (int fieldNo, String fieldType, String fieldName,String fieldDescription, int fieldCost, Color fieldColor,
                                       int fieldRent, int fieldHousePrice, int field1HouseRent, int field2HouseRent, int field3HouseRent,
                                       int field4HouseRent,int field5HouseRent) {

        switch (fieldType) {
            case "Property":
                addPropertyField(fieldNo, fieldType, fieldName,fieldDescription, fieldCost, fieldColor, fieldRent, fieldHousePrice,
                        field1HouseRent, field2HouseRent, field3HouseRent, field4HouseRent, field5HouseRent);
                break;
            case "Start":
                addStartField(fieldNo,fieldType,fieldName,fieldDescription,fieldCost,fieldColor);
                break;

            case "Action":
                addActionField(fieldNo,fieldType,fieldName,fieldDescription,fieldCost,fieldColor);
                break;

            case "Boat":
                addBoatField(fieldNo,fieldType,fieldName,fieldDescription,fieldCost,fieldColor);
                break;

            case "Brewery":
                addBreweryField(fieldNo,fieldType,fieldName,fieldDescription,fieldCost,fieldColor);
                break;

            case "ChanceCard":
                addChanceField(fieldNo,fieldType,fieldName,fieldDescription,fieldCost,fieldColor);
                break;

            case "Parking":
                addParkingField(fieldNo,fieldType,fieldName,fieldDescription,fieldCost,fieldColor);
                break;

            case "Prison":
                addPrisonField(fieldNo,fieldType,fieldName,fieldDescription,fieldCost,fieldColor);
                break;
            default:
                break;
        }

        fieldIndexCounter++;
    }
    
    /*
    ---------------------- Support Methods ----------------------
     */

    private void addStartField (int fieldNo, String fieldType, String fieldName,String fieldDescription, int fieldCost, Color fieldColor) {
        tempArrayOfFields[fieldIndexCounter] = new StartField(fieldNo, fieldType, fieldName,fieldDescription, fieldCost, fieldColor);
    }

    private void addPropertyField(int fieldNo, String fieldType, String fieldName,String fieldDescription, int fieldCost, Color fieldColor,
                                  int fieldRent, int fieldHousePrice, int field1HouseRent, int field2HouseRent,
                                  int field3HouseRent, int field4HouseRent, int field5HouseRent) {

        tempArrayOfFields[fieldIndexCounter] = new PropertyField(fieldNo, fieldType, fieldName,fieldDescription, fieldCost,
                fieldColor, fieldRent, fieldHousePrice, field1HouseRent, field2HouseRent, field3HouseRent,
                field4HouseRent, field5HouseRent);
    }

    private void addActionField (int fieldNo, String fieldType, String fieldName,String fieldDescription, int fieldCost, Color fieldColor) {
        tempArrayOfFields[fieldIndexCounter] = new ActionField(fieldNo, fieldType, fieldName,fieldDescription, fieldCost, fieldColor);
    }

    private void addBoatField (int fieldNo, String fieldType, String fieldName,String fieldDescription, int fieldCost, Color fieldColor) {
        tempArrayOfFields[fieldIndexCounter] = new BoatField(fieldNo, fieldType, fieldName,fieldDescription, fieldCost, fieldColor);
    }

    private void addBreweryField (int fieldNo, String fieldType, String fieldName,String fieldDescription, int fieldCost, Color fieldColor) {
        tempArrayOfFields[fieldIndexCounter] = new BreweryField(fieldNo, fieldType, fieldName,fieldDescription, fieldCost, fieldColor);
    }

    private void addChanceField (int fieldNo, String fieldType, String fieldName,String fieldDescription, int fieldCost, Color fieldColor) {
        tempArrayOfFields[fieldIndexCounter] = new ChanceField(fieldNo, fieldType, fieldName,fieldDescription, fieldCost, fieldColor);
    }

    private void addParkingField (int fieldNo, String fieldType, String fieldName,String fieldDescription, int fieldCost, Color fieldColor) {
        tempArrayOfFields[fieldIndexCounter] = new ParkingField(fieldNo, fieldType, fieldName,fieldDescription, fieldCost, fieldColor);
    }

    private void addPrisonField (int fieldNo, String fieldType, String fieldName,String fieldDescription, int fieldCost, Color fieldColor) {
        tempArrayOfFields[fieldIndexCounter] = new PrisonField(fieldNo, fieldType, fieldName,fieldDescription, fieldCost, fieldColor);
    }

}
