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
		HashMap<String, String> boardMap = new HashMap<>(40);
		Reader reader = new Reader("board1.4_Packed.csv", ";");
		
		reader.readFileIntoHashMap(boardMap);
		
		BoardManager boardManager = new BoardManager();
		
		board = new Board();
		boardManager.setupBoard(boardMap, board);
		
		gui = new Gui(board.getBoard());
		//endregion
	}
    /*
    ----------------------- Support Methods ----------------------
     */
}
