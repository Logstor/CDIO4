package controller;

import controller.extraActionManagment.extraActions.BuyHousesController;
import controller.fieldManagement.FieldController;
import gui_fields.GUI_Field;
import model.board.Board;
import model.board.Field;
import model.chancecard.Deck;
import model.cup.Cup;
import model.player.Player;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Rasmus Sander Larsen
 * @date 13-01-2019
 */
public class TurnController {

    /*
    -------------------------- Fields --------------------------
     */
    private int cupValue, die1Value, die2Value;
    private int preTotalPosition, postTotalPosition;
    private int prePosition, postPosition;
    private final int ROLLCHANCES = 3;
    private Field currentField;
    
	private GuiController guiController;
	private Board board;
	private Player[] players;
	private Cup cup;
	private Deck deck;
	
	private Player currentPlayer;
	
	private HashMap<String, String> messageMap;
	private GeneralActionController generalActionController;
	private BuyHousesController buyHousesController;
    /*
    ----------------------- Constructor -------------------------
     */
	
	public TurnController(GuiController guiController, Board board,
						  Player[] players, Cup cup, Deck deck,
						  HashMap<String, String> messageMap)
	{
		this.guiController = guiController;
		this.board = board;
		this.players = players;
		this.cup = cup;
		this.deck = deck;
		this.messageMap = messageMap;
		this.generalActionController = new GeneralActionController();
		this.buyHousesController = new BuyHousesController();
	}
    
    /*
    ------------------------ Properties -------------------------
     */

    //region Properties
	
	//endregion
    
    /*
    ---------------------- Public Methods -----------------------
     */

    public void playTurn (Player player)
	{
		// Update currentPlayer
		currentPlayer = player;

		checkIfPlayerHasLost(player, guiController, messageMap);
		
		//region Raffle
		
		raffleCup();
		
		//endregion
		
		//region Move Player
		
		moveRaffle();
		
		//endregion

        //region Passing Start

        passingStart(player,guiController,messageMap, generalActionController);

        //endregion
		
		//region FieldAction
		
		FieldController fieldController = new FieldController(currentField, guiController, player, board, deck,
				messageMap, cup, generalActionController);
		fieldController.doFieldActionByFieldType();

		//endregion

        checkIfPlayerHasLost(player,guiController,messageMap);

		//region ExtraTurn?

        extraTurn();

        //endregion

		//region Buy Houses?

		buyHousesController.houseBuying(player, board, guiController, messageMap,generalActionController);

		//endregion
	}
	
	public void playPrisonTurn (Player player)
	{
		
		//FIXME: Implementér denne metode
		
		// Update currentPlayer
		currentPlayer = player;
		
		// ArrayList to hold the players opportunities
		ArrayList<String> options = new ArrayList<>(3);
		
		//region Check the players opportunities
		
		// Add roll option
		options.add(messageMap.get("Roll"));
		//region Checks
		
		if ( player.isPrisonCard() )
		{
			options.add(messageMap.get("UsePrisonCard"));
		}
		
		if ( player.getAccount().getBalance() >= 1000 )
		{
			options.add(messageMap.get("Pay"));
		}
		
		//endregion
		
		//endregion
		
		switch ( guiController.getUserChoice( messageMap.get("InPrison"), options ) )
		{
			// Subtract kr. 1000 from the player, and set prisonStat to 0
			case "Betal":
				generalActionController.updatePlayerBalanceInclGui(guiController, currentPlayer, -1000);
				currentPlayer.setPrisonStat(0);
				break;
				
			//
			case "Rul":
				//TODO: Give the player 3 rolls
				if ( raffleBreakout() )
				{
				
				}
				break;
				
			case "Fængselskort":
				break;
		}
		
		
	}

    /*
    ---------------------- Support Methods ----------------------
     */

    private void raffleCup ()
	{
		guiController.showMessage(messageMap.get("YourTurn").replace("%name",currentPlayer.getName())+ "\n" +
				messageMap.get("PressToRoll"));

		cupValue = cup.cupRoll();
		die1Value = cup.getDies()[0].getFaceValue();
		die2Value = cup.getDies()[1].getFaceValue();
		preTotalPosition = currentPlayer.getTotalPosition();
		prePosition = currentPlayer.getPosition();

		guiController.showDice(die1Value,die2Value);
		currentPlayer.updatePosition(cupValue);
		postTotalPosition = currentPlayer.getTotalPosition();
		postPosition = currentPlayer.getPosition();

	}

	private void moveRaffle ()
	{
		generalActionController.movingPlayerForwardGUI(currentPlayer,board,guiController,prePosition,postPosition,
                250);
		
		guiController.showMessage(messageMap.get("YouRolled").replace("%cupValue", String.valueOf(cupValue)));

		currentField = board.getBoard()[postPosition];
	}

	private void extraTurn ()
	{
        int die1 = cup.getDies()[0].getFaceValue();
        int die2 = cup.getDies()[1].getFaceValue();

        if(die1==die2)
        {
            guiController.showMessage(messageMap.get("ExtraTurn"));
            playTurn(currentPlayer);
        }

    }
	
	/**
	 * This method gives the player "rolls" amount of chances to roll 2 equal dices.
	 * @return True if the player gets equal dices.
	 */
	private boolean raffleBreakout ()
	{
		//region Give player "rolls" amount of chances in loop
		for (int i = 0; i < ROLLCHANCES; i++ )
		{
			// Roll the dices
			guiController.showMessage( messageMap.get("PrisonRoll").replace("%gang", String.valueOf(i)) );
			cup.cupRoll();
			
			if ( cup.getDies()[0] == cup.getDies()[1] )
			{
				guiController.showMessage(messageMap.get("PrisonBreakout"));
				
				// Return true, as the player made it.
				return true;
			}
		}
		
		//endregion
		
		//region Didn't succeed
		
		// Inform the player, and Return false
		guiController.showMessage(messageMap.get("PrisonNoBreak"));
		return false;
		
		//endregion
	}

	/**
	 * This method awards the player with 4000 if they pass start.
	 * @param player Player
	 * @param guiController GuiController
	 * @param messageMap Messages.csv
	 * @param generalActionController GeneralActionController
	 */

    private void passingStart(Player player, GuiController guiController,
                              HashMap<String,String>messageMap, GeneralActionController generalActionController) {
        int preTotalRounds = preTotalPosition/40;
        int postTotalRounds = postTotalPosition/40;

        if (preTotalRounds<postTotalRounds) {
            guiController.showMessage(messageMap.get("PassingStart"));
            generalActionController.updatePlayerBalanceInclGui(guiController,player, +4000);

        }
    }

	/**
	 * This method checks if the player has a balance above 0,
	 * if the player doesn't the ownedFields will be remove aswell as all the houses and hotels
	 * @param player The Player
	 * @param guiController The GuiController
	 * @param messageMap Messages.csv
	 */
    private void checkIfPlayerHasLost (Player player, GuiController guiController, HashMap<String,String>messageMap) {


    	if(player.getAccount().getBalance()<0){
    		player.setHasLost(true);
    		guiController.showMessage(messageMap.get("Lost"));

    		for(Field field: player.getOwnedFields()){
    			guiController.clearFieldForInfo(field);
    			field.setFieldOwner(null);
    			guiController.setHousesAndHotels(0, field);
				field.setNoOfHousesOnField(0);
    			player.removeFieldFromOwnedFields(field);
			}

		}

	}

	private void checkWinner (Player player, GuiController guiController,
							  GeneralActionController generalActionController, HashMap<String,String>messageMap) {



	}

}
