import java.util.ArrayList;
import java.util.List;

public class GameBoard{
	private static int isStorm = 0;
	private static int boardWidth;
	public static int boardHeight;
	private static char startingTile = '$';
	
	private static char filledTile = '#';
	private static char emptyTile = '.';
	private static int baseRamDamage = 1;

	
	
	private static ArrayList<Player> playerList = new ArrayList<Player>();
	private static ArrayList<ArrayList<Character>> board;

	
	
	
	public static void displayBoard(){ // Prints out the board (temporary method)
		for(int i = 0; i < boardHeight; i++){
			for (int j = 0; j < boardWidth; j++){
				int[] location = {boardHeight-i-1, j};
				System.out.print(getTileBoard(location));
			}
			System.out.println();
		}
	}
	
	public static void removeStartingTiles(){ // Removes the tiles that mark starting tiles from the board
		ArrayList<int[]> startingTiles = findStartingTiles();
		for (int[] location : startingTiles){
			changeTileBoard(location, emptyTile);
		}
	}
	
	public static void startGame(){ // The method for the match
		int isGame = 1;
		removeStartingTiles();
		while (isGame == 1){
			nextTurn();
			if (playerList.size() == 0){
				isGame = 0;
				System.out.println("The game has ended!");
			}
		}
	}
	
	
	public static char getTileBoard(int[] location){ // Returns type of tile at location 
		return board.get(location[0]).get(location[1]);
	}
	

	
	
	public static ArrayList<int[]> findStartingTiles(){ // Creates list of the locations of all starting tiles
		ArrayList<int[]> startingTiles = new ArrayList<int[]>();
		for(int x = 0; x < boardWidth; x++){
			for(int y = 0; y < boardHeight; y++){
				int[] location = {x,y};
				if (GameBoard.getTileBoard(location)==startingTile){
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
	

	
	public static void changeTileBoard(int[] location, Character sqType){ // Changes the tile type in location to sqType
		board.get(location[0]).set(location[1], sqType);
	}
	public static void killPlayer(Player player){ // Kills player. Leaves behind wreck.
		changeTileBoard(player.getLocation(), filledTile);
		playerList.remove(player);
	}
	

	public static Player getPlayer(char icon){
		for(Player player: playerList){
			if (player.getIcon() == icon){
				return player;
			}
		}
		System.out.println("Something has gone wrong. This player does not exist! ");  // Program shouldn't reach this far
		return playerList.get(0);
	}
	
	public static ArrayList<Player> getPlayersSurroundingTile(int[] location, int splash){ // Returns a list of all players surrounding tile at location in splash radius
		//System.out.println("Checking location: "+location[0] + " " + location[1]);
		ArrayList<Player> playersSur = new ArrayList<Player>();
		for (int x = location[0]-splash; x < location[0] + splash +1; x++){
			for (int y = location[1]-splash; y < location[1] + splash +1; y++){
				if (x < boardWidth && y < boardHeight && x >= 0 && y >= 0){
					int[] locationTemp = {x, y};
					//System.out.println("Checking squares: "+locationTemp[0] + " " + locationTemp[1]);
					if (getTileBoard(locationTemp) != filledTile && getTileBoard(locationTemp) != emptyTile){
						//System.out.println("Player " + getPlayer(getTileBoard(locationTemp)).getName() + " is on tile "+ locationTemp[0] + " " + locationTemp[1]);
						playersSur.add(getPlayer(getTileBoard(locationTemp)));
					}
				}
				
			}
		}
		return playersSur;
	}
	
	
	
	public static void nextTurn(){ // Method for taking one turn
		ArrayList<Player> tempPlayerList = new ArrayList<Player>();
		for(Player player: playerList){
			tempPlayerList.add(player);
		}
		for(Player player: tempPlayerList){
			Missile.tickDownMissiles(player);
			displayBoard();
			Missile.displayBoardMissiles();
			// displayBoardTargeted();
			if (playerList.contains(player)){ // If the player was destroyed before he took a turn, this will ensure that he can't take a turn
				player.takeTurn();
			}
		}
		// refreshTurn(); // Was used for updating missiles, now redundant due to tickDownMissiles()
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

	public static char getStartingTileType() {
		return startingTile;
	}

	public static char getFilledTile() {
		return filledTile;
	}

	public static char getEmptyTile() {
		return emptyTile;
	}

	public static int getBaseRamDamage() {
		return baseRamDamage;
	}

	
}
