package model.die;

import org.junit.Test;



import static org.junit.Assert.*;

public class DieTest {

    // Testing object for conventional dice
    Die die = new Die();
    // Testing object for 12 sided dice
    Die die12 = new Die(12);

    @Test
    public void roll() {

        int amountOfRolls = 600000;                       // Total amount the dice will be rolled

        int[] conValues  = new int[die.getSides()];       // Array to hold the conventional dice values
        int[] diffValues = new int[die12.getSides()];     // Array to hold the 12 sided dice values

        // Max deviation limits for the conventional dice
        int upperBoundCon = (int) ( (amountOfRolls/die.getSides() ) * 1.04 );   // 4% above even
        int lowerBoundCon = (int) ( (amountOfRolls/die.getSides() ) * 0.96 );   // 4% below even


        // Max deviation limits for the 12 sided dice
        int upperBound12 = (int) ( (amountOfRolls/die12.getSides() ) * 1.04 );  // 4% above even
        int lowerBound12 = (int) ( (amountOfRolls/die12.getSides() ) * 0.96 );  // 4% below even

        /*
        Test conventional dice
         */
        for ( int i=0 ; i < amountOfRolls ; i++ ) {

            // Roll the dice and put it into conValues
            die.roll();
            conValues[die.getFaceValue()-1] += 1;          // Minus one because of index
        }

        for ( int value : conValues ) {
            System.out.println(value);
            assertTrue(value > lowerBoundCon && value < upperBoundCon);
        }

        System.out.println();

        /*
        Test 12 sided dice
         */
        for ( int i=0 ; i < amountOfRolls ; i++ ) {

            // Roll the dice and put it into diffValues
            die12.roll();
            diffValues[die12.getFaceValue()-1] += 1;          // Minus one because of index
        }

        for ( int value : diffValues ) {
            System.out.println(value);
            assertTrue(value > lowerBound12 && value < upperBound12);
        }


    }

    @org.junit.Test
    public void getFaceValue() {

        // Set expectations
        int faceValue = 1;
        die.setFaceValue(faceValue);

        // Test it
        assertEquals( faceValue, die.getFaceValue() );
    }

    @org.junit.Test
    public void setFaceValue() {

        // Set expectations
        int faceValue = 5;
        die.setFaceValue(faceValue);

        // Test it
        assertEquals( faceValue, die.getFaceValue() );
    }

    @org.junit.Test
    public void getSides() {
        assertEquals( 12, die12.getSides() );
    }

    @org.junit.Test
    public void setSides() {

        int sides = 15;

        // Change amount of sides
        die12.setSides(sides);

        // Test
        assertEquals( sides, die12.getSides() );
    }
}