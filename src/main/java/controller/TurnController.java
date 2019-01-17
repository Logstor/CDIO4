package controller;

import controller.extraActionManagment.ExtraActionController;
import controller.fieldManagement.FieldController;
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
    private ExtraActionController extraActionController;

    /*
    ----------------------- Constructor -------------------------
     */

	public TurnController(GuiController guiController, Board board, Player[] players, Cup cup, Deck deck, HashMap<String, String> messageMap)
	{
		this.guiController = guiController;
		this.board = board;
		this.players = players;
		this.cup = cup;
		this.deck = deck;
		this.messageMap = messageMap;

		this.generalActionController = new GeneralActionController();

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

		//region Raffle
		
		raffleCup();
		
		//endregion
		
		//region Move Player
		
		moveRaffle();
		
		//endregion
		
		//region FieldAction
		
		FieldController fieldController = new FieldController(currentField, guiController, player, board, deck,
				messageMap, cup, generalActionController);
		fieldController.doFieldActionByFieldType();
		
		//endregion

        //region ExtraTurn?

        extraTurn();

        //endregion

		//region Buy Houses?

        extraActionController = new ExtraActionController(currentPlayer, players, board, guiController, messageMap,
                generalActionController);

        if (extraActionController.isExtraActionsValid()) {
			extraActionController.doExtraAction();
		}


		//endregion
	}
	
	/**
	 * This method handles the logic when the currentPlayer is in prison.
	 * @param player The currentPlayer
	 */
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
		
		//region Decide which option the player chose
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
					currentPlayer.setPrisonStat(0);
				break;

			case "Fængselskort":
				currentPlayer.setPrisonStat(0);
				currentPlayer.setPrisonCard(false);
				break;
		}
		//endregion
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

}
