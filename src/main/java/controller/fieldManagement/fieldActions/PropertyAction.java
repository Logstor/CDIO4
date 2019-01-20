package controller.fieldManagement.fieldActions;

import controller.GeneralActionController;
import controller.GuiController;
import controller.fieldManagement.FieldAction;
import model.board.fields.PropertyField;
import model.player.Player;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Alfred Röttger Rydahl
 */
public class PropertyAction extends FieldAction {

	/*
    ---------------------------------- Fields ----------------------------------
     */

	private PropertyField field;
	private GeneralActionController generalActionController;
	private Player[] players;
    
    /*
    ------------------------------ Constructors --------------------------------
     */
	
	/**
	 * Primary Constructor
	 * @param player The Player who landed on the PropertyField.
	 * @param messageMap The HashMap holding all messages.
	 * @param field The PropertyField the player landed on.
	 * @param guiController The GuiController.
	 */
	public PropertyAction(Player player, Player[] players, HashMap<String, String> messageMap, PropertyField field, GuiController guiController,
						  GeneralActionController generalActionController)
	{
		super(player,messageMap,guiController);
		this.players = players;
		this.field = field;
		this.generalActionController = generalActionController;
	}

	/*
    ------------------------------ Properties ----------------------------------
     */
    
    /*
    ---------------------------- Public Methods --------------------------------
     */
	
	/**
	 * This method handles all events. Buying, Sale and Rent.
	 */
	@Override
	public void action() {

		// Check if the currentPlayer doesn't own the Property
		if ( field.getFieldOwner() !=  player)
		{
			//region Buying Sequence
			if (field.getFieldOwner() == null) {
				buyingSequence();
			}
			//endregion

			//region Pay Rent
			else {
				// Handle the rent event, and display messages
				generalActionController.payPropertyRent(player, field, guiController, messageMap);
			}
			//endregion
		}

		// Otherwise he owns the property
		else
			guiController.showMessage(messageMap.get("LandedOnOwnField"));
	}

    /*
    ----------------------------- Support Methods ------------------------------
     */

	/**
	 *
	 */
	private void buyingSequence()
	{
		// Make
		if ( (player.getAccount().getBalance() >= field.getFieldCost()))
		{

			// Ask the player if he wants to buy, and handle the event
			if (guiController.getLeftButtonPressed(messageMap.get("PropertyWantToBuy").replace("%fieldName",
					field.getFieldName()),
					messageMap.get("Yes"), messageMap.get("No")))
			{
				generalActionController.buyField(player, field, guiController);
			}

			// Otherwise, player don't want to
			else
			{
				//region auction
				auctionField(field , players, generalActionController);
				//endregion
			}
		}

		// Otherwise put it on auction
		else
		{
			guiController.showMessage(messageMap.get("PropertyNoMoney"));

			//region auction
			auctionField(field,players, generalActionController);
			//endregion
		}
	}
}
