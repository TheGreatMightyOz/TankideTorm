

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MapInput { // Reads the map from a text file and creates an ArrayList<ArrayList<char>> type board that it assigns to GameBoard
	public static void readMap() throws FileNotFoundException{
		java.util.Scanner scUser = new java.util.Scanner(System.in);
		System.out.println("Sisestage kasutatava kaardi nimi: ");
		String fileName = scUser.nextLine();
		scUser.close();
		
		if (fileName.equals("")) fileName = "TankideKaart1.txt";
		java.io.File file = new java.io.File(fileName);
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
