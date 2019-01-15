package controller;

import model.board.Board;
import model.board.FieldActionManager;
import model.cup.Cup;
import model.cup.Die;
import model.game.Turn;
import model.player.Player;
import view.gui.Gui;

import java.util.HashMap;

/**
 * @author Rasmus Sander Larsen
 * @date 13-01-2019
 */
public class TurnController {

    /*
    -------------------------- Fields --------------------------
     */

    private FieldActionManager fieldActionManager;
    private int cupValue, die1Value, die2Value;
    private int preTotalPosition, postTotalPosition;
    private int prePosition, postPosition;
    
    /*
    ----------------------- Constructor -------------------------
     */
    
    public TurnController ()
    {
        fieldActionManager = new FieldActionManager();

    }

    
    /*
    ------------------------ Properties -------------------------
     */

    // <editor-folder desc="Properties"


    // </editor-folder>
    
    /*
    ---------------------- Public Methods -----------------------
     */

    public void playTurn (Player player, GuiController guiController,
						  HashMap<String,String> messageMap, Board board, Cup cup, GeneralActionController generalActionController)
	{
		raffleCup(player, guiController, messageMap, cup);

		moveRaffle(player, board, guiController, messageMap, generalActionController);

		fieldActionManager.fieldAction(player, board.getBoard()[player.getPosition()], guiController, messageMap);
	}

    /*
    ---------------------- Support Methods ----------------------
     */

    private void raffleCup (Player player, GuiController guiController, HashMap<String,String> messageMap, Cup cup) {
		guiController.showMessage(messageMap.get("YourTurn") + " " + player.getName() + ".\n" +
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
                             HashMap<String,String> messageMap, GeneralActionController generalActionController) {
		generalActionController.movingPlayerForwardGUI(player,board,guiController,preTotalPosition,postTotalPosition,
                500);
		guiController.showMessage(messageMap.get("YouRolled") + " " + cupValue);
	}



}
