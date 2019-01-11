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
	private Player[] players;
	private Cup cup;
	private Board board;
    /*
    ------------------------ Constructors ------------------------
     */
	
	public SetupControl(Player[] players, Cup cup, Board board) {
		this.players = players;
		this.cup = cup;
		this.board = board;
	}
	
	/*
	----------------------- Public Methods -----------------------
	*/
    public Gui setShitUp()
	{
		HashMap<String, String> boardMap = new HashMap<>(40);
		Reader reader = new Reader("board1.4_Packed.csv", ";");
		
		reader.readFileIntoHashMap(boardMap);
		
		BoardManager boardManager = new BoardManager();
		
		board = new Board();
		boardManager.setupBoard(boardMap, board);
		
		return new Gui(board.getBoard());
	}
    /*
    ----------------------- Support Methods ----------------------
     */
}
