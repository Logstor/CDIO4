package controller;

import controller.extraActionManagment.ExtraActionController;
import controller.extraActionManagment.extraActions.BuyHousesController;
import controller.fieldManagement.FieldController;
import model.board.Board;
import model.board.Field;
import model.chancecard.Deck;
import model.cup.Cup;
import model.player.Player;

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
    private Field currentField;
    private ExtraActionController extraActionController;
    /*
    ----------------------- Constructor -------------------------
     */
    
    public TurnController () {
	}

    
    /*
    ------------------------ Properties -------------------------
     */

    //region Properties
	
	//endregion
    
    /*
    ---------------------- Public Methods -----------------------
     */

    public void playTurn (Player player, GuiController guiController, HashMap<String,String> messageMap, Deck deck,
						  Board board, Cup cup, GeneralActionController generalActionController,
						  ExtraActionController extraActionController)
	{
		
		//region Raffle
		
		raffleCup(player, guiController, messageMap, cup);
		
		//endregion
		
		//region Move Player
		
		moveRaffle(player, board, guiController, messageMap, generalActionController);
		
		//endregion
		
		//region FieldAction
		
		FieldController fieldController = new FieldController(currentField, guiController, player, board, deck,
				messageMap, cup, generalActionController);
		fieldController.doFieldActionByFieldType();
		
		//endregion

        //region ExtraTurn?

        extraTurn(player,guiController,cup,board,deck,messageMap,generalActionController, extraActionController);

        //endregion

		//region Buy Houses?

		extraActionController.doExtraAction();

		//endregion
	}
	
	public void playPrisonTurn ()
	{
		//FIXME: Implementér denne metode, og brug den i MainControl - GameLoop
	}

    /*
    ---------------------- Support Methods ----------------------
     */

    private void raffleCup (Player player, GuiController guiController, HashMap<String,String> messageMap, Cup cup) {
		guiController.showMessage(messageMap.get("YourTurn").replace("%name",player.getName())+ "\n" +
				messageMap.get("PressToRoll"));

		cupValue = cup.cupRoll();
		die1Value = cup.getDies()[0].getFaceValue();
		die2Value = cup.getDies()[1].getFaceValue();
		preTotalPosition = player.getTotalPosition();
		prePosition = player.getPosition();

		guiController.showDice(die1Value,die2Value);
		player.updatePosition(cupValue);
		postTotalPosition = player.getTotalPosition();
		postPosition = player.getPosition();

	}

	private void moveRaffle (Player player, Board board, GuiController guiController,
                             HashMap<String,String> messageMap, GeneralActionController generalActionController)
	{
		generalActionController.movingPlayerForwardGUI(player,board,guiController,prePosition,postPosition,
                250);
		
		guiController.showMessage(messageMap.get("YouRolled").replace("%cupValue", String.valueOf(cupValue)));

		currentField = board.getBoard()[postPosition];
	}

	private void extraTurn(Player player, GuiController guiController, Cup cup, Board board, Deck deck,
                           HashMap<String, String>messageMap,GeneralActionController generalActionController,
						   ExtraActionController extraActionController)
	{
        int die1 = cup.getDies()[0].getFaceValue();
        int die2 = cup.getDies()[1].getFaceValue();

        if(die1==die2)
        {
            guiController.showMessage(messageMap.get("ExtraTurn"));
            playTurn(player,guiController,messageMap,deck,board,cup,generalActionController, extraActionController);
        }

    }

}
