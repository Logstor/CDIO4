package controller;

import model.board.Board;
import model.cup.Cup;
import model.player.Player;
import view.gui.Gui;

/**
 * @author Alfred Röttger Rydahl
 */

public class MainControl {

    /*
    --------------------------- Fields ---------------------------
     */
    private Gui gui;
    private Board board;
    private Player[] players;
    private Cup cup;
    
    /*
    ------------------------ Constructors ------------------------
     */
    public MainControl ()
	{ }
    /*
    ------------------------- Properties -------------------------
     */    
    /*
    ----------------------- Public Methods -----------------------
     */
	
	/**
	 * This method starts the game.
	 * @return Returns non-zero if an unexpected shutdown happends.
	 */
	public int launch ()
	{
		// Set the game up, and display
		setup();
		return 0;
	}
    /*
    ----------------------- Support Methods ----------------------
     */
	
	/**
	 * This method sets the board, and takes care of getting information from user.
	 */
	private void setup ()
	{
		SetupControl setupControl = new SetupControl(players, cup, board);
		
		// Creating the board with all Fields
		gui = setupControl.setShitUp();
		
		
	}
}
