package controller;

import model.board.Board;
import model.board.BoardManager;
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
    private HashMap<String, String> boardInfo;
    /*
    ------------------------ Constructors ------------------------
     */
    public SetupControl()
	{
		boardInfo = new HashMap<>();
	}
    /*
    ------------------------- Properties -------------------------
     */
    /*
    ----------------------- Public Methods -----------------------
     */
    
    public void boardCreator(Board board)
	{
		loadBoardInfo();
		
		BoardManager boardManager = new BoardManager();
		boardManager.setupBoard(boardInfo, board);
		
	}
	
	public void messageMapCreator (HashMap<String, String> messageMap)
	{
		Reader reader = new Reader("messages1.0.csv", ";");
		reader.readFileIntoHashMap(messageMap);
	}
	
	public Player[] playerSetup(GuiController guiController, HashMap<String, String> messageMap)
	{
		guiController.showMessage(messageMap.get("Welcome"));
		
		// Ask how many players who wants to play and Initialize Player[]
		Player[] players = new Player[guiController.getUserInteger(messageMap.get("GetPlayers"), 3, 6)];
		
		
		
		return players;
	}
    
    /*
    ----------------------- Support Methods ----------------------
     */
    private void loadBoardInfo()
	{
		Reader reader = new Reader("boardV1.csv", ";");
		reader.readFileIntoHashMap(boardInfo);
	}
}
