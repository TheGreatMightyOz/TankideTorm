import java.util.ArrayList;

public class Missile {
	private Player owner;
	private int[] location;
	private int height;
	private static int splash = 1;
	private static int baseMissileDamage = 1;
	private static int missileDistTravelledPerTurn = 5;
	
	private static ArrayList<Missile> missileArray = new ArrayList<Missile>(); // The array of all airborne missiles
	
	
	public static int getLowestMissileHeight(int[] location){ //Returns the height of the lowest missile targeting location
		int minHeight = 999999999;
		for (Missile missile: missileArray){
			if (missile.getLocation()[0] == location[0] && missile.getLocation()[1] == location[1]){
				if (missile.height < minHeight){
					minHeight = missile.height;
				}
			}
		}
		return minHeight;
	}
	
	public static void displayBoardMissiles(){
		char[][] map = new char[GameBoard.getBoardHeight()][GameBoard.getBoardWidth()];
		FormatOutput.setLastFrame(FormatOutput.set_empty(150, 50));
		ArrayList<int[]> missileLocations = new ArrayList<>();
		for(Missile missile: missileArray){ // Generates an array of possible missile locations
			missileLocations.add(missile.getLocation());
		}
		for (int i = 0; i<GameBoard.getBoardHeight(); i++){
			for (int j = 0; j < GameBoard.getBoardWidth(); j++){
				int[] location = {GameBoard.getBoardWidth()-1-i,j};
				int locationFound = 0;
				for (int[] locMissile : missileLocations){
					if (locMissile[0] == location[0] && locMissile[1] == location[1]){
						locationFound = 1;
						int height = getLowestMissileHeight(location);
						if (height > 9){
							map[i][j]='+';
							//System.out.print("+");
						} else {
							map[i][j]=(char)(height+48);
							//System.out.print(height);
						}
						break;
					}
				}
				if (locationFound == 0) {
					map[i][j]=GameBoard.getTileBoard(location);
					//System.out.print(GameBoard.getTileBoard(location));
				}
			}
			//System.out.println();
		}
		GraphicOutput.OutCharArray(FormatOutput.set_map(map));
	}
	
	public static void tickDownMissiles(Player player){ // Ticks down all missiles launched by player
		ArrayList<Missile> missilesToBeRemoved = new ArrayList<Missile>();
		for (Missile missile: missileArray){
			if (missile.owner == player){
				missile.height -= 1;
				if (missile.height == 0){
					ArrayList<Player> playersDamaged = GameBoard.getPlayersSurroundingTile(missile.location, splash);
					for (Player playerDamaged: playersDamaged){
						playerDamaged.takeDamage(baseMissileDamage);
					}
					missilesToBeRemoved.add(missile);
				}
			}
		}
		for (Missile missile: missilesToBeRemoved){
			missileArray.remove(missile);
		}
	}
	
	public static void addMissile(Player player, int[] location, int height){ // Adds a missile targeting tile at location with height as turns until impact
		missileArray.add(new Missile(player, location, height));
		// boardTargeted.get(location[0]).get(location[1]).add(height);
		// System.out.println("New missile added at location " +location[0] +" " +location[1] + " at height "+ height);
	}
	
	public static void removeMissile(Missile missile){
		missileArray.remove(missile);
	}
	
	public Missile(Player player, int[] location, int height){
		owner = player;
		this.location = location;
		this.height = height;
	}
	
	public int[] getLocation(){
		return location;
	}

	public static int getSplash() {
		return splash;
	}

	public static int getBaseMissileDamage() {
		return baseMissileDamage;
	}

	public static int getMissileDistTravelledPerTurn() {
		return missileDistTravelledPerTurn;
	}
	
}
