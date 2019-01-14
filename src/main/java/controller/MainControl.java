package controller;

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

			do {
                for (Player currentPlayer : players) {

                    turn(currentPlayer);

                }
            } while (players.length>1);

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

	private void turn (Player player) {
	    TurnController turnController = new TurnController();
	    turnController.playTurn(player, guiController, messageMap, board, cup);
    }
}
