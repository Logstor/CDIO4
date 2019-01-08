package model.player;

/**
 * @author Rasmus Sander Larsen
 * @date 07-01-2019
 */
public class Account {

    /*
    -------------------------- Fields --------------------------
     */
    
    private int balance;
    
    /*
    ----------------------- Constructor -------------------------
     */


    public Account (int initializeBalance) {
        balance = initializeBalance;
    }
    
    /*
    ------------------------ Properties -------------------------
     */

    // <editor-folder desc="Properties"

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }


    // </editor-folder>
    
    /*
    ---------------------- Public Methods -----------------------
     */
    
    public void updateBalance (int balanceToUpdateScoreWith) {
        balance+= balanceToUpdateScoreWith;
    }
    
    /*
    ---------------------- Support Methods ----------------------
     */


}
