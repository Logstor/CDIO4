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
		tokenColorsString.add("Sort"); tokenColorsString.add("Hvid"); tokenColorsString.add("Rød");
		tokenColorsString.add("Grøn"); tokenColorsString.add("Gul"); tokenColorsString.add("Blå");

		for (int i = 0; i < players.length; i++) {
		String name = guiController.getUserString(messageMap.get("NamePlayer") + " " + Integer.toString(i+1));

			String chosenTokenColor = guiController.getUserChoice("Vælg en farve til din spillerbrik:", tokenColorsString);
			for (int j = 0; j < tokenColorsString.size(); j++) {
				if (tokenColorsString.get(j).equals(chosenTokenColor)) {
					tokenColorsString.remove(j);
				}
			}
			Color tokenColor = stringToColorSwitch(chosenTokenColor);

			Player player = new Player(name, tokenColor, 30000, 0);
			players[i]=(player);
			guiController.showMessage(messageMap.get("TokenSelected") + " " + chosenTokenColor.toLowerCase());
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

	private Color stringToColorSwitch (String colorString) {
    	Color colorFromString;

    	switch (colorString) {
			case "Sort":
				colorFromString = Color.BLACK;
				break;
			case "Hvid":
				colorFromString = Color.WHITE;
				break;
			case "Rød":
				colorFromString = Color.RED;
				break;
			case "Grøn":
				colorFromString = Color.GREEN;
				break;
			case "Gul":
				colorFromString = Color.YELLOW;
				break;
			case "Blå":
				colorFromString = Color.BLUE;
				break;
			default:
				colorFromString = Color.PINK;
				break;
		}
    	return colorFromString;
	}
}
