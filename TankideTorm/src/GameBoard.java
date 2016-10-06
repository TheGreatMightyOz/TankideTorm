import java.util.ArrayList;
import java.util.List;

public class GameBoard{
	private static int isStorm = 0;
	private static int boardWidth;
	public static int boardHeight;
	private static char startingTileType = '$';
	private static int splash = 1;
	private static char filledTile = '#';
	private static char emptyTile = '.';
	private static int baseDamage = 1;
	
	
	private static ArrayList<Player> playerList;
	private static ArrayList<ArrayList<Character>> board;
	private static ArrayList<ArrayList<ArrayList<Integer>>> boardTargeted = new ArrayList<ArrayList<ArrayList<Integer>>>();

	

	public static char getTileBoard(int[] location){ // Returns type of tile at location 
		return board.get(location[0]).get(location[1]);
	}
	
	public static ArrayList<Integer> getTileBoardTargeted(int[] location){
		return boardTargeted.get(location[0]).get(location[1]);
	}
	
	
	public static ArrayList<int[]> findStartingTiles(){ // Creates list of the locations of all starting tiles
		ArrayList<int[]> startingTiles = new ArrayList<int[]>();
		for(int x = 0; x < boardWidth; x++){
			for(int y = 0; y < boardHeight; y++){
				int[] location = {x,y};
				if (GameBoard.getTileBoard(location)==startingTileType){
					startingTiles.add(location);
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
		changeTileBoard(tileLoc, filledTile);
		return tileLoc;
	}
	
	public static void genPlayerList(String[] playerNames){ // Generates locations and icons for players from the list of names given
		for (String name: playerNames){
			int[] location = GameBoard.getRandomStartingTile();
			playerList.add(new Player(location, name));
		}
	}
	
	
	public static void spawnPlayers(){ // Spawns the players in their starting locations
		for (Player player: playerList){
			changeTileBoard(player.getLocation(), player.getIcon());
		}
	}
	
	public static void changeTileBoardTargeted(int[] location, int sqInt){ // 3-dimensional location is needed here
		boardTargeted.get(location[0]).get(location[1]).set(location[2], sqInt);
	}
	
	public static void changeTileBoard(int[] location, Character sqType){ // Changes the tile type in location to sqType
		board.get(location[0]).set(location[1], sqType);
	}
	public static void killPlayer(Player player){ // Kills player. Leaves behind wreck.
		changeTileBoard(player.getLocation(), filledTile);
		playerList.remove(player);
	}
	
	public static void genTilesTargeted(){ // Generates a new board that shows which tiles are being targeted
		ArrayList<ArrayList<Integer>> lineTemp = new ArrayList<ArrayList<Integer>>();
		for (int x = 0; x < boardWidth; x++){
			lineTemp.add(new ArrayList<Integer>());
		}
		for (int y = 0; y < boardHeight; y++){
			boardTargeted.add(lineTemp);
		}
	}
	
	public static Player getPlayer(char icon){
		for(Player player: playerList){
			if (player.getIcon() == icon){
				return player;
			}
		}
		System.out.println("Midagi on valesti läinud. Sellist mängijat ei eksisteeri. ");  // Program shouldn't reach this far
		return playerList.get(0);
	}
	
	public static ArrayList<Player> getPlayersSurroundingTile(int[] location, int splash){
		ArrayList<Player> playersSur = new ArrayList<Player>();
		for (int x = location[0]-splash; x < location[0] + splash +1; x++){
			for (int y = location[0]-splash; y < location[0] + splash +1; y++){
				if (x < boardWidth && y < boardHeight){
					int[] locationTemp = {x, y};
					if (getTileBoard(locationTemp) != filledTile && getTileBoard(locationTemp) != emptyTile){
						playersSur.add(getPlayer(getTileBoard(locationTemp)));
					}
				}
				
			}
		}
		return playersSur;
	}
	
	public static void refreshTurn(){
		for (int x = 0; x < boardWidth; x++){
			for (int y = 0; y < boardHeight; y++){
				int[] location = {x, y};
				for (int z = 0; z < getTileBoardTargeted(location).size(); z++){
					int[] location3d = {x, y, z};
					int tileTargeted = getTileBoardTargeted(location).get(z);
					if (tileTargeted > 0) {
						changeTileBoardTargeted(location3d, tileTargeted-1);
					} else {
						boardTargeted.get(x).get(y).remove(z);
					}
				
					if (getTileBoardTargeted(location).get(z) == 0){
						for (Player player: getPlayersSurroundingTile(location, splash)){
							player.takeDamage(baseDamage);
						}
					}
				}
			}
		}
	}
	
	public static void nextTurn(){
		for(Player player: playerList){
			player.takeTurn();
		}
		refreshTurn();
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
