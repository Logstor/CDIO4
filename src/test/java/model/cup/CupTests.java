package model.cup;

import org.junit.Test;

import static org.junit.Assert.*;

public class CupTests {
    // Cup with 2 conventional 6 sided dies
    Cup cup = new Cup();
    // Cup with 4, 12 sided dies
    Cup cup6 = new Cup(2, 6);


    @Test
    public void cupRoll() {
        /*
        Roll the dies in the conventional cup and put the value into "first".
        Check that the roll is in the correct range.
        Check that the roll actually is different from time to time - THIS WILL EVENTUALLY FAIL
         */
        int first = cup.cupRoll();
        assertTrue(first > 0 && first < 13);
        assertTrue(first != cup.cupRoll());     // CAN FAIL

        /*
        Roll the dies in the pervert cup and put them into "first".
        Check that the roll is in the correct range.
        Check that the roll actually is different from time to time - THIS WILL EVENTUALLY FAIL
         */
        first = cup6.cupRoll();
        assertTrue(first > 1 && first < 13);
        assertTrue(first != cup6.cupRoll());   // CAN FAIL

    }

    @Test
    public void getCupValue() {

        // Roll the cups and put result into "roll" and "roll12"
        int roll = cup.cupRoll();
        int roll12 = cup6.cupRoll();

        // Check the methods
        assertEquals(roll, cup.getCupValue());
        assertEquals(roll12, cup6.getCupValue());

    }

    @Test
    public void setCupValue() {

        // Set the "cupValue" to 6 in both cups
        cup.setCupValue(6);
        cup6.setCupValue(6);

        // Check both cups
        assertEquals(6, cup.getCupValue());
        assertEquals(6, cup6.getCupValue());

    }
}