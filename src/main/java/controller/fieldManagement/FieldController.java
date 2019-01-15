package controller.fieldManagement;

import controller.GeneralActionController;
import controller.GuiController;
import controller.fieldManagement.fieldActions.*;
import model.board.Board;
import model.board.Field;
import model.board.fields.PrisonField;
import model.board.fields.PropertyField;
import model.chancecard.Deck;
import model.cup.Cup;
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
	private Cup cup;
	private GeneralActionController generalActionController;
	private Board board;
	private Deck deck;
    /*
    ------------------------------ Constructors --------------------------------
     */
	
	public FieldController(Field currentField, GuiController guiController, Player player, Board board, Deck deck,
						   HashMap<String, String> messageMap, Cup cup, GeneralActionController generalActionController) {
		this.currentField = currentField;
		this.guiController = guiController;
		this.player = player;
		this.messageMap = messageMap;
		this.cup = cup;
		this.generalActionController = generalActionController;
		this.board = board;
		this.deck = deck;
	}
	
	//TODO: Hvis vi vil have FieldController til at eksisterer gennem hele spillet, så skal der laves objekter af
	//TODO: alle Action klasserne. De skal da initialiseres i constructoren og tilføjes, som attributer.

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
            	ChanceAction chanceAction = new ChanceAction(player,messageMap,guiController,generalActionController,
						board, deck);
            	chanceAction.action();
                break;
                
            case Tax:
				TaxAction taxAction = new TaxAction(player ,messageMap, guiController, currentField);
                taxAction.action();
                break;
                
            case Prison:
            	PrisonAction prisonAction = new PrisonAction(player, currentField, messageMap, guiController);
            	prisonAction.action();
                break;
                
            case Boat:
				BoatAction boatAction = new BoatAction(player, messageMap, guiController, currentField);
                boatAction.action();
                break;
                
            case Parking:
                break;
                
            case Brewery:
				BreweryAction breweryAction = new BreweryAction(player, messageMap, guiController, cup, currentField);
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
