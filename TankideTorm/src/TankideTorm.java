import java.util.ArrayList;

public class TankideTorm {

	public static void main(String[] args) throws Exception {
		//Sets buffer size
		new Graafika(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
		//Shows welcome message
		Graafika.welcome();
		//System.out.println(System.getProperty("user.dir"));
		MapInput.readMap(); // Reads map from file (name input from user)
		
		String[] playerNameList = PlayerInputter.inputPlayers(); // Generates list of player names from player input
		
		GameBoard.genPlayerList(playerNameList); // Generates list of players with starting locations from list of names
		GameBoard.spawnPlayers(); // Places the players on the board
		
	}

}
