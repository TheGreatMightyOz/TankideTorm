import java.util.ArrayList;

public class GameBoard{
	private static int isStorm = 0;
	public static int boardWidth;
	public static int getBoardWidth() {
		return boardWidth;
	}
	public static void setBoardWidth(int boardWidth) {
		GameBoard.boardWidth = boardWidth;
	}
	public static int getBoardHeight() {
		return boardHeight;
	}
	public static void setBoardHeight(int boardHeight) {
		GameBoard.boardHeight = boardHeight;
	}
	public static int boardHeight;
	
	private static ArrayList<ArrayList<Character>> board;
	private static ArrayList<Player> playerList;
	public static void setBoard(ArrayList<ArrayList<Character>> boardOrigin) {
		board = boardOrigin;
	}
	public static ArrayList<ArrayList<Character>> getBoard() {
		return board;
	}
	public static void changeMap(int[] location, Character sqType){
		board.get(location[0]).set(location[1], sqType);
	}
	public static void killPlayer(Player player){
		changeMap(player.getLocation(), '#');
		playerList.remove(player);
	}
}
