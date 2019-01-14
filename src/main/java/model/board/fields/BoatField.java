package model.board.fields;

import model.board.Field;
import model.cup.*;
import model.player.Player;

import java.awt.*;

/**
 * @author Rasmus Sander Larsen
 * @date 07-01-2019
 */
public class BoatField extends Field {

    /*
    -------------------------- Fields --------------------------
     */
    
    
    
    /*
    ----------------------- Constructor -------------------------
     */

    public BoatField(int fieldNo, String fieldType, String fieldName,String fieldDescription, int fieldCost, Color fieldColor) {
        super(fieldNo,fieldType,fieldName,fieldDescription,fieldCost,fieldColor);
        forSale=true;
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

        if (fieldOwner == null) {
            actionText = "Du køber dette felt for " + fieldCost + " pengesedler\n";
            setFieldOwner(player);
            player.updateBalance(-fieldCost);
        } else {
            actionText = "Du er landet på "+ fieldOwner + "'s felt \n Du betaler "+ rentByOwnersNoOfBoats()
                    + " pengesedler i husleje til "+ fieldOwner.getName();
            player.updateBalance(-rentByOwnersNoOfBoats());
            fieldOwner.updateBalance(rentByOwnersNoOfBoats());
        }

    }

    public void fieldAction (Player player, Cup Cup) {}
    
    /*
    ---------------------- Support Methods ----------------------
     */
    private int rentByOwnersNoOfBoats () {
        int variableRent;
        int OwnersNoOfBoats = fieldOwner.getBoatsOwned();

        switch (OwnersNoOfBoats) {
            case 1:
                variableRent = 500;
                break;

            case 2:
                variableRent = 1000;
                break;

            case 3:
                variableRent = 2000;
                break;

            case 4:
                variableRent = 4000;
                break;

            default:
                variableRent = 666;
                break;
        }
        return variableRent;
    }


}
