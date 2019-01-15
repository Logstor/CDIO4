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
	public PropertyAction(Player player, HashMap<String, String> messageMap, PropertyField field, GuiController guiController) {
		super(player,messageMap,guiController);
		this.field = field;
		this.generalAction = new GeneralActionController();
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
			// Ask the user if he wants to buy the Property
			String choice = guiController.getUserButton2(messageMap.get("PropertyWantToBuy").replace("%fieldName", field.getFieldName()),
					"Ja", "Nej");
			
			if (choice.equals("Ja"))
			{
				generalAction.buyField(player, field, guiController);
			}
			else
			{ }
		}
		//endregion
		
		//region Pay Rent
		else
		{
			guiController.showMessage(messageMap.get("PropertyFirst").replace("%name", field.getFieldOwner().getName()));
			
			// Update both players balance
			generalAction.payPropertyRent(player, field, guiController);
		}
		//endregion
	}

    /*
    ----------------------------- Support Methods ------------------------------
     */
}
