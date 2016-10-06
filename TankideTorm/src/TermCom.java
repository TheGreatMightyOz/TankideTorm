import java.io.IOException;

public class TermCom {
	
	public static void clean()throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }
	
	public static void setScreen(int x, int y) throws InterruptedException, IOException{
		new ProcessBuilder("cmd", "/c", "mode con: cols="+x+" lines=" +y).inheritIO().start().waitFor();
	}
	public static void setColor(String a) throws InterruptedException, IOException{
		new ProcessBuilder("cmd", "/c", "color "+ a).inheritIO().start().waitFor();
	}
}
