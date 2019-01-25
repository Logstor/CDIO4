package model.board;

import model.player.Player;

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
    protected FieldTypeEnum fieldType;
    protected int fieldCost;
    protected Color fieldColor;
    protected String actionText;
    protected String fieldDescription;
    protected Player fieldOwner;
    protected boolean pledged;
    
    /*
    ----------------------- Constructor -------------------------
     */

    protected Field (int fieldNo, FieldTypeEnum fieldType, String fieldName,
                     String fieldDescription, int fieldCost, Color fieldColor) {

        this.fieldNo = fieldNo;
        this.fieldType = fieldType;
        this.fieldName = fieldName;
        this.fieldDescription = fieldDescription;
        this.fieldCost = fieldCost;
        this.fieldColor = fieldColor;
        fieldOwner = null;
        pledged = false;

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

    public FieldTypeEnum getFieldType() {
        return fieldType;
    }

    public void setFieldType(FieldTypeEnum fieldType) {
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

    public Player getFieldOwner() {
        return fieldOwner;
    }

    public void setFieldOwner(Player fieldOwner) {
        this.fieldOwner = fieldOwner;
    }

    public String getFieldDescription() {
        return fieldDescription;
    }

    public void setFieldDescription(String fieldDescription) {
        this.fieldDescription = fieldDescription;
    }

    public boolean isPledged() {
        return pledged;
    }

    public void setPledged(boolean pledged) {
        this.pledged = pledged;
    }

    // </editor-folder>
    
    /*
    ---------------------- Public Methods -----------------------
     */

    @Override
    public String toString () {
        StringBuilder toStringBuilder = new StringBuilder();

        toStringBuilder.append("~~~~~~~~~~~~ Field Info ~~~~~~~~~~~~\n");
        toStringBuilder.append("Field No.: " +fieldNo + "\n");
        toStringBuilder.append("Field Type: " + fieldType + "\n");
        toStringBuilder.append("Field Name: " + fieldName + "\n");
        toStringBuilder.append("Field Description: " + fieldDescription + "\n");
        toStringBuilder.append("Field Cost: " + fieldCost + "\n");
        toStringBuilder.append("Field Color: " + fieldColor + "\n");

        return toStringBuilder.toString();
    }

    /*
    ---------------------- Support Methods ----------------------
     */

}
