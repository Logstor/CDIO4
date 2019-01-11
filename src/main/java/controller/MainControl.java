package controller;

import model.board.Board;
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
    
    private HashMap<String, String> messageMap;
    /*
    ------------------------ Constructors ------------------------
     */
    
    public MainControl () { }
    
    /*
    ----------------------- Public Methods -----------------------
     */
	
	/**
	 * This method starts the game.
	 * @return Returns non-zero if an unexpected shutdown happens.
	 */
	public int letsFuckingGo()
	{
		try {
			// Set the game up, and display
			setup();
			return 0;
		}
		catch (Exception e) {
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
		SetupControl setupControl = new SetupControl(gui, guiController, players, cup, board, messageMap);
		
		// Creating the board with all Fields
		setupControl.setShitUp();
		
	}
}
