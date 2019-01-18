package controller.chanceCardManagement.chanceCardActions;

import controller.chanceCardManagement.ChanceCardAction;
import controller.GeneralActionController;
import controller.GuiController;
import model.board.Field;
import model.board.FieldTypeEnum;
import model.board.fields.PropertyField;
import model.chancecard.ChanceCard;
import model.chancecard.cards.ExtraExpense;
import model.player.Player;

import java.util.HashMap;

/**
 * @author Rasmus Sander Larsen
 * @date 16-01-2019
 */
public class ExtraExpenseAction extends ChanceCardAction {

    /*
    -------------------------- Fields --------------------------
     */
    
    
    
    /*
    ----------------------- Constructor -------------------------
     */
    
    public ExtraExpenseAction (GuiController guiController, HashMap<String,String> messageMap,
                         GeneralActionController generalActionController) {
        super(guiController, messageMap, generalActionController);
    }
    
    /*
    ------------------------ Properties -------------------------
     */

    // <editor-folder desc="Properties"


    // </editor-folder>
    
    /*
    ---------------------- Public Methods -----------------------
     */
    
    public void chanceCardAction (Player player, ChanceCard currentCard) {
        int totalExtraExpenseToPay = 0;
        int taxHousePrice = ((ExtraExpense) currentCard).getTaxHousePrice();
        int taxHotelPrice = ((ExtraExpense) currentCard).getTaxHotelPrice();

        for (Field field : player.getOwnedFields()) {
            if (field.getFieldType().equals(FieldTypeEnum.Property)) {
                // Gets number of houses on Property.
                int noOfHousesOnProperty = ((PropertyField) field).getNoOfHousesOnProperty();
                // If number of houses on Property is 5, aka. Hotel. Uses HotelTax and adds to total.
                if (noOfHousesOnProperty==5) {
                    totalExtraExpenseToPay += taxHotelPrice;
                } else {
                    // Otherwise the tax pr. houses is multiplied with number of houses and added to total.
                    totalExtraExpenseToPay += (noOfHousesOnProperty*taxHousePrice);
                }
            }
        }
        // Set, Display og showMessage.
        setDisplayMessageChanceCardOnGUi(currentCard);

        // Show result depending on number of houses.
        if (totalExtraExpenseToPay==0) {
            guiController.showMessage(messageMap.get("NoExtraExpense"));

        } else {
            guiController.showMessage(messageMap.get("PayExtraExpense").
                    replace("%extraExpense", String.valueOf(totalExtraExpenseToPay)));
            generalActionController.updatePlayerBalanceInclGui(guiController,player,-totalExtraExpenseToPay);


        }
    }
    
    /*
    ---------------------- Support Methods ----------------------
     */


}
