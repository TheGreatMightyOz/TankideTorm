import java.util.ArrayList;

public class GameBoard{
	private static ArrayList<ArrayList<String>> board;
	private static ArrayList<Player> playerList;
	public static void setBoard(ArrayList<ArrayList<String>> boardOrigin) {
		board = boardOrigin;
	}
	public static ArrayList<ArrayList<String>> getBoard() {
		return board;
	}
}
