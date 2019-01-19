package controller;

import controller.extraActionManagment.ExtraActionController;
import model.board.Board;
import model.board.Field;
import model.board.FieldTypeEnum;
import model.board.fields.PropertyField;
import model.chancecard.Deck;
import model.cup.Cup;
import model.player.Player;
import view.gui.Gui;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Alfred Röttger Rydahl
 */

public class MainControl {

    /*
    --------------------------- Fields ---------------------------
     */
    private Gui gui;
    private GuiController guiController;
    private Board board;
    private Player[] players;
    private Cup cup;
    private Deck deck;

    private TurnController turnController;
    private ExtraActionController extraActionController;
    private GeneralActionController generalActionController;
    private HashMap<String, String> messageMap;

    /*
    ------------------------ Constructors ------------------------
     */
    
    public MainControl ()
	{
		board = new Board();
		deck = new Deck();
    	messageMap = new HashMap<>();
    	cup = new Cup();

	}
    
    /*
    ----------------------- Public Methods -----------------------
     */
	
	/**
	 * This method starts the game.
	 * @return Returns non-zero if an unexpected shutdown happens.
	 */
	public int letsFuckingGo()
	{

		try
		{
			// Set the game up, and display
			setup();

			//region GameLoop
			do {
                for (Player currentPlayer : players) {

                    turn(currentPlayer);

                    extraActions(currentPlayer);

                    /*
                	// Check if currentPlayer is in prison
                	if ( currentPlayer.getPrisonStat() > 0 )
						prisonTurn(currentPlayer);
                	
                	// Otherwise run a normal turn
                	else
                		turn(currentPlayer);
                		*/
                }

                // Clean a player if hasLost=True.
                cleanFaliitPlayers(players);

            }
			// Continue while there's more than 1 player left
			while (players.length>1);
			
			//endregion

			return 0;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return -1;
		}
	}
    /*
    ----------------------- Support Methods ----------------------
     */
	
	/**
	 * This method sets the board, and takes care of getting information from user.
	 */
	private void setup ()
	{
		// Instantiate everything
		
		// Creating the board with all Fields
		SetupControl setupControl = new SetupControl();
		setupControl.boardCreator(board);
		setupControl.deckCreator(deck);
		setupControl.messageMapCreator(messageMap);
		gui = new Gui(board.getBoard());
		guiController = new GuiController(gui);
		
		players = setupControl.playerSetup(guiController, messageMap);
		setupControl.createGUIPlayers(guiController,players);
		guiController.showMessage(messageMap.get("GetReady"));

		//TODO: Er det her okay? :D // RSL
		generalActionController = new GeneralActionController();
		extraActionController = new ExtraActionController(players,board,guiController,messageMap,generalActionController);
		turnController = new TurnController(guiController, board, players, cup, deck, messageMap,extraActionController);

	}

	private void turn (Player player)
	{
	    turnController.playTurn(player);
    }


	/**
	 *
	 * @param player
	 */
	private void prisonTurn (Player player)
	{
		turnController.playPrisonTurn(player);
	}

	private void cleanFaliitPlayers (Player[] players) {
	    for (Player player : players) {
	        if (player.isHasLost()){
                    cleanAFallitPlayer(player);
                    ;
            }
        }
    }

    private void cleanAFallitPlayer (Player player) {

        ArrayList<Field> fieldsToRemove = new ArrayList<>();
        for(Field field: player.getOwnedFields()){
            guiController.clearFieldForInfo(field);
            field.setFieldOwner(null);
            if (field.getFieldType() == FieldTypeEnum.Property) {
                guiController.setHousesAndHotels(0, field);
            }
            ((PropertyField)field).setNoOfHousesOnProperty(0);
            fieldsToRemove.add(field);
        }
        // Removes field from Player
        for (Field fieldToRemove : fieldsToRemove) {
            player.removeFieldFromOwnedFields(fieldToRemove);
        }
    }

    /**
     * Does ExtraActions if it is valid.
     * @param currentPlayer The Player.
     */
	private void extraActions (Player currentPlayer) {
        if (extraActionController.isExtraActionsValid(currentPlayer)){
            extraActionController.doExtraAction(currentPlayer);
        }
    }
}
