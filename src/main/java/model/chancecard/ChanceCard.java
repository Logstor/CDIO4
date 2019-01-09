package model.chancecard;

import model.player.Player;

/**
 * @author Rasmus Sander Larsen
 * @date 09-01-2019
 */
public abstract class ChanceCard {

    /*
    -------------------------- Fields --------------------------
     */

    protected String title;
    protected String description;
    protected String cardType;
    
    /*
    ----------------------- Constructor -------------------------
     */

    protected ChanceCard (String cardType, String title) {

        this.cardType=cardType;
        this.title = title;

    }
    
    /*
    ------------------------ Properties -------------------------
     */

    // <editor-folder desc="Properties"


    // </editor-folder>
    
    /*
    ---------------------- Public Methods -----------------------
     */

    public abstract void cardAction(Player player);
    
    /*
    ---------------------- Support Methods ----------------------
     */


}
