package controller;

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
    private Player[] players;
    private Cup cup;
    
    /*
    ------------------------ Constructors ------------------------
     */
    public MainControl ()
	{
	
	}
    /*
    ------------------------- Properties -------------------------
     */    
    /*
    ----------------------- Public Methods -----------------------
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
    private void setup ()
	{
		SetupControl setupControl = new SetupControl();
		
		
	}
}
