package model.board.fields;

import model.board.Field;
import model.cup.Cup;
import model.player.Player;

import java.awt.*;

/**
 * @author Rasmus Sander Larsen
 * @date 07-01-2019
 */
public class ChanceField extends Field {

    /*
    -------------------------- Fields --------------------------
     */



    /*
    ----------------------- Constructor -------------------------
     */

    public ChanceField(int fieldNo, String fieldType, String fieldName,String fieldDescription, int fieldCost, Color fieldColor) {
        super(fieldNo,fieldType,fieldName,fieldDescription,fieldCost,fieldColor);
        forSale = false;
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

        System.out.println("Du er landet på chancefeltet og trækker et chancekort");

    }

    public void fieldAction (Player player, Cup Cup) {}
    
    /*
    ---------------------- Support Methods ----------------------
     */


}
