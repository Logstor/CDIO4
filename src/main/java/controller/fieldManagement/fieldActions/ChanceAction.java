package controller.fieldManagement.fieldActions;

import controller.chanceCardManagement.ChanceCardController;
import controller.GeneralActionController;
import controller.GuiController;
import controller.fieldManagement.FieldAction;
import model.board.Board;
import model.chancecard.ChanceCard;
import model.chancecard.Deck;

import model.player.Player;

import java.util.HashMap;

/**
 * @author Alfred Röttger Rydahl
 */
public class ChanceAction extends FieldAction {

	/*
    ---------------------------------- Fields ----------------------------------
     */

	private Deck deck;
	private ChanceCardController chanceCardController;

    /*
    ------------------------------ Constructors --------------------------------
     */

	public ChanceAction(Player player, HashMap<String, String> messageMap, GuiController guiController,
						GeneralActionController generalActionController, Board board, Deck deck) {
		super(player,messageMap,guiController);
		chanceCardController = new ChanceCardController(generalActionController,board,guiController,messageMap);
		this.deck = deck;
	}

	/*
    ------------------------------ Properties ----------------------------------
     */



    /*
    ---------------------------- Public Methods --------------------------------
     */
	/**
	 *
	 *
	 */
	@Override
	public void action() {

		ChanceCard drawedCard = deck.drawChanceCard();
		chanceCardController.doChanceCardActionFromCardType(player, drawedCard);

    }

    /*
    ----------------------------- Support Methods ------------------------------
     */
}
