import model.board.Board;
import model.board.BoardManager;
import model.board.Field;
import model.reader.Reader;

import java.util.HashMap;

/**
 * @author Alfred Röttger Rydahl
 */

public class Main {

	public static void main(String[] args) {

		HashMap<String,String> boardMap = new HashMap<>();

		Reader reader = new Reader("board1.3.csv", ";");

		reader.readFileIntoHashMap(boardMap);

		System.out.println(boardMap.get("field1"));

		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");

		Board playingBoard = new Board();
		BoardManager boardManager = new BoardManager();
		boardManager.setupBoard(boardMap,playingBoard);

		Field tempField = playingBoard.getBoard()[3];
		System.out.println(tempField);

		System.out.println("Hello World");


	}
}
