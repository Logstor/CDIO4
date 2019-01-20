package controller.extraActionManagment.extraActions;

import controller.GeneralActionController;
import controller.GuiController;
import controller.extraActionManagment.ExtraAction;
import controller.extraActionManagment.ExtraActionType_Enum;
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
public class SellFieldAction extends ExtraAction {

    /*
    -------------------------- Fields --------------------------
     */

    private ArrayList<Player> playerArrayList;
    
    /*
    ----------------------- Constructor -------------------------
     */
    
    public SellFieldAction(ArrayList<Player> playerArrayList,
                           GuiController guiController, HashMap<String,String> messageMap,
                           GeneralActionController generalActionController) {
        super(guiController, messageMap, generalActionController);
        extraActionType = ExtraActionType_Enum.SellField;
        this.playerArrayList = playerArrayList;
    }
    
    /*
    ------------------------ Properties -------------------------
     */

    //region Properties
    
    //endregion
    
    /*
    ---------------------- Public Methods -----------------------
     */
    
    public void doExtraAction (Player player) {


        if (guiController.getLeftButtonPressed(messageMap.get("WantToSellFields?"),
                messageMap.get("Yes"),messageMap.get("No"))) {

            // Runs Sell Logic
            sellField(player);
        }
    }

    public void forceSellField (Player player) {

         // This there is anyone who wishes to buy any of Players Fields. Start SellField and do it.
         sellField(player);

    }

    /**
     * Is true if currentPlayer owns 1 or more fields.
     * @return false if Player OwnedFields == 0 else true.
     */
    public boolean checkIfValidForSellField (Player currentPlayer) {
        return currentPlayer.getOwnedFields().size()!=0;
    }

    /*
    ---------------------- Support Methods ----------------------
     */

    public void sellField (Player currentPlayer) {

        // Adds players owned fields to List af fieldsToSell.
        ArrayList<Field>  fieldsToSell = currentPlayer.getOwnedFields();

        // Asks which field the player wises to sell. DropDownMenu.
        String nameOnChosenField = guiController.getUserChoiceFields(messageMap.get("WhichFieldToSellField?"),
                fieldsToSell);

        //region Find the field the Player wises to sell.
        Field[] fieldForSellArray = new Field[1];
        for (Field f :fieldsToSell) {
            if (f.getFieldName().equals(nameOnChosenField)){
                fieldForSellArray[0] =f;
            }
        }
        Field fieldForSell = fieldForSellArray[0];
        //endregion

        //region Finds names on possible buyers
        ArrayList<String> possibleBuyers = new ArrayList<>();
        for (Player p : playerArrayList) {
            if (p.getAccount().getBalance()>=0){
                if (!currentPlayer.getName().equals(p.getName())){
                    possibleBuyers.add(p.getName());
                }
            }
        }
        //endregion

        //region Asks who to sell the Property for?
        String nameOnBuyer = guiController.getUserChoice(messageMap.get("WhoToSellTo?")
                .replace("%fieldName",fieldForSell.getFieldName()),possibleBuyers);

        //endregion

        //region Finds the Player who is the buyer.
        ArrayList<Player> THEBUYER = new ArrayList<>();
        for (Player p : playerArrayList) {
            if (p.getName().equals(nameOnBuyer)) {
                THEBUYER.add(p);
            }
        }
        Player buyingPlayer = THEBUYER.get(0);
        //endregion

        //region Finds the sell Price
        int sellPrice = guiController.getUserInteger(messageMap.get("WhatIsSellPrice?").
                        replace("%buyer", nameOnBuyer).
                        replace("%fieldName", fieldForSell.getFieldName()),
                0,buyingPlayer.getAccount().getBalance()); //
        //endregion

        //region Show Message of fully described Sale
        guiController.showMessage(messageMap.get("FullSellDesc").replace("%seller", currentPlayer.getName())
                .replace("%fieldName",fieldForSell.getFieldName())
                .replace("%sellPrice", String.valueOf(sellPrice))
                .replace("%buyer", buyingPlayer.getName()));
        //endregion

        //region Sets the Buyer as new owner of Field and GUI_field.
        setNewFieldOwner(currentPlayer,buyingPlayer,fieldForSell,sellPrice,guiController,generalActionController);
        //endregion
    }

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
        //region GuiPlayers Updates.
        generalActionController.updatePlayerBalanceInclGui(guiController,seller,sellPrice);
        generalActionController.updatePlayerBalanceInclGui(guiController,buyer,-sellPrice);
        //endregion

        //region Adds and remove FieldForSell from Player.OwnedFields incl. noOfBoats and noOfBreweries.
        seller.removeFieldFromOwnedFields(fieldForSale);
        buyer.addFieldToOwnedFields(fieldForSale);
        //endregion

        //region Handle noOfBoats and noOfBreweries in Player object
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

        //region Field and GUI_Field Updates.
        fieldForSale.setFieldOwner(buyer);
        guiController.setOwner(buyer,fieldForSale);
        guiController.setDottedBorderWithPlayerCarColor(buyer,fieldForSale);
        //endregion
    }

}
