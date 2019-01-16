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
		StringBuilder actionBuilder = new StringBuilder();
		if (currentField.getFieldOwner() == null) {
			actionBuilder.append(messageMap.get("LandedOnBrewery").replace("%brewery", currentField.getFieldName()));
			actionBuilder.append(messageMap.get("BuyBrewery").replace("%cost", String.valueOf(currentField.getFieldCost())));

			if (guiController.getLeftButtonPressed(actionBuilder.toString(), messageMap.get("Yes"), messageMap.get("No"))) {
				generalActionController.buyField(player, currentField, guiController);
			}
		} else {
			int rentFromCupValue = rentFromCupValue(player, cup);
			generalActionController.payManuelRent(player, rentFromCupValue, currentField, guiController, messageMap);
		}

	}

    /*
    ----------------------------- Support Methods ------------------------------
     */

	private int rentFromCupValue (Player player, Cup cup) {
		int rentFromCupValue;
		switch (player.getNoOfBreweriesOwned()) {
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
