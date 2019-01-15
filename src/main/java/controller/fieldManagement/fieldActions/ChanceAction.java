package controller.fieldManagement.fieldActions;

import controller.GuiController;
import controller.fieldManagement.FieldAction;
import model.board.Field;
import model.chancecard.CardTypeEnum;
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

    /*
    ------------------------------ Constructors --------------------------------
     */

	public ChanceAction(Player player, HashMap<String, String> messageMap, GuiController guiController, Deck deck) {
		super(player,messageMap,guiController);
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



		guiController.displayCCard();



	}

    /*
    ----------------------------- Support Methods ------------------------------
     */
}
