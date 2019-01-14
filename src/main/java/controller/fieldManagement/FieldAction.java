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
	protected Field field;
	protected HashMap<String, String> messageMap;
    
    /*
    ------------------------------ Constructors --------------------------------
     */

	public FieldAction(GuiController guiController, Player player, Field field, HashMap<String, String> messageMap)
	{
		this.guiController = guiController;
		this.player = player;
		this.field = field;
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
