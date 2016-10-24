public class PlayerInputter { // Generates a list of player names from user input
	public static String[] inputPlayers(){
		java.util.Scanner sc = new java.util.Scanner(System.in);
		FormatOutput.setLastFrame(FormatOutput.set_empty(150, 50));
		GraphicOutput.OutCharArray(
				FormatOutput.set_footer(
						"Input the number of players:"));
		//System.out.println("Input the number of players: ");
		int n = Integer.parseInt(sc.nextLine());
		String[] nameList = new String[n];

		for(int i = 0; i < n; i++){
			FormatOutput.setLastFrame(FormatOutput.set_empty(150, 50));
			GraphicOutput.OutCharArray(
					FormatOutput.set_footer(
							"Input the name of Player " + Integer.toString(i+1) + ":"));
			//System.out.println("Input the name of Player " + (i+1) + ":");
			nameList[i] = sc.nextLine();
		}
		
		//sc.close();
		return nameList;
	}
}
