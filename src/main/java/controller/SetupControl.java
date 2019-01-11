package controller;

import model.board.Board;
import model.board.BoardManager;
import model.cup.Cup;
import model.player.Player;
import model.reader.Reader;
import view.gui.Gui;

import java.util.HashMap;

/**
 * @author Alfred Röttger Rydahl
 */

public class SetupControl {
	
	/*
    --------------------------- Fields ---------------------------
     */
	
	//region Fields
	private Gui gui;
	private GuiController guiController;
	private Player[] players;
	private Cup cup;
	private Board board;
	
	private HashMap<String, String> map;
	//endregion
	
    /*
    ------------------------ Constructors ------------------------
     */
	
	/**
	 * Primary constructor for the setup control. This takes references to all the game parameters, which
	 * memory can be altered to be able to start the game.
	 * @param gui A reference to the Gui object.
	 * @param guiController A reference to the GuiController object, that communicates with the Gui.
	 * @param players A reference to the array of players.
	 * @param cup A reference to the Cup holding the dices.
	 * @param board A reference to the board.
	 */
	public SetupControl(Gui gui, GuiController guiController, Player[] players, Cup cup,
						Board board, HashMap<String, String> map)
	{
		this.players = players;
		this.cup = cup;
		this.board = board;
		this.gui = gui;
		this.guiController = guiController;
		this.map = map;
	}
	
	/*
	----------------------- Public Methods -----------------------
	*/
    public void setShitUp()
	{
		//region Load Messages
		
		messageLoad();
		
		//endregion
		
		//region Board Setup
		
		boardSetup();
		
		//endregion
		
		//region Start GuiController
		
		guiController = new GuiController(gui);
		
		//endregion
		
		//region Setup Players
		
		playerSetup();
		
		//endregion
		
		//region Setup Cup
		
		cup = new Cup();
		
		//endregion
	}
    /*
    ----------------------- Support Methods ----------------------
     */
	
	/**
	 * This method prints welcoming messages to the player(s)
	 */
	private void introduction()
	{
		guiController.showMessage(map.get("Welcome"));
	}
	
	/**
	 * This method is loading all messages into the HashMap
	 */
	private void messageLoad()
	{
		// Initialize the HashMap
		map = new HashMap<>();
		
		// Create Reader object
		Reader reader = new Reader("messages1,0.csv",",");
		
		// Load all messages into the HashMap
		reader.readFileIntoHashMap(map);
	}
	
	/**
	 *
	 */
	private void playerSetup()
	{
		// Welcoming the players
		introduction();
		
		// Ask how many players who wants to play
		int playerCount = guiController.getUserInteger(map.get("GetPlayers"), 3, 6);
	}
	
	/**
	 * This method takes care of setting the board and the Gui.
	 */
	private void boardSetup()
	{
		// HashMap to hold all the board info
		HashMap<String, String> boardMap = new HashMap<>(40);
		
		// Read all the information in the board CSV to the HashMap
		Reader reader = new Reader("board1.4_Packed.csv", ";");
		reader.readFileIntoHashMap(boardMap);
		
		// Initialize the board
		board = new Board();
		
		// Create BoardManager and make it set the board with the information contained in the HashMap
		BoardManager boardManager = new BoardManager();
		boardManager.setupBoard(boardMap, board);
		
		// Initialize and display the Gui
		gui = new Gui(board.getBoard());
	}
}
