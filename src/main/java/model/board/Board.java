package model.board;

/**
 * @author Rasmus Sander Larsen
 * @date 07-01-2019
 */
public class Board {

    /*
    -------------------------- Fields --------------------------
     */

    private Field[] board;
    private final int NUMBEROFFIELDS = 40;
    
    /*
    ----------------------- Constructor -------------------------
     */
    
    public Board () {
        board = new Field[NUMBEROFFIELDS];
    }
    
    /*
    ------------------------ Properties -------------------------
     */

    // <editor-folder desc="Properties"

    public Field[] getBoard() {
        return board;
    }

    public void setBoard(Field[] board) {
        this.board = board;
    }

    public int getNUMBEROFFIELDS() {
        return NUMBEROFFIELDS;
    }


    // </editor-folder>
    
    /*
    ---------------------- Public Methods -----------------------
     */
    
    
    
    /*
    ---------------------- Support Methods ----------------------
     */


}
