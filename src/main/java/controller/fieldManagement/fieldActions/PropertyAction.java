package controller.fieldManagement.fieldActions;

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
    
    /*
    ------------------------------ Constructors --------------------------------
     */

	public PropertyAction(Player player, HashMap<String, String> messageMap, PropertyField field, GuiController guiController) {
		super(player,messageMap,guiController);
		this.field = field;
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
