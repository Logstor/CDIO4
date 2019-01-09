package model.chancecard.cards;

import model.chancecard.ChanceCard;

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
    
    public void addChanceCardFromCardType (String cardTypes, String cardText, int moneyToTransfer,
                        int taxHousePrice, int taxHotelPrice, int absFieldNo, int relMoving) {

        switch (cardTypes) {
            case "moneyBank":
                addMoneyBank(cardTypes,cardText,moneyToTransfer);
                break;
            case "MovingAbs":
                addMovingAbs(cardTypes,cardText,absFieldNo);
                break;
            case "MovingRel":
                addMovingRel(cardTypes,cardText,relMoving);
                break;
            default:
                break;
        }
    }
    
    /*
    ---------------------- Support Methods ----------------------
     */

    private void addMoneyBank (String cardType, String cardText, int moneyToTransfer) {
        tempListOfCards.add(new MoneyBank(cardType, cardText, moneyToTransfer));
    }

    private void addMovingAbs (String cardType, String cardText, int absFieldNo) {
        tempListOfCards.add(new MovingAbs(cardType, cardText, absFieldNo));
    }

    private void addMovingRel (String cardType, String cardText, int relMoving) {
        tempListOfCards.add(new MovingAbs(cardType, cardText, relMoving));
    }
}
