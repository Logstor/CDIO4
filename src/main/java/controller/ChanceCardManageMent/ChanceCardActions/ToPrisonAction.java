package controller.ChanceCardManageMent.ChanceCardActions;

import controller.ChanceCardManageMent.ChanceCardAction;
import controller.GeneralActionController;
import controller.GuiController;
import model.board.Board;
import model.board.Field;
import model.player.Player;

import java.util.HashMap;

/**
 * @author Rasmus Sander Larsen
 * @date 16-01-2019
 */
public class ToPrisonAction extends ChanceCardAction {

    /*
    -------------------------- Fields --------------------------
     */

    private Board board;
    
    /*
    ----------------------- Constructor -------------------------
     */
    
    public ToPrisonAction(GuiController guiController, HashMap<String,String> messageMap,
                          GeneralActionController generalActionController, Board board) {
        super(guiController,messageMap,generalActionController);
        this.board = board;
    
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
