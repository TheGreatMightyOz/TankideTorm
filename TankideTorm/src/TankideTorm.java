import java.util.ArrayList;

public class TankideTorm {

	public static void main(String[] args) throws Exception {
		//Sets buffer size
		new Graafika(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
		//Shows welcome message
		Graafika.welcome();
		//System.out.println(System.getProperty("user.dir"));
		MapInput.readMap("Tankidetorm.txt");
		
		
		String[] playerNameList = {"Mängur"}; //Loo meetod mängijate (nimede) sisestamiseks
		
		
		GameBoard.genPlayerList(playerNameList);
		GameBoard.spawnPlayers();
		
	}

}
