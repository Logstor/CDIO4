package model.player;

import java.awt.*;

/**
 * @author Rasmus Sander Larsen
 * @date 07-01-2019
 */
public class Player {

    /*
    -------------------------- Fields --------------------------
     */

    private Account account;
    private String name;
    private boolean hasLost = false;
    private int BreweriesOwned = 0;
    private int BoatsOwned = 0;
    private boolean inPrison;
    private int totalPosition;
    private int position;
    private Token token;
    
    /*
    ----------------------- Constructor -------------------------
     */
    
    public Player (String name, Color color, int initialBalance, int startingPosition) {
        this.name=name;
        account = new Account(initialBalance);
        totalPosition= startingPosition;
        token = new Token(color);
    }
    
    /*
    ------------------------ Properties -------------------------
     */

    // <editor-folder desc="Properties"

    public boolean isHasLost() {
        return hasLost;
    }

    public void setHasLost(boolean hasLost) {
        this.hasLost = hasLost;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBreweriesOwned() {
        return BreweriesOwned;
    }

    public void setBreweriesOwned(int breweriesOwned) {
        BreweriesOwned = breweriesOwned;
    }

    public int getBoatsOwned() {
        return BoatsOwned;
    }

    public void setBoatsOwned(int boatsOwned) {
        BoatsOwned = boatsOwned;
    }

    public boolean isInPrison() {
        return inPrison;
    }

    public void setInPrison(boolean inPrison) {
        this.inPrison = inPrison;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getTotalPosition() {
        return totalPosition;
    }

    public void setTotalPosition(int totalPosition) {
        this.totalPosition = totalPosition;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

// </editor-folder>
    
    /*
    ---------------------- Public Methods -----------------------
     */
    
    public void updateBalance (int amountToUpdateBalanceWith) {
        account.updateBalance(amountToUpdateBalanceWith);
        if (account.getBalance()<=0) {
            hasLost=true;
        }
    }

    /**
     * This method updates the total position and the boardPosition.
     * @param moves antallet af felter der skal rykkes.
     */
    public void updatePosition (int moves) {
        totalPosition += moves;
        position = totalPosition % 40;
    }
    
    /*
    ---------------------- Support Methods ----------------------
     */

}
