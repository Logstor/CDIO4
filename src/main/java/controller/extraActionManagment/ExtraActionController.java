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
    
    private Player currentPlayer;
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
    
    public ExtraActionController (Player currentPlayer, Player[] players, Board board, GuiController guiController,
                                  HashMap<String,String> messageMap, GeneralActionController generalActionController) {
        this.currentPlayer = currentPlayer;
        this.players = players;
        this.board = board;
        this.guiController = guiController;
        this.messageMap = messageMap;
        this.generalActionController = generalActionController;

        sellFieldAction = new SellFieldAction(currentPlayer,players,board,guiController,messageMap,
                generalActionController);
        buyHousesAction = new BuyHousesAction(currentPlayer,board,guiController,messageMap,generalActionController);

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

    public void doExtraAction () {

        if (guiController.getLeftButtonPressed(messageMap.get("DoExtraAction?"),
                messageMap.get("Yes"), messageMap.get("No"))) {

            //Checks if the Player owns anything and if true it adds extra options to Arraylist.
            ArrayList<String> extraActionOptions = new ArrayList<>();
            if (sellFieldAction.checkIfValidForSellField()) {
                // Adds the option: SellField.
                extraActionOptions.add(messageMap.get("SellField"));
            }
            if (buyHousesAction.checkIfPlayerIsValidForBuyHouses()) {
                // Adds the option: BuyHouses.
                extraActionOptions.add(messageMap.get("BuyHouse"));
            }

            // Gets the chose of Player.
            String chosenOption = guiController.getUserChoice(messageMap.get("ExtraOptionsChoices"),extraActionOptions);

            // Finds the right extraAction og runs it.
            extraActionSelectorSwitch(chosenOption);
        }
    }
    
    public boolean isExtraActionsValid () {

        if (sellFieldAction.checkIfValidForSellField()) {
            return true;
        } else if (buyHousesAction.checkIfPlayerIsValidForBuyHouses()) {
            return true;
        } else {
            return false;
        }
    }
    
    /*
    ---------------------- Support Methods ----------------------
     */

    private void extraActionSelectorSwitch (String nameOnSelectedAction) {

        String keyForValue = null;
        for ( String s : messageMap.keySet() ){
            if ( messageMap.get(s).equals(nameOnSelectedAction) )
                keyForValue = s;
        }

        ExtraActionType_Enum extraActionTypeOfSelected = ExtraActionType_Enum.valueOf(keyForValue);

        switch (extraActionTypeOfSelected)
        {
            case BuyHouse:
                buyHousesAction = new BuyHousesAction(currentPlayer,board,guiController,messageMap,
                        generalActionController);
                buyHousesAction.doExtraAction();
                break;
                
            case SellField:
                sellFieldAction = new SellFieldAction(currentPlayer, players, board,guiController,messageMap,
                        generalActionController);
                sellFieldAction.doExtraAction();
                break;
                
            default:
                break;
        }
    }

}
