import model.board.Board;
import model.board.BoardManager;
import model.board.Field;
import model.chancecard.Deck;
import model.chancecard.DeckManager;
import model.reader.Reader;

import java.util.HashMap;

/**
 * @author Alfred Röttger Rydahl
 */

public class Main {

	public static void main(String[] args) {

		HashMap<String,String> boardMap = new HashMap<>();

		Reader boardReader = new Reader("board1.45.csv", ";");

		boardReader.readFileIntoHashMap(boardMap);

		System.out.println(boardMap.get("field1"));

		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");

		Board playingBoard = new Board();
		BoardManager boardManager = new BoardManager();
		boardManager.setupBoard(boardMap,playingBoard);

		Field tempField = playingBoard.getBoard()[3];
		System.out.println(tempField);

		System.out.println("Hello World");
		String filePath;
		filePath = ClassLoader.getSystemClassLoader().getResource("chanceCard.csv").getFile().
				replaceAll("/","\\");
	System.out.println(filePath);
		//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
		HashMap<String,String> chanceCardMap = new HashMap<>();
		Reader chanceCardReader = new Reader("src\\main\\resources\\","chanceCard_Packed.csv", ";");
		chanceCardReader.readFileIntoHashMap(chanceCardMap);

		Deck chanceCardDeck = new Deck();
		DeckManager deckManager = new DeckManager();
		deckManager.setupBeck(chanceCardMap,chanceCardDeck);


	}
}
