package model.board;

import model.board.fields.FieldAdder;

import java.awt.*;
import java.util.HashMap;

/**
 * @author Rasmus Sander Larsen
 * @date 07-01-2019
 */
public class BoardManager {

    /*
    -------------------------- Fields --------------------------
     */

    private String lineSplitter;
    private String colorSplitter;

    private int fieldNumber_Index;
    private int fieldType_Index;
    private int fieldName_Index;
    private int fieldCost_Index;
    private int fieldColor_Index;
    private int fieldHousePrice_Index;
    private int fieldRent_Index;
    private int field1HouseRent_Index;
    private int field2HouseRent_Index;
    private int field3HouseRent_Index;
    private int field4HouseRent_Index;
    private int field5HouseRent_Index;

    private HashMap<String, String > boardInfoMap;

    /*
    ----------------------- Constructor -------------------------
     */
    
    public BoardManager () {
        boardInfoMap = new HashMap<>();

    }
    
    /*
    ------------------------ Properties -------------------------
     */

    // <editor-folder desc="Properties"

    public int getFieldNumber_Index() {
        return fieldNumber_Index;
    }

    public void setFieldNumber_Index(int fieldNumber_Index) {
        this.fieldNumber_Index = fieldNumber_Index;
    }

    public int getFieldType_Index() {
        return fieldType_Index;
    }

    public void setFieldType_Index(int fieldType_Index) {
        this.fieldType_Index = fieldType_Index;
    }

    public int getFieldName_Index() {
        return fieldName_Index;
    }

    public void setFieldName_Index(int fieldName_Index) {
        this.fieldName_Index = fieldName_Index;
    }

    public int getFieldCost_Index() {
        return fieldCost_Index;
    }

    public void setFieldCost_Index(int fieldCost_Index) {
        this.fieldCost_Index = fieldCost_Index;
    }

    public int getFieldColor_Index() {
        return fieldColor_Index;
    }

    public void setFieldColor_Index(int fieldColor_Index) {
        this.fieldColor_Index = fieldColor_Index;
    }

    public int getFieldHousePrice_Index() {
        return fieldHousePrice_Index;
    }

    public void setFieldHousePrice_Index(int fieldHousePrice_Index) {
        this.fieldHousePrice_Index = fieldHousePrice_Index;
    }

    public int getFieldRent_Index() {
        return fieldRent_Index;
    }

    public void setFieldRent_Index(int fieldRent_Index) {
        this.fieldRent_Index = fieldRent_Index;
    }

    public int getField1HouseRent_Index() {
        return field1HouseRent_Index;
    }

    public void setField1HouseRent_Index(int field1HouseRent_Index) {
        this.field1HouseRent_Index = field1HouseRent_Index;
    }

    public int getField2HouseRent_Index() {
        return field2HouseRent_Index;
    }

    public void setField2HouseRent_Index(int field2HouseRent_Index) {
        this.field2HouseRent_Index = field2HouseRent_Index;
    }

    public int getField3HouseRent_Index() {
        return field3HouseRent_Index;
    }

    public void setField3HouseRent_Index(int field3HouseRent_Index) {
        this.field3HouseRent_Index = field3HouseRent_Index;
    }

    public int getField4HouseRent_Index() {
        return field4HouseRent_Index;
    }

    public void setField4HouseRent_Index(int field4HouseRent_Index) {
        this.field4HouseRent_Index = field4HouseRent_Index;
    }

    public int getField5HouseRent_Index() {
        return field5HouseRent_Index;
    }

    public void setField5HouseRent_Index(int field5HouseRent_Index) {
        this.field5HouseRent_Index = field5HouseRent_Index;
    }

    public HashMap<String, String> getBoardInfoMap() {
        return boardInfoMap;
    }

    public void setBoardInfoMap(HashMap<String, String> boardInfoMap) {
        this.boardInfoMap = boardInfoMap;
    }

    public String getLineSplitter() {
        return lineSplitter;
    }

    public void setLineSplitter(String lineSplitter) {
        this.lineSplitter = lineSplitter;
    }

    public String getColorSplitter() {
        return colorSplitter;
    }

    public void setColorSplitter(String colorSplitter) {
        this.colorSplitter = colorSplitter;
    }

    // </editor-folder>
    
    /*
    ---------------------- Public Methods -----------------------
     */

    public void setupBoard (HashMap<String,String> hashMapWithBoardInfo, Board board) {
        //Loads the indexNumber of for all the fieldInformation into variables.
        setupIndexVariables(hashMapWithBoardInfo);
        // Loads field information into a HashMap.
        setupBoardInfoMap(hashMapWithBoardInfo, board);
        // Loads Board with Fields from BoardInfoMap
        boardInfoMapIntoBoard(board);
    }

    
    /*
    ---------------------- Support Methods ----------------------
     */

