import java.util.ArrayList;

public class TankideTorm {

	public static void main(String[] args) throws Exception {
		System.out.println(System.getProperty("user.dir"));
		java.io.File file = new java.io.File("TankideKaart.txt");
		java.util.Scanner sc = new java.util.Scanner(file, "UTF-8");
		ArrayList<ArrayList<String>> board = new ArrayList<ArrayList<String>>();
		String[] temp =  sc.nextLine().split(" ");
		int boardWidth = Integer.parseInt(temp[0]);
		int boardHeight = Integer.parseInt(temp[1]);
		while (sc.hasNextLine()){
			String row = sc.nextLine();
			String[] rowList = row.split(" ");
			ArrayList<String> rowArray = new ArrayList<String>();
			for(String el : rowList){
				rowArray.add(el);
			}
			board.add(rowArray);
		}
		sc.close();
		GameBoard.setBoard(board);
	}

}
