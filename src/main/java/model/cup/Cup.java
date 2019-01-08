package model.cup;

/**
 * @author Rasmus Sander Larsen
 * @date 07-01-2019
 */
public class Cup {

    /*
    -------------------------- Fields --------------------------
     */
    
    private int cupValue;
    private Die[] dies;
    
    /*
    ----------------------- Constructor -------------------------
     */

    public Cup (int noOfDies, int sidesOnDies) {
        // Initialize
        dies = new Die[2];

        // Fill the array with 2 dies
        for( int i=0 ; i < dies.length ; i++) {
            dies[i] = new Die();
        }
    }
    
    
    /*
    ------------------------ Properties -------------------------
     */

    // <editor-folder desc="Properties"

    public int getCupValue() {
        return cupValue;
    }

    public void setCupValue(int cupValue) {
        this.cupValue = cupValue;
    }

    public Die[] getDies() {
        return dies;
    }

    public void setDies(Die[] dies) { this.dies = dies; }


    // </editor-folder>
    
    /*
    ---------------------- Public Methods -----------------------
     */
    
    
    
    /*
    ---------------------- Support Methods ----------------------
     */


}
