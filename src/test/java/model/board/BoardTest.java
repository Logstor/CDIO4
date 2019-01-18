package model.board;

import model.board.fields.BoatField;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Søren Poulsen
 * @date 16-01-2019
 */

public class BoardTest {


    @Test
    public void getNUMBEROFFIELDS() {
        Board board = new Board();
        assertEquals(board.getNUMBEROFFIELDS(),40);
    }
}