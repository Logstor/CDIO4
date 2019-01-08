package model.Cup;

import model.cup.Cup;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Alfred Röttger Rydahl
 * @date 05-11-2018
 */
public class CupTest {

    // Cup with 2 conventional 6 sided dies
    Cup cup = new Cup();
    // Cup with 4, 12 sided dies
    Cup cup12 = new Cup(4, 12);


    @Test
    public void cupRoll() {
        /*
        Roll the dies in the conventional cup and put the value into "first".
        Check that the roll is in the correct range.
        Check that the roll actually is different from time to time - THIS WILL EVENTUALLY FAIL
         */
        int first = cup.cupRoll();
        assertTrue(first > 1 && first < 13);
        assertTrue(first != cup.cupRoll());     // CAN FAIL

        /*
        Roll the dies in the pervert cup and put them into "first".
        Check that the roll is in the correct range.
        Check that the roll actually is different from time to time - THIS WILL EVENTUALLY FAIL
         */
        first = cup12.cupRoll();
        assertTrue(first > 3 && first < 49);
        assertTrue(first != cup12.cupRoll());   // CAN FAIL

    }

    @Test
    public void getCupValue() {

        // Roll the cups and put result into "roll" and "roll12"
        int roll = cup.cupRoll();
        int roll12 = cup12.cupRoll();

        // Check the methods
        assertEquals(roll, cup.getCupValue());
        assertEquals(roll12, cup12.getCupValue());

    }

    @Test
    public void setCupValue() {

        // Set the "cupValue" to 6 in both cups
        cup.setCupValue(6);
        cup12.setCupValue(6);

        // Check both cups
        assertEquals(6, cup.getCupValue());
        assertEquals(6, cup12.getCupValue());

    }
}