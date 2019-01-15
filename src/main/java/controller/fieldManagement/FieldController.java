package controller.fieldManagement;

import controller.GuiController;
import controller.fieldManagement.fieldActions.*;
import model.board.Field;
import model.board.fields.BoatField;
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
	private GuiController guiController;
	private Player player;
	private HashMap<String, String> messageMap;
    
    /*
    ------------------------------ Constructors --------------------------------
     */
	
	public FieldController(Field currentField, GuiController guiController, Player player, HashMap<String, String> messageMap)
	{
		this.currentField = currentField;
		this.guiController = guiController;
		this.player = player;
		this.messageMap = messageMap;
		
		//TODO: Hvis vi vil have FieldController til at eksisterer gennem hele spillet, så skal der laves objekter af
		//TODO: alle Action klasserne. De skal da initialiseres i constructoren og tilføjes, som attributer.
	}

    /*
    ------------------------------ Properties ----------------------------------
     */

    public void doFieldActionByFieldType() {

        //region Find Field Type

        switch (currentField.getFieldType())
		{
            case Property:
				PropertyAction propertyAction = new PropertyAction(player, messageMap, (PropertyField)currentField, guiController);
				propertyAction.action();
                break;
                
            case Start:
				StartAction startAction = new StartAction(player, messageMap, guiController);
				startAction.action();
                break;
                
            case ChanceCard:
                break;
                
            case Tax:
				TaxAction taxAction = new TaxAction(player ,messageMap, guiController, currentField);
                taxAction.action();
                break;
                
            case Prison:
                break;
                
            case Boat:
				BoatAction boatAction = new BoatAction(player, messageMap, guiController, currentField);
                boatAction.action();
                break;
                
            case Parking:
                break;
                
            case Brewery:
				BreweryAction breweryAction = new BreweryAction(player, messageMap, guiController);
				breweryAction.action();
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
