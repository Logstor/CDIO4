package model.chancecard.cards;

import model.chancecard.ChanceCard;
import model.chancecard.ChanceCardEnum;

import java.util.ArrayList;

/**
 * @author Rasmus Sander Larsen
 * @date 09-01-2019
 */
public class ChanceCardAdder {

    /*
    -------------------------- Fields --------------------------
     */

    private ArrayList<ChanceCard> tempListOfCards;

    
    /*
    ----------------------- Constructor -------------------------
     */
    
    public ChanceCardAdder (ArrayList<ChanceCard> arrayListOfChanceCard) {
        tempListOfCards = arrayListOfChanceCard;

    }
    
    /*
    ------------------------ Properties -------------------------
     */

    // <editor-folder desc="Properties"


    // </editor-folder>
    
    /*
    ---------------------- Public Methods -----------------------
     */
    
    public void addChanceCardFromCardType (ChanceCardEnum cardTypes, String cardText, int moneyToTransfer,
                                           int taxHousePrice, int taxHotelPrice, int absFieldNo, int relMoving) {

        switch (cardTypes) {
            case moneyBank:
                addMoneyBank(cardTypes,cardText,moneyToTransfer);
                break;
            case movingAbs:
                addMovingAbs(cardTypes,cardText,absFieldNo);
                break;
            case movingRel:
                addMovingRel(cardTypes,cardText,relMoving);
                break;
            default:
                break;
        }
    }
    
    /*
    ---------------------- Support Methods ----------------------
     */

    private void addMoneyBank (ChanceCardEnum cardType, String cardText, int moneyToTransfer) {
        tempListOfCards.add(new MoneyBank(cardType, cardText, moneyToTransfer));
    }

    private void addMovingAbs (ChanceCardEnum cardType, String cardText, int absFieldNo) {
        tempListOfCards.add(new MovingAbs(cardType, cardText, absFieldNo));
    }

    private void addMovingRel (ChanceCardEnum cardType, String cardText, int relMoving) {
        tempListOfCards.add(new MovingAbs(cardType, cardText, relMoving));
    }
}
