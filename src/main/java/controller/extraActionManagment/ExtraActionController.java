package controller.extraActionManagment;

import controller.GeneralActionController;
import controller.GuiController;
import controller.extraActionManagment.extraActions.BuyHousesAction;
import controller.extraActionManagment.extraActions.PledgeAction;
import controller.extraActionManagment.extraActions.SellFieldAction;
import controller.extraActionManagment.extraActions.UnPledgeAction;
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

    private ArrayList<Player> playerArrayList;
    private Board board;
    private GuiController guiController;
    private HashMap<String,String> messageMap;
    private GeneralActionController generalActionController;

    // ExtraActions
    private SellFieldAction sellFieldAction;
    private BuyHousesAction buyHousesAction;
    private PledgeAction pledgeAction;
    private UnPledgeAction unPledgeAction;
    
    /*
    ----------------------- Constructor -------------------------
     */
    
    public ExtraActionController (ArrayList<Player> playerArrayList, Board board, GuiController guiController,
                                  HashMap<String,String> messageMap, GeneralActionController generalActionController) {
        this.playerArrayList = playerArrayList;
        this.board = board;
        this.guiController = guiController;
        this.messageMap = messageMap;
        this.generalActionController = generalActionController;

        sellFieldAction = new SellFieldAction(playerArrayList,guiController,messageMap,generalActionController);
        buyHousesAction = new BuyHousesAction(board,guiController,messageMap,generalActionController);
        pledgeAction = new PledgeAction(guiController,messageMap,generalActionController);
        unPledgeAction = new UnPledgeAction(guiController, messageMap,generalActionController);

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
            if (pledgeAction.checkIfPledgeIsValid(currentPlayer)){
                //Adds the option: Pledge
                extraActionOptions.add(messageMap.get("Pledge"));
            }
            if (unPledgeAction.checkIfUnPledgeIsValid(currentPlayer)) {
                //Adds the option: UnPledge
                extraActionOptions.add(messageMap.get("UnPledge"));
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
        } else if (pledgeAction.checkIfPledgeIsValid(currentPlayer)) {
            return true;
        } else if (unPledgeAction.checkIfUnPledgeIsValid(currentPlayer)) {
            return true;
        }else{
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
                sellFieldAction.doExtraAction(currentPlayer);
                break;

            case Pledge:
                pledgeAction.doExtraAction(currentPlayer);
                break;

            case UnPledge:
                unPledgeAction.doExtraAction(currentPlayer);
                break;

            default:
                break;
        }
    }

}
