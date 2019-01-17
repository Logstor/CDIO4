package model.board;

import model.board.fields.BoatField;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {


    @Test
    public void getNUMBEROFFIELDS() {
        Board board = new Board();
        assertEquals(board.getNUMBEROFFIELDS(),40);
    }
}