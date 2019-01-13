package model.player;

import java.awt.*;

/**
 * @author Rasmus Sander Larsen
 * @date 13-01-2019
 */
public class Token {

    /*
    -------------------------- Fields --------------------------
     */
    
    private String carType;
    private String carPattern;
    private Color carColor, patternColor;
    
    /*
    ----------------------- Constructor -------------------------
     */

    /**
     * Standard Token: Only the color of the token is possible to change.
     * @param carColor color of the Token.
     */

    public Token (Color carColor) {
        carType = "CAR";
        carPattern = "FILL";
        this.carColor = carColor;
        patternColor = carColor;
    }
    
    /*
    ------------------------ Properties -------------------------
     */

    // <editor-folder desc="Properties"

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCarPattern() {
        return carPattern;
    }

    public void setCarPattern(String carPattern) {
        this.carPattern = carPattern;
    }

    public Color getCarColor() {
        return carColor;
    }

    public void setCarColor(Color carColor) {
        this.carColor = carColor;
    }

    public Color getPatternColor() {
        return patternColor;
    }

    public void setPatternColor(Color patternColor) {
        this.patternColor = patternColor;
    }

    // </editor-folder>
    
    /*
    ---------------------- Public Methods -----------------------
     */
    
    
    
    /*
    ---------------------- Support Methods ----------------------
     */


}
