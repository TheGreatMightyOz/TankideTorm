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
		char[][] frame = GraphicInput.GetCharArray(x,y,"resource/welcome");
		new WelcomeTorm(x,y);
		for(int i=0; i<2000; ++i){
			GraphicOutput.OutCharArray(frame);
			frame=WelcomeTorm.sand(frame, x ,y);
			try {
				TimeUnit.MILLISECONDS.sleep(100);
				TermCom.clean();
			} catch (InterruptedException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			TermCom.setColor("08");
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
		try {
			TermCom.setColor("8F");
			TimeUnit.MILLISECONDS.sleep(50);
			TermCom.setColor("F8");
			TimeUnit.MILLISECONDS.sleep(50);
			TermCom.setColor("07");
			TimeUnit.SECONDS.sleep(3);
			TermCom.clean();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}

