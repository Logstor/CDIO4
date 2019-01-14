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
	private HashMap<String, String> messageMap;
    
    /*
    ------------------------------ Constructors --------------------------------
     */

	public PropertyAction(GuiController guiController, Player player, PropertyField field,
						  HashMap<String, String> messageMap)
	{
		super(guiController, player);
		this.field = field;
		this.messageMap = messageMap;
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
