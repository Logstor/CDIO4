package model.chancecard;

import model.chancecard.cards.ChanceCardAdder;

import java.util.HashMap;

/**
 * @author Rasmus Sander Larsen
 * @date 09-01-2019
 */
public class DeckManager {

    /*
    -------------------------- Fields --------------------------
     */

    private int noOfCardsInDeck;

    private int cardTypes_Index;
    private int cardText_Index;
    private int moneyToTransfer_Index;
    private int taxHousePrice_Index;
    private int taxHotelPrice_Index;
    private int absFieldNo_Index;
    private int relMoving_Index;

    private HashMap<String, String> deckInfoMap;
    
    /*
    ----------------------- Constructor -------------------------
     */

    public DeckManager() {
        deckInfoMap = new HashMap<>();
    }

    /*
    ------------------------ Properties -------------------------
     */

    // <editor-folder desc="Properties"


    // </editor-folder>
    
    /*
    ---------------------- Public Methods -----------------------
     */
    
    public void setupBeck (HashMap<String, String > hashMapWithDeckInfo, Deck deck) {
        //Loads the indexNumber of for all the cardInformation into variables.
        setupInformationVariables(hashMapWithDeckInfo);
        // Loads card information into a HashMap.
        setupDeckInfoMap(hashMapWithDeckInfo);
        // Loads Deck with Cards from DeckInfoMap
        deckInfoMapIntoDeck(deck);

    }
    
    /*
    ---------------------- Support Methods ----------------------
     */

    private void deckInfoMapIntoDeck (Deck deck) {

        ChanceCardAdder chanceCardAdder = new ChanceCardAdder(deck.getChanceCardDeck());

        for (int i = 1; i<=noOfCardsInDeck;i++) {

            String line = deckInfoMap.get("card" + i);
            String[] chanceCardInfo = line.split(";");
            CardTypeEnum tempCardTypes = CardTypeEnum.valueOf(chanceCardInfo[cardTypes_Index]);
            String tempCardText = chanceCardInfo[cardText_Index];
            int tempMoneyToTransfer = Integer.parseInt(chanceCardInfo[moneyToTransfer_Index]);
            int tempTaxHousePrice = Integer.parseInt(chanceCardInfo[taxHousePrice_Index]);
            int tempTaxHotelPrice = Integer.parseInt(chanceCardInfo[taxHotelPrice_Index]);
            int tempAbsFieldNo = Integer.parseInt(chanceCardInfo[absFieldNo_Index]);
            int tempRelMoving = Integer.parseInt(chanceCardInfo[relMoving_Index]);

            chanceCardAdder.addChanceCardFromCardType(tempCardTypes,tempCardText,tempMoneyToTransfer,tempTaxHousePrice,
                    tempTaxHotelPrice,tempAbsFieldNo,tempRelMoving);

        }
    }

    private void setupDeckInfoMap (HashMap<String,String> hashMapWithDeckInfo) {
        for (int i = 1; i <= noOfCardsInDeck; i++) {
            deckInfoMap.put("card" + i, hashMapWithDeckInfo.get("card" +i));
        }
    }

    private void setupInformationVariables (HashMap<String,String> hashMapWithDeckInfo) {

        noOfCardsInDeck = Integer.parseInt(hashMapWithDeckInfo.get("noOfCardsInDeck"));

        cardTypes_Index = Integer.parseInt(hashMapWithDeckInfo.get("CardTypes_Index"));
        cardText_Index = Integer.parseInt(hashMapWithDeckInfo.get("CardText_Index"));
        moneyToTransfer_Index = Integer.parseInt(hashMapWithDeckInfo.get("MoneyToTransfer_Index"));
        taxHousePrice_Index = Integer.parseInt(hashMapWithDeckInfo.get("TaxHousePrice_Index"));
        taxHotelPrice_Index = Integer.parseInt(hashMapWithDeckInfo.get("TaxHotelPrice_Index"));
        absFieldNo_Index = Integer.parseInt(hashMapWithDeckInfo.get("AbsFieldNo_Index"));
        relMoving_Index = Integer.parseInt(hashMapWithDeckInfo.get("RelMoving_Index"));

    }

}
