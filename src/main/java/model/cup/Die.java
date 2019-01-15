package model.cup;

import java.util.Random;

/**
 * @author Rasmus Sander Larsen
 * @date 07-01-2019
 */
public class Die {

    /*
    -------------------------- Fields --------------------------
     */

    private int faceValue;          // Value of model.die after roll
    private final int DIESIDES = 6; // Sides on model.die
    private int sides;              // Sides for custom sides on model.die
    private Random generator = new Random();
    
    /*
    ----------------------- Constructor -------------------------
     */

    /**
     * Default constructor, which creates a Die with 6 sides
     */
    public Die () {

        sides = DIESIDES;
    }

    /**
     * Alternate constructor, which creates a Die with "sides" amount of sides
     * @param sides int
     */
    public Die (int sides) {

        this.sides = sides;
    }

    
    /*
    ------------------------ Properties -------------------------
     */

    // <editor-folder desc="Properties"

    public int getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(int faceValue) {
        this.faceValue = faceValue;
    }

    public int getDIESIDES() {
        return DIESIDES;
    }

    public int getSides() {
        return sides;
    }

    public void setSides(int sides) {
        this.sides = sides;
    }


    // </editor-folder>
    
    /*
    ---------------------- Public Methods -----------------------
     */

    public void roll () {

        faceValue = generator.nextInt(sides)+1; // Random number om model.die with DIESIDES
    }

    public int toInteger () {
        return faceValue;
    }
    /*
    ---------------------- Support Methods ----------------------
     */


}
