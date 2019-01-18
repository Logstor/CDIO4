package model.board.fields;

import controller.GuiController;
import model.board.Field;
import model.board.FieldTypeEnum;
import model.chancecard.ChanceCard;
import model.chancecard.Deck;
import model.cup.Cup;
import model.player.Player;
import view.gui.Gui;

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

    public ChanceField(int fieldNo, FieldTypeEnum fieldType, String fieldName, String fieldDescription, int fieldCost, Color fieldColor) {
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
    
    /*
    ---------------------- Support Methods ----------------------
     */


}
