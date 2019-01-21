package controller.fieldManagement.fieldActions;

import controller.GuiController;
import controller.fieldManagement.FieldAction;
import model.board.Field;
import model.player.Player;

import java.util.HashMap;

/**
 * @author Alfred Röttger Rydahl
 */
public class TaxAction extends FieldAction {

	/*
    ---------------------------------- Fields ----------------------------------
     */

	private Field currentField;

    /*
    ------------------------------ Constructors --------------------------------
     */

	public TaxAction(Player player, HashMap<String, String> messageMap,GuiController guiController, Field currentField) {
		super(player, messageMap, guiController);
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
		StringBuilder actionBuilder = new StringBuilder();

		if (currentField.getFieldNo()== 39) {
			taxStandardAction(actionBuilder,"LandedOnTax39");
		} else {
			taxStandardAction(actionBuilder,"LandedOnTax5");
		}
	}

    /*
    ----------------------------- Support Methods ------------------------------
     */

    private void taxStandardAction (StringBuilder actionBuilder,String stringLabel) {
		actionBuilder.append(messageMap.get(stringLabel).replace("%fieldName", currentField.getFieldName())
				.replace("%rent",String.valueOf(currentField.getFieldCost())) + "\n");
		player.updateBalance(-currentField.getFieldCost());
		guiController.updateBalance(player,player.getAccount().getBalance());
		guiController.showMessage(actionBuilder.toString());
	}
}
