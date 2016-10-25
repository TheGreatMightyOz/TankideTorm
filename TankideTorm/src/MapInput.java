import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MapInput { // Reads the map from a text file and creates an ArrayList<ArrayList<char>> type board that it assigns to GameBoard
	public static void readMap() throws FileNotFoundException{
		java.util.Scanner scUser = new java.util.Scanner(System.in);
		System.out.println("Input the filename of the map used: ");
		String fileName = scUser.nextLine();
		//scUser.close();
		
		if (fileName.equals("")) fileName = "tk.txt";
		java.io.File file = new java.io.File(fileName);
		java.util.Scanner sc = new java.util.Scanner(file, "UTF-8");
		ArrayList<ArrayList<Character>> boardTemp = new ArrayList<ArrayList<Character>>();
		String[] temp =  sc.nextLine().split(" ");
		int boardWidth = Integer.parseInt(temp[1]); // The indexes are swapped here because it works out that way
		int boardHeight = Integer.parseInt(temp[0]); // It's fine, don't worry about it. Write it normally in the text file.
		GameBoard.setBoardWidth(boardWidth);
		GameBoard.setBoardHeight(boardHeight);
		
		while (sc.hasNextLine()){
			String row = sc.nextLine();
			ArrayList<Character> rowArray = new ArrayList<Character>();
			for(int i = 0; i<row.length(); i++){
				rowArray.add(row.charAt(i));
			}
			boardTemp.add(rowArray);
		}
		ArrayList<ArrayList<Character>> board = new ArrayList<ArrayList<Character>>();
		for (int i = 0; i < boardTemp.size();i++){ //Flips the input to get the map the right way around
			board.add(boardTemp.get(boardTemp.size()-i-1));
		}
		//sc.close();
		GameBoard.setBoard(board);
	}
}
