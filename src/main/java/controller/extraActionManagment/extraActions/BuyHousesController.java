package controller.extraActionManagment.extraActions;

import controller.GeneralActionController;
import controller.GuiController;
import controller.extraActionManagment.ExtraAction;
import controller.extraActionManagment.ExtraActionType_Enum;
import model.board.Board;
import model.board.Field;
import model.board.FieldTypeEnum;
import model.board.fields.PropertyField;
import model.player.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Rasmus Sander Larsen
 * @date 16-01-2019
 */
public class BuyHousesController extends ExtraAction {

    /*
    -------------------------- Fields --------------------------
     */
    private ArrayList<Field> propertyFieldsInBoard;
    private ArrayList<PropertyField> propertiesToPutHouseOn;
    private int[] RGB_ColorMatchingColorCroup;
    private int[] propertiesPrFieldColorBoard;
    private int[] propertiesPrFieldColorPlayerFields;

    private Board board;
    
    /*
    ----------------------- Constructor -------------------------
     */

    public BuyHousesController ( Player player, Board board, GuiController guiController, HashMap<String,String> messageMap,
                                GeneralActionController generalActionController) {
        super(player,guiController,messageMap,generalActionController);
        extraActionType = ExtraActionType_Enum.BuyHouse;
        this.board = board;

        propertyFieldsInBoard =  new ArrayList<>();
        propertiesToPutHouseOn = new ArrayList<>();
        RGB_ColorMatchingColorCroup = new int[8];
        propertiesPrFieldColorBoard = new int[8];
        propertiesPrFieldColorPlayerFields = new int[8];
    }
    
    /*
    ------------------------ Properties -------------------------
     */

    // <editor-folder desc="Properties"


    // </editor-folder>
    
    /*
    ---------------------- Public Methods -----------------------
     */
    
    public void doExtraAction() {

        if (checkIfPlayerIsValidForBuyHouses()) {
            if (guiController.getLeftButtonPressed(messageMap.get("WantToBuyHouse?"),
                    messageMap.get("Yes"), messageMap.get("No"))) {
                buyHouses();
            }
        }

    }

    public boolean checkIfPlayerIsValidForBuyHouses () {

        //Adds Properties on board to ArrayList of Fields
        for (Field f : board.getBoard()){
            if (f.getFieldType().equals(FieldTypeEnum.Property)){
                propertyFieldsInBoard.add(f);
            }
        }
        // Sets the FieldPrColorCounterArray, with data from ArrayList of Properties on board.
        propertiesPrFieldColorCounter(propertiesPrFieldColorBoard,propertyFieldsInBoard);

        // Sets the FieldPrColorCounterArray, with data from ArrayList of Fields that Player Owns.
        propertiesPrFieldColorCounter(propertiesPrFieldColorPlayerFields,currentPlayer.getOwnedFields());

        // Checks if player haves the total amout of fields for each FieldColor.
        // If yes, that FieldColor is added to an ArrayList of Color.
        // Fields with this Color is allowed to buy Houses on.
        ArrayList<Color> fieldColorAllowsToPutHouseOn = new ArrayList<>();
        for (int i = 0; i < propertiesPrFieldColorBoard.length; i++){
            if (propertiesPrFieldColorBoard[i]==propertiesPrFieldColorPlayerFields[i]){
                fieldColorAllowsToPutHouseOn.add(new Color(RGB_ColorMatchingColorCroup[i]));
            }
        }

        // If fieldColorAllowedToButHousesOn is bigger than 0, it means that the Player holds of fields of the same Color.
        if (fieldColorAllowsToPutHouseOn.size()>0) {
            // Properties the player owns is added to propertiesToPutHousesOn: ArrayList<PropertyFields>.
            for (int k = 0; k < fieldColorAllowsToPutHouseOn.size(); k++) {
                for (Field field : currentPlayer.getOwnedFields()) {
                    if (field.getFieldType().equals(FieldTypeEnum.Property)) {
                        if (field.getFieldColor().equals(fieldColorAllowsToPutHouseOn.get(k))) {
                            // If Field is a PropertyField and has the allowColor it is added to the Array of PropertiesToPutHousesOn.
                            propertiesToPutHouseOn.add(((PropertyField) field));
                        }

                    }
                }
            }
        }

        return fieldColorAllowsToPutHouseOn.size()>0;
    }
    
    /*
    ---------------------- Support Methods ----------------------
     */

    private void buyHouses ()
    {

        // Asks where the player wants to buy a house. DropDownMenu.
        String nameOnSelectedField = guiController.getUserChoiceProperty(messageMap.get("WhereToBuyHouse?"), propertiesToPutHouseOn);

        for (PropertyField field : propertiesToPutHouseOn) {
            // Finds the PropertyField the players wants to buy.
            if (field.getFieldName().equals(nameOnSelectedField)) {
                // Presents the player for the price of the house.
                if (guiController.getLeftButtonPressed(messageMap.get("HouseOnPropertyCosts")
                                .replace("%housePrice", String.valueOf(field.getFieldHousePrice())),
                        messageMap.get("Yes"), messageMap.get("No"))) {
                    // If the Players still wants to buy the house the Field.noOfHousesOnProperty is updated
                    field.updateHousesOnProperty(1);
                    // Gui is updated with the correct number of Houses or Hotels.
                    guiController.setHousesAndHotels(field.getNoOfHousesOnProperty(), field);
                    // Tells that the house is bought and shows the new rent on the Property.
                    guiController.showMessage(messageMap.get("BoughtHouses").replace("%fieldName", field.getFieldName())
                            .replace("%newRent", String.valueOf(generalActionController.rentFromNoOfHouses(field))));
                }

            }
        }
    }


    private void propertiesPrFieldColorCounter(int[] intArray, ArrayList<Field> fieldArrayList) {

        for (int i = 0; i < fieldArrayList.size(); i++) {
            switch (fieldArrayList.get(i).getFieldColor().toString()) {

                case "java.awt.Color[r=115,g=194,b=251]":
                    RGB_ColorMatchingColorCroup[0] = new Color(115,194,251).getRGB();
                    intArray[0] +=1;
                    break;

                case "java.awt.Color[r=239,g=130,b=13]":
                    RGB_ColorMatchingColorCroup[1] = new Color(239,130,13).getRGB();
                    intArray[1] +=1;
                    break;

                case "java.awt.Color[r=199,g=234,b=70]":
                    RGB_ColorMatchingColorCroup[2] = new Color(199,234,70).getRGB();
                    intArray[2]+=1;
                    break;

                case "java.awt.Color[r=119,g=123,b=126]":
                    RGB_ColorMatchingColorCroup[3] = new Color(119,123,126).getRGB();
                    intArray[3] +=1;
                    break;

                case "java.awt.Color[r=255,g=36,b=0]":
                    RGB_ColorMatchingColorCroup[4] = new Color(255,36,0).getRGB();
                    intArray[4]+=1;
                    break;

                case "java.awt.Color[r=255,g=255,b=255]":
                    RGB_ColorMatchingColorCroup[5] = new Color(255,255,255).getRGB();
                    intArray[5]+=1;
                    break;

                case "java.awt.Color[r=252,g=226,b=5]":
                    RGB_ColorMatchingColorCroup[6] = new Color(252,226,5).getRGB();
                    intArray[6]+=1;
                    break;

                case "java.awt.Color[r=141,g=69,b=133]":
                    RGB_ColorMatchingColorCroup[7] = new Color(141,69,133).getRGB();
                    intArray[7] +=1;
                    break;

                default:
                    break;

            }
        }


    }
}
