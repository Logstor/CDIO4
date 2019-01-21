package controller.fieldManagement;

import controller.GeneralActionController;
import controller.GuiController;
import model.board.Field;
import model.player.Player;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Alfred Röttger Rydahl
 */
public abstract class FieldAction {

	/*
    ---------------------------------- Fields ----------------------------------
     */

	protected GuiController guiController;
	protected Player player;
	protected HashMap<String, String> messageMap;
    
    /*
    ------------------------------ Constructors --------------------------------
     */

    public FieldAction(Player player, HashMap<String, String> messageMap,GuiController guiController) {
		this.player = player;
		this.messageMap = messageMap;
		this.guiController = guiController;
	}
    
    /*
    ---------------------------- Public Methods --------------------------------
     */

    public abstract void action ();
    
    /*
    ----------------------------- Support Methods ------------------------------
     */


    public void auctionField (Field currentField,Player[] players, GeneralActionController generalActionController) {

		//region Finds possible buyers for the field.
		ArrayList<String> possiblesBuyersNames = new ArrayList<>();
		for (Player buyers : players) {
			// Checks if the player has enough Money to buy the field.
			if (buyers.getAccount().getBalance()> currentField.getFieldCost()) {
				// Finds possibleBuyers for the field, that isn't the currentPlayer.
				if (!buyers.getName().equals(player.getName())) {
					possiblesBuyersNames.add(buyers.getName());
				}
			}
		}
		//endregion.

		if (possiblesBuyersNames.size()>=1) {

			//region Asks if anyone wants to buy the field if auction.
			if (guiController.getLeftButtonPressed(messageMap.get("AnyBuysAuction")
							.replace("%name", player.getName())
							.replace("%fieldName", currentField.getFieldName()),
					// Bottoms
					messageMap.get("Yes"), messageMap.get("No"))) {


				//region Asks for the name of the auctionBuyer.
				String buyersName = guiController.getUserChoice(messageMap.get("WhoIsAuctionBuyer")
						.replace("%fieldName", currentField.getFieldName()),possiblesBuyersNames);

				//endregion

				//region Finds the Player object of the buyer, from buyersName.
				ArrayList<Player> THEBUYER = new ArrayList<>();
				for (Player possibleBuyer : players) {
					if (possibleBuyer.getName().equals(buyersName)){
						THEBUYER.add(possibleBuyer);
					}
				}

				Player buyingPlayer = THEBUYER.get(0);

				//endregion

				//region Checks if player buys with monopoly money.
				boolean ValidMoney = false;
				while(!ValidMoney) {
				//region Asks that the buyer is paying for the field.
				int auctionPrice = guiController.getUserInteger(messageMap.get("WhatIsAuctionPrice")
								.replace("%buyer", buyersName)
								.replace("%fieldName", currentField.getFieldName()),
						currentField.getFieldCost(), buyingPlayer.getAccount().getBalance());
				//endregion

				//region Buyer buys the field.
					if((auctionPrice % 50) == 0) {
						ValidMoney = true;

						// Saves the original FieldCost and Sets the fieldCost temporarily to auctionPrice.
						int origianlFieldCost = currentField.getFieldCost();
						currentField.setFieldCost(auctionPrice);


						//region Final auction description
						guiController.showMessage(messageMap.get("FinalAuctionDesc")
								.replace("%fieldName", currentField.getFieldName())
								.replace("%buyerName", buyingPlayer.getName())
								.replace("%fieldName", currentField.getFieldName())
								.replace("%auctionPrice", String.valueOf(auctionPrice)));
						//endregion

				// Buying Player buys the field.
						generalActionController.buyField(buyingPlayer, currentField, guiController);

						// Resets the FieldCost to the original value.
						currentField.setFieldCost(origianlFieldCost);
				}
				else {
					guiController.showMessage(messageMap.get("InvalidValuta"));
				}

				//endregion
				}
				//endregion
			}
			//endregion

		}
	}
}
