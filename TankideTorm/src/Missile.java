import java.lang.reflect.Array;
import java.util.ArrayList;

public class Missile {
	private Player owner;
	private int[] location;
	private int height;
	private static int splash = 1;
	private static int baseMissileDamage = 1;
	private static int missileDistTravelledPerTurn = 3;
	
	private static ArrayList<Missile> missileArray = new ArrayList<Missile>();
	
	
	public static int getLowestMissileHeight(int[] location){
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
							System.out.print("+");
						} else {
							System.out.print(height);
						}
						break;
					}
				}
				if (locationFound == 0) {
					System.out.print(GameBoard.getTileBoard(location));
				}
			}
			System.out.println();
		}
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
}
