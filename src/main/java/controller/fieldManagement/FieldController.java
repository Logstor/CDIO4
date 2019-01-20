package controller.fieldManagement;

import controller.GeneralActionController;
import controller.GuiController;
import controller.fieldManagement.fieldActions.*;
import model.board.Board;
import model.board.Field;
import model.board.fields.PropertyField;
import model.chancecard.Deck;
import model.cup.Cup;
import model.player.Player;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Alfred Röttger Rydahl
 */
public class FieldController {

	/*
    ---------------------------------- Fields ----------------------------------
     */
	private GuiController guiController;
	private HashMap<String, String> messageMap;
	private Cup cup;
	private GeneralActionController generalActionController;
	private Board board;
	private Deck deck;
	private Player[] players;

    /*
    ------------------------------ Constructors --------------------------------
     */
	
	public FieldController ( Board board, Player[] players, Deck deck,GuiController guiController,HashMap<String, String> messageMap,
							 Cup cup, GeneralActionController generalActionController) {
		this.players = players;
		this.guiController = guiController;
		this.messageMap = messageMap;
		this.cup = cup;
		this.generalActionController = generalActionController;
		this.board = board;
		this.deck = deck;
	}

    /*
    ------------------------------ Properties ----------------------------------
     */

    public void doFieldActionByFieldType(Player player, Field currentField) {

        //region Find Field Type

        switch (currentField.getFieldType())
		{
            case Property:
				PropertyAction propertyAction = new PropertyAction(player, players, messageMap, (PropertyField)currentField,
						guiController, generalActionController);
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
				BoatAction boatAction = new BoatAction(player, messageMap, guiController,generalActionController, currentField);
                boatAction.action();
                break;
                
            case Parking:
            	ParkingAction parkingAction = new ParkingAction(player,messageMap,guiController);
            	parkingAction.action();
                break;
                
            case Brewery:
				BreweryAction breweryAction = new BreweryAction(player, messageMap, guiController, cup,
						currentField, generalActionController);
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
