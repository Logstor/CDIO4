package controller.fieldManagement;

import controller.GuiController;
import controller.fieldManagement.fieldActions.TaxAction;
import model.board.Field;
import model.board.fields.PropertyField;
import model.player.Player;

import java.util.HashMap;

/**
 * @author Alfred Röttger Rydahl
 */
public class FieldController {

	/*
    ---------------------------------- Fields ----------------------------------
     */
	private Field currentField;
	private TaxAction taxAction;
    
    /*
    ------------------------------ Constructors --------------------------------
     */

    public FieldController (Field currentField,GuiController guiController, Player player, HashMap<String, String> messageMap) {
        this.currentField = currentField;

        taxAction = new TaxAction(player,messageMap,guiController,currentField);

    }

    /*
    ------------------------------ Properties ----------------------------------
     */

    public void doFieldAction(Player player, Field field, GuiController guiController,
                            HashMap<String, String> messageMap) {
        //region Find Field Type

        switch (field.getFieldType()) {
            case Property:
                break;
            case Start:
                break;
            case ChanceCard:
                break;
            case Tax:
                taxAction.action();
                break;
            case Prison:
                break;
            case Boat:
                break;
            case Parking:
                break;
            case Brewery:
                break;
        }
    }


    /*
    ---------------------------- Public Methods --------------------------------
     */
    
    /*
    ----------------------------- Support Methods ------------------------------
     */

}
