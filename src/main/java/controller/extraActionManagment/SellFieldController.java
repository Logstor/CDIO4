package controller.extraActionManagment;

import controller.GeneralActionController;
import controller.GuiController;
import model.board.Board;
import model.board.Field;
import model.board.FieldTypeEnum;
import model.player.Player;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Rasmus Sander Larsen
 * @date 17-01-2019
 */
public class SellFieldController {

    /*
    -------------------------- Fields --------------------------
     */
    
    private ArrayList<Field> fieldsToSell;
    
    /*
    ----------------------- Constructor -------------------------
     */
    
    public SellFieldController () {

        fieldsToSell = new ArrayList<>();

    }
    
    /*
    ------------------------ Properties -------------------------
     */

    //region Properties
    
    //endregion
    
    /*
    ---------------------- Public Methods -----------------------
     */
    
    public void sellField (Player seller, Player[] players, Board board, GuiController guiController, HashMap<String,String> messageMap,
                           GeneralActionController generalActionController) {
        
        // Check if the owns any Fields at all
        if (generalActionController.checkIfPlayerOwnsAnyFieldsAndAddsThemToArrayList(seller,fieldsToSell))
        {

            if (guiController.getLeftButtonPressed(messageMap.get("WantToSellFields?"),
                    messageMap.get("Yes"),messageMap.get("No")))
            {

                String nameOnChosenField = guiController.getUserChoiceFields(messageMap.get("WhichFieldToSellField?"),
                        fieldsToSell);

                // Finds the field the Player wises to sell.
                Field[] fieldForSellArray = new Field[1];
                for (Field f :fieldsToSell) {
                    if (f.getFieldName().equals(nameOnChosenField)){
                        fieldForSellArray[0] =f;
                    }
                }
                Field fieldForSell = fieldForSellArray[0];

                // Finds names on possible buyers
                ArrayList<String> possibleBuyers = new ArrayList<>();
                for (Player p : players) {
                    if (!seller.getName().equals(p.getName())){
                        possibleBuyers.add(p.getName());
                    }
                }

                // Asks who to sell the Property for?
                String nameOnBuyer = guiController.getUserChoice(messageMap.get("WhoToSellTo?").replace("%fielName",
                        fieldForSell.getFieldName()),possibleBuyers);

                // Finds the Player who is the buyer.
                Player buyer = null;
                for (Player p : players) {
                    if (p.getName().equals(nameOnBuyer)) {
                        buyer=p;
                    }
                }

                // Finds the sell Price
                int sellPrice = guiController.getUserInteger(messageMap.get("WhatIsSellPrice?").
                        replace("%buyer", buyer.getName()).replace("%fieldName",fieldForSell.getFieldName()),
                        0,buyer.getAccount().getBalance());

                guiController.showMessage(messageMap.get("FullSellDesc").replace("%seller", seller.getName())
                .replace("%fieldName",fieldForSell.getFieldName())
                        .replace("%sellPrice", String.valueOf(sellPrice)));


                setNewFieldOwner(seller,buyer,fieldForSell,sellPrice,guiController,generalActionController);

            }
        }
    }
    
    /*
    ---------------------- Support Methods ----------------------
     */

    /**
     * Runs all the changes in Players & GUI_Players involved in the sale and the Field & GUI_Field.
     * @param seller The Player that is selling the Field.
     * @param buyer The Player who buys the Field.
     * @param fieldForSale The Field that is for sale.
     * @param sellPrice The price of the Field as an positive int.
     * @param guiController The GuiController.
     * @param generalActionController The GeneralActionController.
     */
    private void setNewFieldOwner(Player seller, Player buyer, Field fieldForSale, int sellPrice, GuiController guiController,
                                  GeneralActionController generalActionController)
    {

        // General change in values.
        // GuiPlayers Updates.
        generalActionController.updatePlayerBalanceInclGui(guiController,seller,sellPrice);
        generalActionController.updatePlayerBalanceInclGui(guiController,buyer,-sellPrice);

        // Adds and remove FieldForSell from Player.OwnedFields incl. noOfBoats and noOfBreweries.
        seller.removeFieldFromOwnedFields(fieldForSale);
        buyer.addFieldToOwnedFields(fieldForSale);
        
        //region Handle Counters in Player object
        if (fieldForSale.getFieldType() == FieldTypeEnum.Boat)
        {
            seller.updateNoOfBoatsOwned(-1);
            buyer.updateNoOfBoatsOwned(1);
        }
        else if (fieldForSale.getFieldType() == FieldTypeEnum.Brewery) {
            seller.updateNoOfBreweriesOwned(-1);
            buyer.updateNoOfBreweriesOwned(1);
        }
        //endregion

        // Field and GUI_Field Updates.
        fieldForSale.setFieldOwner(buyer);
        guiController.setOwner(buyer,fieldForSale);
        guiController.setDottedBorderWithPlayerCarColor(buyer,fieldForSale);

    }


}
