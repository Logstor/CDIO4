package model.board.fields;

import model.board.Field;
import model.board.FieldTypeEnum;

import java.awt.*;

/**
 * @author Rasmus Sander Larsen
 * @date 07-01-2019
 */
public class BoatField extends Field {

    /*
    -------------------------- Fields --------------------------
     */
    
    
    
    /*
    ----------------------- Constructor -------------------------
     */

    public BoatField(int fieldNo, FieldTypeEnum fieldType, String fieldName, String fieldDescription, int fieldCost, Color fieldColor) {
        super(fieldNo,fieldType,fieldName,fieldDescription,fieldCost,fieldColor);
        pledged =true;
    }
    
    /*
    ------------------------ Properties -------------------------
     */

    // <editor-folder desc="Properties"


    // </editor-folder>
    
    /*
    ---------------------- Public Methods -----------------------
     */
    
    /*
    ---------------------- Support Methods ----------------------
     */


    private int rentByOwnersNoOfBoats () {
        int rent;
        int noOfBoatsOwned = fieldOwner.getNoOfBoatsOwned();

        switch (noOfBoatsOwned) {
            case 1:
                rent = 500;
                break;

            case 2:
                rent = 1000;
                break;
            case 3:
                rent = 2000;
                break;

            case 4:
                rent = 4000;
                break;

            default:
                rent = 0;
                break;
        }
        return rent;
        }


}
