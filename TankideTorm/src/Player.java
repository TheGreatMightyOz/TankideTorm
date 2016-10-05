

public class Player {
	private int[] location;
	private String name;
	private char icon; // The first letter of the name, the letter that is displayed on the map
	
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
		this.location = location;
		this.name = name;
		this.icon = name.charAt(0);
	}
}
