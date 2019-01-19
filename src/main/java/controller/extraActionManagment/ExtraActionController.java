package controller.extraActionManagment;

import controller.GeneralActionController;
import controller.GuiController;
import controller.extraActionManagment.extraActions.BuyHousesAction;
import controller.extraActionManagment.extraActions.SellFieldAction;
import model.board.Board;
import model.player.Player;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Rasmus Sander Larsen
 * @date 17-01-2019
 */
public class ExtraActionController {

    /*
    -------------------------- Fields --------------------------
     */

    private Player[] players;
    private Board board;
    private GuiController guiController;
    private HashMap<String,String> messageMap;
    private GeneralActionController generalActionController;

    // ExtraActions
    private SellFieldAction sellFieldAction;
    private BuyHousesAction buyHousesAction;

    
    /*
    ----------------------- Constructor -------------------------
     */
    
    public ExtraActionController (Player[] players, Board board, GuiController guiController,
                                  HashMap<String,String> messageMap, GeneralActionController generalActionController) {
        this.players = players;
        this.board = board;
        this.guiController = guiController;
        this.messageMap = messageMap;
        this.generalActionController = generalActionController;

        sellFieldAction = new SellFieldAction(players,guiController,messageMap,generalActionController);
        buyHousesAction = new BuyHousesAction(board,guiController,messageMap,generalActionController);

    }
    
    /*
    ------------------------ Properties -------------------------
     */

    // <editor-folder desc="Properties"

    public SellFieldAction getSellFieldAction() {
        return sellFieldAction;
    }

    public void setSellFieldAction(SellFieldAction sellFieldAction) {
        this.sellFieldAction = sellFieldAction;
    }

    public BuyHousesAction getBuyHousesAction() {
        return buyHousesAction;
    }

    public void setBuyHousesAction(BuyHousesAction buyHousesAction) {
        this.buyHousesAction = buyHousesAction;
    }


    // </editor-folder>
    
    /*
    ---------------------- Public Methods -----------------------
     */

    public void doExtraAction (Player currentPlayer) {

        if (guiController.getLeftButtonPressed(messageMap.get("DoExtraAction?"),
                messageMap.get("Yes"), messageMap.get("No"))) {

            //Checks if the Player owns anything and if true it adds extra options to Arraylist.
            ArrayList<String> extraActionOptions = new ArrayList<>();
            if (sellFieldAction.checkIfValidForSellField(currentPlayer)) {
                // Adds the option: SellField.
                extraActionOptions.add(messageMap.get("SellField"));
            }
            if (buyHousesAction.checkIfPlayerIsValidForBuyHouses(currentPlayer)) {
                // Adds the option: BuyHouses.
                extraActionOptions.add(messageMap.get("BuyHouse"));
            }

            // Gets the chose of Player.
            String chosenOption = guiController.getUserChoice(messageMap.get("ExtraOptionsChoices"),extraActionOptions);

            // Finds the right extraAction og runs it.
            extraActionSelectorSwitch(currentPlayer,chosenOption);
        }
    }
    
    public boolean isExtraActionsValid (Player currentPlayer) {

        if (sellFieldAction.checkIfValidForSellField(currentPlayer)) {
            return true;
        } else if (buyHousesAction.checkIfPlayerIsValidForBuyHouses(currentPlayer)) {
            return true;
        } else {
            return false;
        }
    }
    
    /*
    ---------------------- Support Methods ----------------------
     */

    private void extraActionSelectorSwitch (Player currentPlayer, String nameOnSelectedAction) {

        String keyForValue = null;
        for ( String s : messageMap.keySet() ){
            if ( messageMap.get(s).equals(nameOnSelectedAction) )
                keyForValue = s;
        }

        ExtraActionType_Enum extraActionTypeOfSelected = ExtraActionType_Enum.valueOf(keyForValue);

        switch (extraActionTypeOfSelected)
        {
            case BuyHouse:
                buyHousesAction = new BuyHousesAction(board,guiController,messageMap,
                        generalActionController);
                buyHousesAction.doExtraAction(currentPlayer);
                break;
                
            case SellField:
                sellFieldAction = new SellFieldAction(players, guiController,messageMap,
                        generalActionController);
                sellFieldAction.doExtraAction(currentPlayer);
                break;
                
            default:
                break;
        }
    }

}
