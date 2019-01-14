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

	protected GuiController guiController;
	protected Player player;
	protected HashMap<String, String> messageMap;
    
    /*
    ------------------------------ Constructors --------------------------------
     */

	public FieldAction(GuiController guiController, Player player, HashMap<String, String> messageMap) {
		this.guiController = guiController;
		this.player = player;
		this.messageMap = messageMap;
	}

	/*
    ------------------------------ Properties ----------------------------------
     */
    
    /*
    ---------------------------- Public Methods --------------------------------
     */

    public abstract void action ();
    
    /*
    ----------------------------- Support Methods ------------------------------
     */
}
