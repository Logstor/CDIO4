package controller.fieldManagement;

import controller.GuiController;
import model.board.Field;
import model.player.Player;

import java.util.HashMap;

/**
 * @author Alfred Röttger Rydahl
 */
public abstract class FieldAction {

	/*
    ---------------------------------- Fields ----------------------------------
     */

	protected Field currentField;
	protected GuiController guiController;
	protected Player player;
	protected HashMap<String, String> messageMap;
    
    /*
    ------------------------------ Constructors --------------------------------
     */

    public FieldAction(Player player, HashMap<String, String> messageMap,GuiController guiController) {
		this.player = player;
		this.messageMap = messageMap;
		this.guiController = guiController;
	}
    
    /*
    ---------------------------- Public Methods --------------------------------
     */

    public abstract void action ();
    
    /*
    ----------------------------- Support Methods ------------------------------
     */
}
