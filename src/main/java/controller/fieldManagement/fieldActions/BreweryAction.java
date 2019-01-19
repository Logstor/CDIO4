package controller.fieldManagement.fieldActions;

import controller.GeneralActionController;
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
	private GeneralActionController generalActionController;
    /*
    ------------------------------ Constructors --------------------------------
     */

	public BreweryAction(Player player, HashMap<String, String> messageMap,GuiController guiController,
						 Cup cup, Field currentField, GeneralActionController generalActionController) {
		super(player,messageMap,guiController);
		this.cup = cup;
		this.currentField = currentField;
		this.generalActionController = generalActionController;
	}

	/*
    ------------------------------ Properties ----------------------------------
     */


    
    /*
    ---------------------------- Public Methods --------------------------------
     */

	@Override
	public void action() {

		// Checks that Player doesn't own the field.
		if (currentField.getFieldOwner() != player) {

			//region Buying Sequence
			// Checks if the field is for sell and runs BuyingSequence if true.
			if (currentField.getFieldOwner() == null) {

				buyingSequence();

			}
			//endregion.

			//region Pay Rent Sequence
			// If Field has an owner, runs PayRentSequence.
			else {
				payRentFromCupValueAndNoOfBreweries();
			}
			//endregion

		}
		// Otherwise he owns the property
		else {
			guiController.showMessage(messageMap.get("LandedOnOwnField"));
		}
	}

    /*
    ----------------------------- Support Methods ------------------------------
     */

    private void buyingSequence () {
    	StringBuilder actionBuilder = new StringBuilder();
		actionBuilder.append(messageMap.get("LandedOnBrewery").replace("%brewery", currentField.getFieldName()));
		actionBuilder.append(messageMap.get("BuyBrewery").replace("%cost", String.valueOf(currentField.getFieldCost())));

		if (guiController.getLeftButtonPressed(actionBuilder.toString(), messageMap.get("Yes"), messageMap.get("No"))) {
			generalActionController.buyField(player, currentField, guiController);
		}
	}

	private void payRentFromCupValueAndNoOfBreweries () {
		generalActionController.payManuelRent(player, rentFromCupValue(), currentField, guiController, messageMap);
	}

	/**
	 * Calculates the rent from Cup.CupValue and number of owned Breweries.
	 * @return The calculated rent.
	 */
	private int rentFromCupValue () {
		int rentFromCupValue;
		switch (currentField.getFieldOwner().getNoOfBreweriesOwned()) {
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

}
