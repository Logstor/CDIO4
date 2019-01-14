package controller.fieldManagement.fieldActions;

import controller.GuiController;
import controller.fieldManagement.FieldAction;
import model.board.Field;
import model.player.Player;

import java.util.HashMap;

/**
 * @author Alfred Röttger Rydahl
 */
public class BreweryAction extends FieldAction {

	/*
    ---------------------------------- Fields ----------------------------------
     */
    
    /*
    ------------------------------ Constructors --------------------------------
     */

	public BreweryAction(GuiController guiController, Player player, HashMap<String, String> messageMap) {
		super(player,messageMap,guiController);
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
