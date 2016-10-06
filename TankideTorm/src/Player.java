import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Player {
	private int[] location;
	private String name;
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
		this.icon = name.charAt(0);
	}
	public void takeDamage(int damage){
		hitpoints -= damage;
		if (hitpoints <= 0){
			GameBoard.killPlayer(this);
		}
	}
	
	public void move(int[] location){
		GameBoard.changeTileBoard(this.getLocation(),  GameBoard.getEmptyTile());
		GameBoard.changeTileBoard(location, this.getIcon());
		this.setLocation(location);
		
	}
	
	public void takeTurn(){ // Method for taking a turn
		Scanner sc = new Scanner(System.in);
		
		String cmd = "";
		while (!cmd.equals("move") && !cmd.equals("fire")){
			System.out.println(name + " käik. Sisestage käsk: ");
			cmd = sc.next().toLowerCase();
		}
		
		String dir = "";
		while (!legalDirs.contains(dir)){
			System.out.println("Sisestage suund: ");
			dir = sc.nextLine().toUpperCase();
		}
		
		int[] curLoc = this.getLocation();
		int newX = 0;
		int newY = 0;
		int length = -1; // Length moved/fired

		
		if (cmd.equals("fire")){
			while (length < 0) {
				System.out.println("Kui kaugele soovite lasta? ");
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
		int[] newLoc = {newX, newY};
		if (cmd.equals("move")){
			if (GameBoard.getTileBoard(newLoc) == GameBoard.getEmptyTile()){
				this.move(newLoc);
			} else {
				this.takeDamage(GameBoard.getBaseRamDamage());
			}
		}
		if (cmd.equals("fire")){
			GameBoard.addMissile(newLoc, (length+GameBoard.getMissileDistTravelledPerTurn()-1)/GameBoard.getMissileDistTravelledPerTurn());
		}

		
		sc.close();
	}
}
