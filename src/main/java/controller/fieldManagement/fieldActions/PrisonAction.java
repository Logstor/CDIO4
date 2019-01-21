package controller.fieldManagement.fieldActions;

import controller.GuiController;
import controller.fieldManagement.FieldAction;
import model.board.Field;
import model.player.Player;

import java.util.HashMap;

/**
 * @author Alfred Röttger Rydahl
 */
public class PrisonAction extends FieldAction {

	/*
    ---------------------------------- Fields ----------------------------------
     */

	private Field currentField;

    /*
    ------------------------------ Constructors --------------------------------
     */

	public PrisonAction(Player player, Field currentField, HashMap<String, String> messageMap,GuiController guiController)
	{
		super(player,messageMap,guiController);
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

		if (currentField.getFieldNo()==11)
		{
			if (player.getPrisonStat()==0) {
				guiController.showMessage(messageMap.get("PrisonVisiting"));
			}
		}
		
		else if(currentField.getFieldNo()==31)
		{
			guiController.showMessage(messageMap.get("PrisonSentTo"));
			player.setPrisonStat(1);
			
			// Player position is updated with "-20" to set Player position to FieldNo 11.
			player.updatePosition(-20);
			guiController.movePlayer(player, player.getPosition());
		}

	}
    /*
    ----------------------------- Support Methods ------------------------------
     */
}
