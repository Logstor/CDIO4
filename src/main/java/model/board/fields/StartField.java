package model.board.fields;

import model.board.Field;
import model.player.Player;
import model.cup.*;

import java.awt.*;

/**
 * @author Rasmus Sander Larsen
 * @date 07-01-2019
 */
public class StartField extends Field {

    /*
    -------------------------- Fields --------------------------
     */
    
    
    
    /*
    ----------------------- Constructor -------------------------
     */
    
    public StartField (int fieldNo, String fieldType, String fieldName,String fieldDescription, int fieldCost, Color fieldColor) {
        super(fieldNo, fieldType, fieldName,fieldDescription, fieldCost, fieldColor);

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
        actionText="Du passer start og modtager 4000 kr.";
    }
    public void fieldAction (Player player, Cup cup) {}
    
    /*
    ---------------------- Support Methods ----------------------
     */


}
