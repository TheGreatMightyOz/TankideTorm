import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Player {
	private int[] location;
	private String name; // Names' first letters have to be unique!
	private char icon; // The first letter of the name, the letter that is displayed on the map
	private int hitpoints;
	private static int startingHP = 3;
	private static String[] tempDirs = {"N", "NE", "E", "SE", "S", "SW", "W", "NW"};
	private static List<String> legalDirs = Arrays.asList(tempDirs);
	
	
	public int[] getLocation() {
		return location;
	}
	public void setLocation(int[] location) {
		this.location = location;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
		this.icon = name.charAt(0);
	}
	public char getIcon() {
		return icon;
	}
	public Player(int[] location, String name) {
		super();
		this.hitpoints = startingHP;
		this.location = location;
		this.name = name;
		this.icon = name.toUpperCase().charAt(0);
	}
	public void takeDamage(int damage){ // Causes this player to take damage
		hitpoints -= damage;
		System.out.println(this.getName() +" took damage! He has " + hitpoints + " durability left!");
		if (hitpoints <= 0){
			System.out.println(this.getName() + " has been destroyed!");
			GameBoard.killPlayer(this);
		}
	}
	
	public void move(int[] location){ // Moves this player to target location on the board
		GameBoard.changeTileBoard(this.getLocation(),  GameBoard.getEmptyTile());
		GameBoard.changeTileBoard(location, this.getIcon());
		this.setLocation(location);
		
	}
	
	public void takeTurn(){ // Method for taking a turn
		Scanner sc = new Scanner(System.in);
		
		String cmd = "";
		while (!cmd.equals("move") && !cmd.equals("fire")){ // Asks until it receives move or fire as a command
			System.out.println(name + "'s turn. Input your command: ");
			cmd = sc.next().toLowerCase();
		}
		
		String dir = "";
		int tempVar = 0;
		while (!legalDirs.contains(dir)){ // Asks until it receives correct input
			if (tempVar == 1){
				System.out.println("Direction: ");
			}
			tempVar = 1;
			dir = sc.nextLine().toUpperCase();
		}
		
		int[] curLoc = this.getLocation();
		int newX = curLoc[0];
		int newY = curLoc[1];
		int length = -1; // Length moved/fired
		
		if (cmd.equals("fire")){
			while (length < 0) {
				System.out.println("How far do you wish to aim? ");
				length = sc.nextInt();
			}
		}
		
		if (cmd.equals("move")){
			length = 1;
		}
		if (dir.contains("N")){
			newX = curLoc[0]+length;
		} else {
			if (dir.contains("S")){
				newX = curLoc[0]-length;
			}
		}
		if (dir.contains("E")){
			newY = curLoc[1]+length;
		} else {
			if (dir.contains("W")){
				newY = curLoc[1]-length;
			}
		}
		
		
		newX = Math.min(GameBoard.getBoardWidth()-1, newX); // Limits the targeted tile to stay inside the map
		newX = Math.max(0, newX);
		newY = Math.min(GameBoard.getBoardHeight()-1, newY);
		newY = Math.max(0, newY);
		
		int[] newLoc = {newX, newY};
		if (cmd.equals("move")){
			if (GameBoard.getTileBoard(newLoc) == GameBoard.getEmptyTile()){
				this.move(newLoc);
			} else {
				// System.out.println("Your location: " + location[0] +" "+ location[1] + " " +GameBoard.getTileBoard(location));
				// System.out.println("Your target: " + newLoc[0] +" "+ newLoc[1] + " " +GameBoard.getTileBoard(newLoc));
				System.out.println("Something was in the way! ");
				this.takeDamage(GameBoard.getBaseRamDamage());
				if (GameBoard.getTileBoard(newLoc) != GameBoard.getFilledTile()){
					GameBoard.getPlayer(GameBoard.getTileBoard(newLoc)).takeDamage(Missile.getBaseMissileDamage());
				}
			}
		}
		if (cmd.equals("fire")){
			if (length == 0) {
				Missile.addMissile(this, newLoc, 1); // Minimum height for missile
			} else {
				Missile.addMissile(this, newLoc, (length+Missile.getMissileDistTravelledPerTurn()-1)/Missile.getMissileDistTravelledPerTurn());
			}
		}

		
		//sc.close();
	}
}
