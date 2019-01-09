package model.board;

import model.player.Player;
import model.cup.*;

import java.awt.*;

/**
 * @author Rasmus Sander Larsen
 * @date 16-12-2018
 */
public abstract class Field {

    /*
    -------------------------- Fields --------------------------
     */

    protected int fieldNo;
    protected String fieldName;
    protected String fieldType;
    protected int fieldCost;
    protected Color fieldColor;
    protected String actionText;
    protected String fieldDescription;
    protected Player owner;
    protected boolean forSale;

    
    /*
    ----------------------- Constructor -------------------------
     */

    protected Field (int fieldNo, String fieldType, String fieldName,String fieldDescription, int fieldCost, Color fieldColor) {

        this.fieldNo=fieldNo;
        this.fieldType=fieldType;
        this.fieldName = fieldName;
        this.fieldDescription = fieldDescription;
        this.fieldCost = fieldCost;
        this.fieldColor=fieldColor;
        owner=null;

    }
    
    
    /*
    ------------------------ Properties -------------------------
     */

    // <editor-folder desc="Properties"

    public int getFieldNo() {
        return fieldNo;
    }

    public void setFieldNo(int fieldNo) {
        this.fieldNo = fieldNo;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public int getFieldCost() {
        return fieldCost;
    }

    public void setFieldCost(int fieldCost) {
        this.fieldCost = fieldCost;
    }

    public Color getFieldColor() {
        return fieldColor;
    }

    public void setFieldColor(Color fieldColor) {
        this.fieldColor = fieldColor;
    }

    public String getActionText() {
        return actionText;
    }

    public void setActionText(String actionText) {
        this.actionText = actionText;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public String getFieldDescription() {
        return fieldDescription;
    }

    public void setFieldDescription(String fieldDescription) {
        this.fieldDescription = fieldDescription;
    }

    public boolean isForSale() {
        return forSale;
    }

    public void setForSale(boolean forSale) {
        this.forSale = forSale;
    }

    // </editor-folder>
    
    /*
    ---------------------- Public Methods -----------------------
     */

    public String toString () {
        StringBuilder toStringBuilder = new StringBuilder();

        toStringBuilder.append("Field No.: " +fieldNo + "\n");
        toStringBuilder.append("Field Type: " + fieldType + "\n");
        toStringBuilder.append("Field Name: " + fieldName + "\n");
        toStringBuilder.append("Field Description: " + fieldDescription + "\n");
        toStringBuilder.append("Field Cost: " + fieldCost + "\n");
        toStringBuilder.append("Field Color: " + fieldColor + "\n");

        return toStringBuilder.toString();
    }
    
    protected abstract void fieldAction (Player player);

    protected abstract void fieldAction (Player player, Cup cup);

    
    /*
    ---------------------- Support Methods ----------------------
     */


}
