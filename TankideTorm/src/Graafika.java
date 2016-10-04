import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Graafika{
	static int x;
	static int y;
	public Graafika(int x, int y){
		Graafika.x=x;
		Graafika.y=y;
	}

	public static void welcome(){
		//Path path = Paths.get(System.getProperty("user.dir").toString());
		GraphicOutput.OutCharArray(GraphicInput.GetCharArray(x,y,"resource/welcome"));
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			clean();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void clean()throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }
}

