import java.util.ArrayList;

public class TankideTorm {

	public static void main(String[] args) throws Exception {
		java.io.File file = new java.io.File("tankidekaart.txt");
		java.util.Scanner sc = new java.util.Scanner(file, "UTF-8");
		ArrayList<ArrayList<Integer>> board = new ArrayList<ArrayList<Integer>>();
		sc.close();
		GameBoard.setBoard(board);

	}

}
