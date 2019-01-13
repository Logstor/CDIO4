import controller.MainControl;
import model.board.Board;
import model.board.BoardManager;
import model.board.Field;
import model.chancecard.Deck;
import model.chancecard.DeckManager;
import model.reader.Reader;
import view.gui.Gui;

import java.util.HashMap;

/**
 * @author Alfred Röttger Rydahl
 */

public class Main {

    public static void main(String[] args) {

        HashMap<String, String> boardMap = new HashMap<>();

        Reader boardReader = new Reader("boardV1.csv", ";");

        boardReader.readFileIntoHashMap(boardMap);
        System.out.println(boardReader.getFilePath());

        System.out.println(boardMap.get("field1"));

        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");

        Board playingBoard = new Board();
        BoardManager boardManager = new BoardManager();
        boardManager.setupBoard(boardMap, playingBoard);

        Field tempField = playingBoard.getBoard()[3];
        System.out.println(tempField);

        //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
        HashMap<String,String> chanceCardMap = new HashMap<>();
        Reader chanceCardReader = new Reader("chanceCardV1.csv", ";");
        chanceCardReader.readFileIntoHashMap(chanceCardMap);

        Deck chanceCardDeck = new Deck();
        DeckManager deckManager = new DeckManager();
        deckManager.setupBeck(chanceCardMap,chanceCardDeck);

        System.out.println(chanceCardDeck.getChanceCardDeck().get(3).toString());
        Gui gui = new Gui(playingBoard.getBoard());
    }
}