

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MapInput {
	public static void readMap(String filename) throws FileNotFoundException{
		java.io.File file = new java.io.File(filename);
		java.util.Scanner sc = new java.util.Scanner(file, "UTF-8");
		ArrayList<ArrayList<Character>> board = new ArrayList<ArrayList<Character>>();
		String[] temp =  sc.nextLine().split(" ");
		int boardWidth = Integer.parseInt(temp[0]);
		int boardHeight = Integer.parseInt(temp[1]);
		GameBoard.setBoardHeight(boardHeight);
		GameBoard.setBoardWidth(boardWidth);
		while (sc.hasNextLine()){
			String row = sc.nextLine();
			String[] rowList = row.split(" ");
			ArrayList<Character> rowArray = new ArrayList<Character>();
			for(String el : rowList){
				rowArray.add(el.charAt(0));
			}
			board.add(rowArray);
		}
		sc.close();
		GameBoard.setBoard(board);
	}
}
