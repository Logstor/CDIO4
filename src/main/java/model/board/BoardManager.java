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

    private String colorSplitter;

    private int fieldNumber_Index;
    private int fieldType_Index;
    private int fieldName_Index;
    private int fieldDescription_Index;
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
        FieldAdder fieldAdder = new FieldAdder(board.getBoard());

        for (int i = 1; i<=board.getNUMBEROFFIELDS();i++) {

            String line = boardInfoMap.get("field"+i);
            String[] fieldInfo = line.split(";");

            int tempFieldNo = Integer.parseInt(fieldInfo[fieldNumber_Index]);
            FieldTypeEnum tempFieldType = FieldTypeEnum.valueOf(fieldInfo[fieldType_Index]);
            String tempFieldName = fieldInfo[fieldName_Index];
            String tempFieldDescription = fieldInfo[fieldDescription_Index];
            int tempFieldCost = Integer.parseInt(fieldInfo[fieldCost_Index]);
            int tempRent = Integer.parseInt(fieldInfo[fieldRent_Index]);
            int tempHousePrice = Integer.parseInt(fieldInfo[fieldHousePrice_Index]);
            int temp1HouseRent = Integer.parseInt(fieldInfo[field1HouseRent_Index]);
            int temp2HouseRent = Integer.parseInt(fieldInfo[field2HouseRent_Index]);
            int temp3HouseRent = Integer.parseInt(fieldInfo[field3HouseRent_Index]);
            int temp4HouseRent = Integer.parseInt(fieldInfo[field4HouseRent_Index]);
            int temp5HouseRent = Integer.parseInt(fieldInfo[field5HouseRent_Index]);

            // Setting Up Color
            String[] RGB_StringArray = fieldInfo[fieldColor_Index].split(colorSplitter);
            int tempColorR = Integer.parseInt(RGB_StringArray[0]);
            int tempColorG = Integer.parseInt(RGB_StringArray[1]);
            int tempColorB = Integer.parseInt(RGB_StringArray[2]);
            Color tempFieldColor = new Color(tempColorR, tempColorG, tempColorB);

            fieldAdder.addFieldFromFieldType(tempFieldNo,tempFieldType,tempFieldName,tempFieldDescription,tempFieldCost,
                    tempFieldColor,tempRent,tempHousePrice,temp1HouseRent,temp2HouseRent,temp3HouseRent,
                    temp4HouseRent,temp5HouseRent);
        }
    }

    /**
     * This method loads only the fieldInformation into a HashMap.
     * @param hashMapWithBoardInfo
     * @param board
     */
    private void setupBoardInfoMap (HashMap<String,String > hashMapWithBoardInfo, Board board) {
        for (int i = 1; i <=board.getNUMBEROFFIELDS(); i++) {
            boardInfoMap.put("field"+i,hashMapWithBoardInfo.get("field"+i));
        }

    }


    /**
     * This Method loads the Index numbers for all the variables in the csv-file
     * @param hashMapWithBoardInfo HashMap to load Index information from.
     */
    private void setupIndexVariables (HashMap<String ,String > hashMapWithBoardInfo) {
        colorSplitter = hashMapWithBoardInfo.get("colorSplitter");

        fieldNumber_Index = Integer.parseInt(hashMapWithBoardInfo.get("FieldNumber_Index"));
        fieldType_Index = Integer.parseInt(hashMapWithBoardInfo.get("FieldType_Index"));
        fieldName_Index = Integer.parseInt(hashMapWithBoardInfo.get("FieldName_Index"));
        fieldDescription_Index = Integer.parseInt(hashMapWithBoardInfo.get("FieldDescription_Index"));
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