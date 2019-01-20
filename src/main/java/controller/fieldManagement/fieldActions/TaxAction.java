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


    /*
    int totalPlayerValue = player.calPlayerTotalValue();

    if (totalPlayerValue <= 40000) {
				// TODO: JEG KAN IKKE FINDE UD AF HVORNÅR MAN SKAL HAVE MULIGHEDEN FOR AT BETALE 10% I SKAT,
				// TODO: ELLER SKAL DE BETALES 4000. (Jeg ville gætte på at man måske kunne have den under startfield)
				int taxUnder = (int) ((totalPlayerValue*0.1)-((totalPlayerValue*0.1)%100));

				actionBuilder.append(messageMap.get("LandedOnTaxUnder").replace("%fieldName", currentField.getFieldName()));
				actionBuilder.append(messageMap.get("TaxToPay").replace("%tax",String.valueOf(taxUnder)));
				player.updateBalance(-taxUnder);
				guiController.updateBalance(player, player.getAccount().getBalance());
				guiController.showMessage(actionBuilder.toString());
			} else {
				actionBuilder.append(messageMap.get("LandedOnTaxOver").replace("%fieldName", currentField.getFieldName()) +"\n");
				actionBuilder.append(messageMap.get("TaxToPay").replace("%rent", String.valueOf(currentField.getFieldCost())));
				player.updateBalance(-currentField.getFieldCost());
				guiController.updateBalance(player, player.getAccount().getBalance());
				guiController.showMessage(actionBuilder.toString());
			}
     */
}
