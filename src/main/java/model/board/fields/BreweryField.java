package model.board.fields;

import model.board.Field;
import model.cup.*;
import model.player.Player;

import java.awt.*;

/**
 * @author Rasmus Sander Larsen
 * @date 07-01-2019
 */
public class BreweryField extends Field {

    /*
    -------------------------- Fields --------------------------
     */
    
    
    
    /*
    ----------------------- Constructor -------------------------
     */

    public BreweryField(int fieldNo, String fieldType, String fieldName,String fieldDescription, int fieldCost, Color fieldColor) {
        super(fieldNo,fieldType,fieldName,fieldDescription,fieldCost,fieldColor);
        forSale = true;
    }
    
    /*
    ------------------------ Properties -------------------------
     */

    // <editor-folder desc="Properties"


    // </editor-folder>
    
    /*
    ---------------------- Public Methods -----------------------
     */

    public void fieldAction (Player player) {

    }

    public void fieldAction (Player player, Cup cup) {

        if (owner == null) {
        actionText = "Du køber dette felt for " + fieldCost + " pengesedler\n";
        setOwner(player);
        player.updateBalance(-fieldCost);
        } else {
            actionText = "Du er landet på "+ owner + "'s felt \n Du betaler "+ rentByOwnersNoOfBreweries(cup.getCupValue())
                    + " pengesedler i husleje til "+ owner.getName();
            player.updateBalance(-rentByOwnersNoOfBreweries(cup.getCupValue()));
            owner.updateBalance(rentByOwnersNoOfBreweries(cup.getCupValue()));
        }
    }
    
    /*
    ---------------------- Support Methods ----------------------
     */

    private int rentByOwnersNoOfBreweries (int cupRoll) {
        int variableRent;
        int OwnersNoOfBreweries = owner.getBreweriesOwned();

        switch (OwnersNoOfBreweries) {
            case 1:
                variableRent = 100* cupRoll;
                break;

            case 2:
                variableRent = 200 * cupRoll;
                break;

            default:
                variableRent = 666;
                break;
        }
        return variableRent;
    }


}
