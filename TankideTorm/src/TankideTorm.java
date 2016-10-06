import java.util.ArrayList;

public class TankideTorm {

	public static void main(String[] args) throws Exception {
		//Screen constants
		int x=150;
		int y=50;
		//Sets buffer size
		TermCom.setScreen(x,y);
		new Graafika(x,y);
		//Shows welcome message
		Graafika.welcome();
		
		//System.out.println(System.getProperty("user.dir"));
		MapInput.readMap(); // Reads map from file (name input from user)
		
		String[] playerNameList = PlayerInputter.inputPlayers(); // Generates list of player names from player input
		
		GameBoard.genPlayerList(playerNameList); // Generates list of players with starting locations from list of names
		GameBoard.spawnPlayers(); // Places the players on the board
		
	}

}
