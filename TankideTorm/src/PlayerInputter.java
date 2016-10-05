


public class PlayerInputter { // Generates a list of player names from user input
	public static String[] inputPlayers(){
		
		java.util.Scanner sc = new java.util.Scanner(System.in);
		System.out.println("Sisestage mängijate arv: ");
		int n = sc.nextInt();
		String[] nameList = new String[n];

		for(int i = 0; i < n; i++){
			System.out.println("Sisestage" + i+1 + ". mängija nimi: ");
			nameList[i] = sc.nextLine();
		}
		
		sc.close();
		return nameList;
	}
}
