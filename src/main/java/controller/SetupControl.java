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
	private Gui gui;
	private Player[] players;
	private Cup cup;
	private Board board;
    /*
    ------------------------ Constructors ------------------------
     */
	
	/**
	 * Primary constructor for the setup control. This takes references to all the game parameters, which
	 * memory can be altered to be able to start the game.
	 * @param gui A reference to the Gui object.
	 * @param players A reference to the array of players.
	 * @param cup A reference to the Cup holding the dices.
	 * @param board A reference to the board.
	 */
	public SetupControl(Gui gui, Player[] players, Cup cup, Board board) {
		this.players = players;
		this.cup = cup;
		this.board = board;
		this.gui = gui;
	}
	
	/*
	----------------------- Public Methods -----------------------
	*/
    public void setShitUp()
	{
		//region BoardSetup
		boardSetup();
		//endregion
		
		//region SetupPlayers
		
		playerSetup();
		
		//endregion
	}
    /*
    ----------------------- Support Methods ----------------------
     */
    private void playerSetup()
	{
	
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
