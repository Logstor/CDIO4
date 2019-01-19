package controller;

import controller.extraActionManagment.ExtraActionController;
import controller.extraActionManagment.extraActions.SellFieldAction;
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

	private HashMap<String, String> messageMap;
	private GeneralActionController generalActionController;
 	private ExtraActionController extraActionController;

 	private FieldController fieldController;
    

    /*
    ----------------------- Constructor -------------------------
     */

	public TurnController(GuiController guiController, Board board, Player[] players, Cup cup, Deck deck,
						  HashMap<String, String> messageMap, ExtraActionController extraActionController)
	{
		this.guiController = guiController;
		this.board = board;
		this.players = players;
		this.cup = cup;
		this.deck = deck;
		this.messageMap = messageMap;

		this.generalActionController = new GeneralActionController();
		this.extraActionController = extraActionController;

		fieldController = new FieldController(board, deck, guiController,
				messageMap, cup, generalActionController);

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
		int equalsCounter = 0;

		do {

			//region If Player is in Prison: Do Prison Logic. Else Raffle Logic incl 3 times equal dices.

			if (player.getPrisonStat()>0) {
				playPrisonTurn(player);
			} else {
				// Raffle Cup.
				raffleCup(player);

				// Checks if Roll is valid for an extraTurn (NO Message). Increments equalsCounter if valid.
				if (checkIfRollIsValidForExtraTurn()) {
					equalsCounter++;
				}

				// If player Rolls 2 equal dices, 3 times. Players is sent to prison and Player turn ends.
				if (equalsCounter== 3) {
					guiController.showMessage(messageMap.get("3EqualsRolls"));
					sentPlayerToPrison(player);
					break;
				}

			}

			//endregion

			//region to run if out of Prison
			if (player.getPrisonStat()==0) {

				//region Move Player

				moveRaffle(player);

				//endregion

				//region CheckIfPassedStartAfterAction

				generalActionController.passingStart(player, preTotalPosition, postTotalPosition, guiController, messageMap);

				//endregion

				//region FieldAction

				fieldActionWithNewFieldActionIfMovedByField(player);
				//				fieldController.doFieldActionByFieldType(player, currentField);

				//endregion

				//region Check winner/loser
				checkIfPlayerHasLost(player, extraActionController.getSellFieldAction());
				//endregion

			}
			//endregion

			//region Prints ExtraTurn Message if Valid

			if (player.getPrisonStat()==0) {
				printExtraTurnMessageIfValid();
			}

			//endregion


		} while (die1Value == die2Value);

	}
	
	/**
	 * This method handles the logic when the currentPlayer is in prison.
	 * @param player The currentPlayer
	 */
	public void playPrisonTurn (Player player)
	{


		// ArrayList to hold the players opportunities
		ArrayList<String> options = new ArrayList<>(3);

		//region Check the players opportunities

		// Add roll option
		options.add(messageMap.get("Roll"));

		//region Checks

		if ( player.getPrisonCard() > 0 )
		{
			options.add(messageMap.get("UsePrisonCard"));
		}

		if ( player.getAccount().getBalance() >= 1000 )
		{
			options.add(messageMap.get("Pay"));
		}

		//endregion

		//endregion

		String chosenGetOutOption = guiController.getUserChoice( messageMap.get("InPrison")
				.replace("%name",player.getName()), options );
		//region Decide which option the player chose
		switch (chosenGetOutOption)
		{
			// Subtract kr. 1000 from the player, and set prisonStat to 0
			case "Betal":
				generalActionController.updatePlayerBalanceInclGui(guiController, player, -1000);
				player.setPrisonStat(0);
				break;

			// Give the player 3 roll chances
			case "Rul":
				if ( raffleBreakout(player) )
					player.setPrisonStat(0);
				break;
				
			// Take the player out of prison, and remove his PrisonCard
			case "Fængselskort":
				player.setPrisonStat(0);
				player.setPrisonCard(player.getPrisonCard() - 1);
				break;
		}
		//endregion
	}

    /*
    ---------------------- Support Methods ----------------------
     */

    private void raffleCup (Player player)
	{
		guiController.showMessage(messageMap.get("YourTurn").replace("%name",player.getName())+ "\n" +
				messageMap.get("PressToRoll"));

		cupValue = cup.cupRoll();
		die1Value = cup.getDies()[0].getFaceValue();
		die2Value = cup.getDies()[1].getFaceValue();
		guiController.showDice(die1Value,die2Value);

	}

	private void moveRaffle (Player player)
	{
		// Updates Player Position.
		player.updatePosition(cupValue);

		// Updates all local pre and post variables for player.
		calAllPreAndPostPositionAfterPlayerPositionUpdate(player);

		// Moves player on GUI.
		generalActionController.movingPlayerForwardGUI(player,board,guiController,prePosition,postPosition,
				250);
		
		guiController.showMessage(messageMap.get("YouRolled").replace("%cupValue", String.valueOf(cupValue)));

		currentField = board.getBoard()[postPosition];
	}

	private boolean checkIfRollIsValidForExtraTurn()
	{
        int die1 = cup.getDies()[0].getFaceValue();
        int die2 = cup.getDies()[1].getFaceValue();

        if(die1==die2){
            return true;
        } else {
        	return false;
		}
    }

    public void printExtraTurnMessageIfValid () {
		int die1 = cup.getDies()[0].getFaceValue();
		int die2 = cup.getDies()[1].getFaceValue();

		if(die1==die2) {
			guiController.showMessage(messageMap.get("ExtraTurn"));
		}
	}

	/**
	 * This method gives the player "rolls" amount of chances to roll 2 equal dices.
	 * @return True if the player gets equal dices.
	 */
	private boolean raffleBreakout (Player player)
	{
		//region Give player "rolls" amount of chances in loop
		for (int i = 0; i < ROLLCHANCES; i++ )
		{
			// Roll the dices
			guiController.showMessage( messageMap.get("PrisonRoll").
					replace("%noPrisonRoll", String.valueOf(i+1)));


			//Rolls and loads variables
			cup.cupRoll();
			int die1 = cup.getDies()[0].getFaceValue();
			int die2 = cup.getDies()[1].getFaceValue();

			guiController.showDice(die1, die2);

			if ( die1 == die2 )
			{
				guiController.showMessage(messageMap.get("PrisonBreakout"));
				//TODO: Spilleren skal rykkes hans slag, ved ikke om det sker.

				// Return true, as the player made it.

				return true;
			} else {
				// "You didnt roll two of the same, try again" message.
				if (i!=2) {
					guiController.showMessage(messageMap.get("PrisonNewRoll"));
				}
			}
		}

		//endregion

		//region Didn't succeed

		// Inform the player, increate prisonStat, and Return false
		guiController.showMessage(messageMap.get("PrisonNoBreak"));
		player.setPrisonStat(player.getPrisonStat() + 1);
		return false;

		//endregion
	}

	/**
	 * This method checks if the player has a balance above 0,
	 * if the player doesn't the ownedFields will be remove aswell as all the houses and hotels
	 * @param player The Player
	 */
    private boolean checkIfPlayerHasLost (Player player, SellFieldAction sellFieldAction) {
    	if(player.getAccount().getBalance()<0){

    		if (player.getOwnedFields().size()>0) {

    			while (guiController.getLeftButtonPressed(messageMap.get("PlayerFallitAnyBuyersOfFields").
								replace("&name", player.getName()).
								replace("%ownedFields", String.valueOf(player.getOwnedFields().size())),
						messageMap.get("Yes"), messageMap.get("No"))) {

    				sellFieldAction.forceSellField(player);
				}
			}

    		if (player.getAccount().getBalance() <0) {

				player.setHasLost(true);
				guiController.showMessage(messageMap.get("Lost"));

			}
    		return player.isHasLost();
		} else {
    		return false;
		}

	}

	private void checkWinner (Player player, GuiController guiController,
							  HashMap<String,String>messageMap) throws InterruptedException {

    	if(players.length==1){
			guiController.showMessage(messageMap.get("Winner").replace("%name",player.getName()));
    		guiController.WinnerMode();


		}

	}

	private void sentPlayerToPrison (Player player){

    	player.setPrisonStat(1);

		int currentPosition, movesToUpdatePositionWith, prisonFieldIndex = 10;

		currentPosition = player.getPosition();
		if (currentPosition>prisonFieldIndex) {
			movesToUpdatePositionWith = -1*(currentPosition-prisonFieldIndex);
		} else {
			movesToUpdatePositionWith = prisonFieldIndex - currentPosition;
		}

		// Player position is updated with "movesToUpDatePositionWith" which is calculated from players CurrentPosition.
		player.updatePosition(movesToUpdatePositionWith);
		guiController.movePlayer(player, player.getPosition());
	}

	public void fieldActionWithNewFieldActionIfMovedByField (Player player) {

		int postFieldActionTotalPosition;
		int preFieldActionTotalPosition;

    	do{
    		preFieldActionTotalPosition = player.getTotalPosition();

			fieldController.doFieldActionByFieldType(player, board.getBoard()[player.getPosition()]);

			postFieldActionTotalPosition = player.getTotalPosition();

		} while (preFieldActionTotalPosition<postFieldActionTotalPosition);

	}

	private void calAllPreAndPostPositionAfterPlayerPositionUpdate (Player player) {

    	postPosition= player.getPosition();
    	postTotalPosition = player.getTotalPosition();

		prePosition = (player.getTotalPosition()-cup.getCupValue())%24;
		preTotalPosition = player.getTotalPosition()-cup.getCupValue();

	}
}
