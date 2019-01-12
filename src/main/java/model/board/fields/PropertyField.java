package model.board.fields;

import model.board.Field;
import model.cup.*;
import model.player.Player;

import java.awt.*;

/**
 * @author Rasmus Sander Larsen
 * @date 07-01-2019
 */
public class PropertyField extends Field {

    /*
    -------------------------- Fields --------------------------
     */

    private int fieldHousePrice;
    private int fieldRent;
    private int field1HouseRent;
    private int field2HouseRent;
    private int field3HouseRent;
    private int field4HouseRent;
    private int field5HouseRent;
    
    /*
    ----------------------- Constructor -------------------------
     */
    
    public PropertyField (int fieldNo, String fieldType, String fieldName, String fieldDescription, int fieldCost, Color fieldColor,
    int fieldRent, int fieldHousePrice, int field1HouseRent, int field2HouseRent, int field3HouseRent,
    int field4HouseRent,int field5HouseRent) {
        super(fieldNo, fieldType, fieldName,fieldDescription, fieldCost, fieldColor);

        forSale = true;

        this.fieldRent = fieldRent;
        this.fieldHousePrice = fieldHousePrice;
        this.field1HouseRent = field1HouseRent;
        this.field2HouseRent = field2HouseRent;
        this.field3HouseRent = field3HouseRent;
        this.field4HouseRent = field4HouseRent;
        this.field5HouseRent = field5HouseRent;

    }
    
    /*
    ------------------------ Properties -------------------------
     */

    // <editor-folder desc="Properties"


    // </editor-folder>
    
    /*
    ---------------------- Public Methods -----------------------
     */
    @Override
    public String toString () {
        StringBuilder toStringBuilder = new StringBuilder();

        toStringBuilder.append(super.toString());
        toStringBuilder.append("Field House Price: " + fieldHousePrice + "\n");
        toStringBuilder.append("Field Rent: " + fieldRent + "\n");
        toStringBuilder.append("Field Rent incl. 1 house: " + field1HouseRent + "\n");
        toStringBuilder.append("Field Rent incl. 2 house: " + field2HouseRent + "\n");
        toStringBuilder.append("Field Rent incl. 3 house: " + field3HouseRent + "\n");
        toStringBuilder.append("Field Rent incl. 4 house: " + field4HouseRent + "\n");
        toStringBuilder.append("Field Rent incl. 5 house: " + field5HouseRent + "\n");

        return toStringBuilder.toString();
    }

    public void fieldAction (Player player) {

        if (fieldOwner ==null) {
            actionText = "Ejendommen du er landet på er til salg og kan købes for " + getFieldCost() + "?";
            player.updateBalance(getFieldCost());
        } else {
            actionText = "Ejendommen er ejet af " + getFieldOwner() + " og du skal betale " + fieldRent + "for at dit ophold.";
            player.updateBalance(-fieldRent);
            fieldOwner.updateBalance(fieldRent);
        }

    }

    public void fieldAction (Player player, Cup cup) {}
    
    /*
    ---------------------- Support Methods ----------------------
     */


}
