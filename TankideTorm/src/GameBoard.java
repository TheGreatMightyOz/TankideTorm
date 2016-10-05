import java.util.ArrayList;
import java.util.List;

public class GameBoard{
	private static int isStorm = 0;
	private static int boardWidth;
	public static int boardHeight;

	private static ArrayList<Player> playerList;
	private static ArrayList<ArrayList<Character>> board;
	

	public static char getTile(int[] location){ // Returns type of tile at location 
		return board.get(location[0]).get(location[1]);
	}
	
	public static ArrayList<int[]> findStartingTiles(){ // Creates list of the locations of all starting tiles
		ArrayList<int[]> startingTiles = new ArrayList<int[]>();
		for(int x = 0; x < boardWidth; x++){
			for(int y = 0; y < boardHeight; y++){
				int[] Loc = {x,y};
				if (GameBoard.getTile(Loc)=='1'){
					startingTiles.add(Loc);
				}
			}
		}
		return startingTiles;
	}
	public static int[] getRandomStartingTile(){ // Returns a random starting tile from the list of all starting tiles
		ArrayList<int[]> startingTiles = GameBoard.findStartingTiles();
		int size = startingTiles.size();
		int randomIndex = (int)(Math.random()*size);
		int[] tileLoc = startingTiles.get(randomIndex);
		changeTile(tileLoc, '#');
		return tileLoc;
	}
	
	public static void genPlayerList(String[] playerNames){ // Generates locations and icons for players from the list of names given
		for (String name: playerNames){
			int[] Loc = GameBoard.getRandomStartingTile();
			playerList.add(new Player(Loc, name));
		}
	}
	
	
	public static void spawnPlayers(){ // Spawns the players in their starting locations
		for (Player player: playerList){
			changeTile(player.getLocation(), player.getIcon());
		}
	}
	
	public static void changeTile(int[] location, Character sqType){ // Changes the tile type in location to sqType
		board.get(location[0]).set(location[1], sqType);
	}
	public static void killPlayer(Player player){ // Kills player. Leaves behind wreck.
		changeTile(player.getLocation(), '#');
		playerList.remove(player);
	}
	public static void setIsStorm(int nr){
		isStorm = nr;
	}
	
	public static int getIsStorm(){
		return isStorm;
	}
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
	
	public static void setPlayerList(List<Player> list){
		for (Player player : list){
			playerList.add(player);
		}
	}
	
	public static void setBoard(ArrayList<ArrayList<Character>> boardOrigin) {
		board = boardOrigin;
	}
	
	public static ArrayList<ArrayList<Character>> getBoard() {
		return board;
	}
	
}
