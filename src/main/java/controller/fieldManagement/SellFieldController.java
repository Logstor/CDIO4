package controller.fieldManagement;

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

    // <editor-folder desc="Properties"


    // </editor-folder>
    
    /*
    ---------------------- Public Methods -----------------------
     */
    
    public void sellField (Player seller, Player[] players, Board board, GuiController guiController, HashMap<String,String> messageMap,
                           GeneralActionController generalActionController) {

        if (generalActionController.checkIfPlayerOwnsAnyFieldsAndAddsThemToArrayList(seller,fieldsToSell)){

            if (guiController.getLeftButtonPressed(messageMap.get("WantToSellFields?"),
                    messageMap.get("Yes"),messageMap.get("No"))){

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
                Player buyer =null;
                for (Player p : players) {
                    if (p.getName().equals(nameOnBuyer)){
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
     * Runs all the changes in Players & GUI_Players involved in the sale and the Field & GUI_Field
     * @param seller
     * @param buyer
     * @param fieldForSell
     * @param sellPrice
     * @param guiController
     * @param generalActionController
     */
    private void setNewFieldOwner(Player seller, Player buyer, Field fieldForSell, int sellPrice, GuiController guiController,
                                  GeneralActionController generalActionController) {

        // General change in values.
        // GuiPlayers Updates.
        generalActionController.updatePlayerBalanceInclGui(guiController,seller,sellPrice);
        generalActionController.updatePlayerBalanceInclGui(guiController,buyer,-sellPrice);

        // Adds and remove FieldForSell from Player.OwnedFields incl. noOfBoats and noOfBreweries.
        seller.removeFieldFromOwnedFields(fieldForSell);
        buyer.addFieldToOwnedFields(fieldForSell);
        if (fieldForSell.getFieldType().equals(FieldTypeEnum.Boat)) {
            seller.updateNoOfBoatsOwned(-1);
            buyer.updateNoOfBoatsOwned(1);
        }
        if (fieldForSell.getFieldType().equals(FieldTypeEnum.Brewery)) {
            seller.updateNoOfBreweriesOwned(-1);
            buyer.updateNoOfBreweriesOwned(1);
        }

        // Field and GUI_Field Updates.
        fieldForSell.setFieldOwner(buyer);
        guiController.setOwner(buyer,fieldForSell);
        guiController.setDottedBorderWithPlayerCarColor(buyer,fieldForSell);

    }


}
