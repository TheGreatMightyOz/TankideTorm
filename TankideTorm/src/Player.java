import java.util.Scanner;

public class Player {
	private int[] location;
	private String name;
	private char icon; // The first letter of the name, the letter that is displayed on the map
	private int hitpoints;
	private static int startingHP = 3;
	
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
	public void takeTurn(){ // Method for taking a turn
		Scanner sc = new Scanner(System.in);
		String cmd = "";
		while (cmd != "move" && cmd != "fire"){
			System.out.println(name + " käik. Sisestage käsk: ");
			cmd = sc.next().toLowerCase();
			if (cmd.equals("move")){
				System.out.println("Sisestage suund: "); // Lisa meetod liikumiseks
			}
			if (cmd.equals("fire")){ // Lisa meetod laskmiseks
				System.out.println("Sisestage suund: ");
			}
		}
		sc.close();
	}
}
