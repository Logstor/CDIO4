package controller;

import controller.extraActionManagment.extraActions.BuyHousesController;
import model.board.Board;
import model.chancecard.Deck;
import model.cup.Cup;
import model.player.Player;
import view.gui.Gui;

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

    private GeneralActionController generalActionController;
    private BuyHousesController buyHousesController;
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

    	generalActionController = new GeneralActionController();
    	buyHousesController = new BuyHousesController();
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
                	
                	// Check if currentPlayer is in prison
                	if(currentPlayer.getPrisonStat() > 0)
						prisonTurn(currentPlayer);
                	
                	// Otherwise run a normal turn
                	else
                		turn(currentPlayer);
                }
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
	}

	private void turn (Player player)
	{
	    TurnController turnController = new TurnController();
	    turnController.playTurn(player, guiController, messageMap, deck, board, cup, generalActionController,
				buyHousesController);
    }
    
    private void prisonTurn (Player player)
	{
		//FIXME: Denne metode skal implementeres
		
		//TODO: Create new TurnController
		
		//TODO: Run the specific TurnController method for starting in prison
	}
}
