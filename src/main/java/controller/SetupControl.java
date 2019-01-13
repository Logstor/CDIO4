package controller;

import model.board.Board;
import model.board.BoardManager;
import model.player.Player;
import model.reader.Reader;
import view.gui.Gui;

import java.awt.*;
import java.util.ArrayList;
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

		// Color[] tokenColors = new Color[6];
		// tokenColors[0] = Color.BLACK; tokenColors[1] = Color.WHITE; tokenColors[2] = Color.RED;
		// tokenColors[3] = Color.GREEN; tokenColors[4] = Color.YELLOW; tokenColors[5] = Color.BLUE;

		ArrayList<String> tokenColorsString = new ArrayList<>();
		tokenColorsString.add("BLACK"); tokenColorsString.add("WHITE"); tokenColorsString.add("RED");
		tokenColorsString.add("GREEN"); tokenColorsString.add("YELLOW"); tokenColorsString.add("BLUE");

		for (int i = 0; i < players.length; i++) {
		String name = guiController.getUserString(messageMap.get("NamePlayer") + " " + Integer.toString(i+1));

			String chosenToken = guiController.getUserChoice("Vælg en farve til din spillerbrik:", tokenColorsString);
			for (int j = 0; j < tokenColorsString.size(); j++) {
				if (tokenColorsString.get(j).equals(chosenToken)) {
					tokenColorsString.remove(j);
				}
			}

			Player player = new Player(name, chosenToken, 30000, 0);
			players[i]=(player);
			guiController.showMessage(messageMap.get("TokenSelected") + chosenToken);
		}

		
		
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
