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
    
    /*
    ------------------------------ Constructors --------------------------------
     */

	public TaxAction(GuiController guiController, Player player, Field field, HashMap<String, String> messageMap) {
		super(guiController, player, field, messageMap);
	}
    /*
    ------------------------------ Properties ----------------------------------
     */
    
    /*
    ---------------------------- Public Methods --------------------------------
     */

	@Override
	public void action() {

	}
    /*
    ----------------------------- Support Methods ------------------------------
     */
}
