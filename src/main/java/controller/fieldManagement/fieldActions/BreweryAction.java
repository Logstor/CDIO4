package controller.fieldManagement.fieldActions;

import controller.GuiController;
import controller.fieldManagement.FieldAction;
import model.board.Field;
import model.cup.Cup;
import model.player.Player;

import java.util.HashMap;

/**
 * @author Alfred Röttger Rydahl
 */
public class BreweryAction extends FieldAction {

	/*
    ---------------------------------- Fields ----------------------------------
     */

	private Cup cup;
	private Field currentField;
    /*
    ------------------------------ Constructors --------------------------------
     */

	public BreweryAction(Player player, HashMap<String, String> messageMap,GuiController guiController,
						 Cup cup, Field currentField) {
		super(player,messageMap,guiController);
		this.cup = cup;
		this.currentField = currentField;
	}

	/*
    ------------------------------ Properties ----------------------------------
     */
    
    /*
    ---------------------------- Public Methods --------------------------------
     */

	@Override
	public void action() {
		if (currentField.getFieldOwner() == null) {
			buyField(player, currentField, guiController);
		} else {
			int rentFromCupValue = rentFromCupValue(player, cup);
			payManuelRent(player, rentFromCupValue, currentField, guiController, messageMap);
		}

	}

    /*
    ----------------------------- Support Methods ------------------------------
     */

	private int rentFromCupValue (Player player, Cup cup) {
		int rentFromCupValue;
		switch (player.getBreweriesOwned()) {
			case 1:
				rentFromCupValue = 100*cup.getCupValue();
				break;
			case 2:
				rentFromCupValue = 200*cup.getCupValue();
				break;
			default:
				rentFromCupValue = 0;
				break;
		}
		return rentFromCupValue;
	}
	private void buyField (Player player, Field currentField, GuiController guiController) {

		currentField.setFieldOwner(player);
		player.updateBalance(-currentField.getFieldCost());
		guiController.updateBalance(player, player.getAccount().getBalance());
		guiController.setOwner(player,currentField);

	}

	private void payManuelRent (Player player, int manuelRent, Field currentField, GuiController guiController,
								HashMap<String,String> messageMap) {

		player.updateBalance(-manuelRent);
		currentField.getFieldOwner().updateBalance(manuelRent);
		guiController.showMessage(messageMap.get("PayRentTo") + " " +currentField.getFieldOwner().getName() + ".\n" +
				messageMap.get("RentIs")+ " " + manuelRent);
	}

}
