package controller.fieldManagement.fieldActions;

import controller.GeneralActionController;
import controller.GuiController;
import controller.fieldManagement.FieldAction;
import model.board.Field;
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
	 *
	 * @param player
	 * @param messageMap
	 * @param field
	 * @param guiController
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
			generalAction.buyField(player, field, guiController);
		}
		//endregion
		
		//region Pay Rent
		else
		{
			guiController.showMessage(messageMap.get("PropertyFirst").replace("%name", field.getFieldOwner().getName()));
			
			// Update both players balance
			field.getFieldOwner().updateBalance(field.getFieldRent());
			guiController.updateBalance(field.getFieldOwner(), field.getFieldOwner().getAccount().getBalance());
			
			player.updateBalance(-field.getFieldRent());
			guiController.updateBalance(player, player.getAccount().getBalance());
		}
		//endregion
	}

    /*
    ----------------------------- Support Methods ------------------------------
     */
}