    private void boardInfoMapIntoBoard (Board board) {

        int tempFieldNo;
        String tempFieldType;
        String tempFieldName;
        int tempFieldCost;
        int tempRent;
        int tempHousePrice;
        int temp1HouseRent;
        int temp2HouseRent;
        int temp3HouseRent;
        int temp4HouseRent;
        int temp5HouseRent;

        Color tempFieldColor;

        FieldAdder fieldAdder = new FieldAdder(board.getBoard());

        for (int i = 1; i<=board.getNUMBEROFFIELDS();i++) {

            String line = boardInfoMap.get("field"+i);
            String[] fieldInfo = line.split(lineSplitter);

            tempFieldNo = Integer.parseInt(fieldInfo[fieldNumber_Index]);
            tempFieldType = fieldInfo[fieldType_Index];
            tempFieldName = fieldInfo[fieldName_Index];
            tempFieldCost = Integer.parseInt(fieldInfo[fieldCost_Index]);
            if (tempFieldType.equals("Property")) {
                tempRent = Integer.parseInt(fieldInfo[fieldRent_Index]);
                tempHousePrice = Integer.parseInt(fieldInfo[fieldHousePrice_Index]);
                temp1HouseRent = Integer.parseInt(fieldInfo[field1HouseRent_Index]);
                temp2HouseRent = Integer.parseInt(fieldInfo[field2HouseRent_Index]);
                temp3HouseRent = Integer.parseInt(fieldInfo[field3HouseRent_Index]);
                temp4HouseRent = Integer.parseInt(fieldInfo[field4HouseRent_Index]);
                temp5HouseRent = Integer.parseInt(fieldInfo[field5HouseRent_Index]);
            } else {
                tempRent = 0;
                tempHousePrice = 0;
                temp1HouseRent = 0;
                temp2HouseRent = 0;
                temp3HouseRent = 0;
                temp4HouseRent = 0;
                temp5HouseRent = 0;
            }


            // Setting Up Color
                String[] RGB_StringArray = fieldInfo[fieldColor_Index].split(colorSplitter);
                int tempColorR = Integer.parseInt(RGB_StringArray[0]);
                int tempColorG = Integer.parseInt(RGB_StringArray[1]);
                int tempColorB = Integer.parseInt(RGB_StringArray[2]);
                tempFieldColor = new Color(tempColorR, tempColorG, tempColorB);

            fieldAdder.addFieldFromFieldType(tempFieldNo,tempFieldType,tempFieldName,tempFieldCost,
                    tempFieldColor,tempRent,tempHousePrice,temp1HouseRent,temp2HouseRent,temp3HouseRent,
                    temp4HouseRent,temp5HouseRent);
        }
    }

    private void setupBoardInfoMap (HashMap<String,String > hashMapWithBoardInfo, Board board) {
        for (int i = 1; i <=board.getNUMBEROFFIELDS(); i++) {
            boardInfoMap.put("field"+i,hashMapWithBoardInfo.get("field"+i));
        }

    }

    /**
     *This Method loads the Index numbers for all the variables of a field.
     */
    private void setupIndexVariables (HashMap<String ,String > hashMapWithBoardInfo) {
        lineSplitter = hashMapWithBoardInfo.get("lineSplitter");
        colorSplitter = hashMapWithBoardInfo.get("colorSplitter");


        fieldNumber_Index = Integer.parseInt(hashMapWithBoardInfo.get("FieldNumber_Index"));
        fieldType_Index = Integer.parseInt(hashMapWithBoardInfo.get("FieldType_Index"));
        fieldName_Index = Integer.parseInt(hashMapWithBoardInfo.get("FieldName_Index"));
        fieldCost_Index = Integer.parseInt(hashMapWithBoardInfo.get("FieldCost_Index"));
        fieldColor_Index = Integer.parseInt(hashMapWithBoardInfo.get("FieldColor_Index"));
        fieldHousePrice_Index = Integer.parseInt(hashMapWithBoardInfo.get("FieldHousePrice_Index"));
        fieldRent_Index = Integer.parseInt(hashMapWithBoardInfo.get("FieldRent_Index"));
        field1HouseRent_Index = Integer.parseInt(hashMapWithBoardInfo.get("Field1HouseRent_Index"));
        field2HouseRent_Index = Integer.parseInt(hashMapWithBoardInfo.get("Field2HouseRent_Index"));
        field3HouseRent_Index = Integer.parseInt(hashMapWithBoardInfo.get("Field3HouseRent_Index"));
        field4HouseRent_Index = Integer.parseInt(hashMapWithBoardInfo.get("Field4HouseRent_Index"));
        field5HouseRent_Index = Integer.parseInt(hashMapWithBoardInfo.get("Field5HouseRent_Index"));
    }

}
