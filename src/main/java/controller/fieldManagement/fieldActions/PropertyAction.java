package controller.fieldManagement.fieldActions;

import controller.GeneralActionController;
import controller.GuiController;
import controller.fieldManagement.FieldAction;
import model.board.fields.PropertyField;
import model.player.Player;

import java.util.HashMap;

/**
 * @author Alfred Röttger Rydahl
 */
public class PropertyAction extends FieldAction {

	/*
    ---------------------------------- Fields ----------------------------------
     */

	private PropertyField field;
	private GeneralActionController generalAction;
    
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
	public PropertyAction(Player player, HashMap<String, String> messageMap, PropertyField field, GuiController guiController,
						  GeneralActionController generalActionController)
	{
		super(player,messageMap,guiController);
		this.field = field;
		this.generalAction = generalActionController;
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
		
		//region Buying Sequence
		if (field.getFieldOwner() == null)
		{
			// Make
			if ( (player.getAccount().getBalance() - field.getFieldCost()) > 0 )
			{
				//region Does Player Want to Buy?
				
				// Ask the user if he wants to buy the Property
				String choice = guiController.getUserButton2(messageMap.get("PropertyWantToBuy").replace("%fieldName", field.getFieldName()),
						"Ja", "Nej");
				
				//endregion
				
				if (choice.equals("Ja"))
				{
					generalAction.buyField(player, field, guiController);
				}
				
				// Otherwise, player don't want to
				else
				{
					//region auction
					
					//endregion
				}
			}
			
			// Otherwise put it on auction
			else
			{
				guiController.showMessage(messageMap.get("PropertyNoMoney"));
				
				//region auction
				
				//endregion
			}
		}
		//endregion
		
		//region Pay Rent
		else
		{
			// Handle the rent event, and display messages
			generalAction.payPropertyRent(player, field, guiController, messageMap);
		}
		//endregion
	}

    /*
    ----------------------------- Support Methods ------------------------------
     */
}
