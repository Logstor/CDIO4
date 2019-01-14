package model.board.fields;

import model.board.Field;
import model.board.FieldTypeEnum;
import model.cup.*;
import model.player.Player;

import java.awt.*;

/**
 * @author Rasmus Sander Larsen
 * @date 07-01-2019
 */
public class PrisonField extends Field {

    /*
    -------------------------- Fields --------------------------
     */
    
    
    
    /*
    ----------------------- Constructor -------------------------
     */

    public PrisonField(int fieldNo, FieldTypeEnum fieldType, String fieldName, String fieldDescription, int fieldCost, Color fieldColor) {
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
        if (fieldNo==11) {
            actionText = "Du er på besøg i fængslet. Dit ophold er gratis.";
        } else if(fieldNo==31) {
            actionText = "Du er landet på fængslet og du bliver sendt direkte i fængselt, felt 11";
            player.setPrisonStat(1);
            player.setPosition(10);
        }

    }

    public void fieldAction (Player player, Cup Cup) {}
    
    /*
    ---------------------- Support Methods ----------------------
     */


}
