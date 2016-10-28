import java.io.Console;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Graafika{
	static int x;
	static int y;
	public Graafika(int x, int y){
		Graafika.x=x;
		Graafika.y=y;
	}

	public static void welcome() throws IOException{
		//Path path = Paths.get(System.getProperty("user.dir").toString());
		char[][] frame = GraphicInput.GetCharArray(x,y,"resource/welcome");
		new WelcomeTorm(x,y);
		//new Footer();
		while(System.in.available() == 0){
			GraphicOutput.OutCharArray(frame);
			frame=WelcomeTorm.sand(frame, x ,y);
			frame=FormatOutput.set_footer("Press Enter to continue");
			try {
				TimeUnit.MILLISECONDS.sleep(100);
				TermCom.clean();
			} catch (InterruptedException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			frame = GraphicInput.GetCharArray(x,y,"resource/mainmenu");
			GraphicOutput.OutCharArray(frame);
			TermCom.setColor("08");
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
		try {
			TermCom.setColor("8F");
			TimeUnit.MILLISECONDS.sleep(50);
			TermCom.setColor("F8");
			TimeUnit.MILLISECONDS.sleep(50);
			TermCom.setColor("07");
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		Console console = System.console();
		while(true){
			String maptype = console.readLine();
			if(maptype.equalsIgnoreCase("1")){ //winter
				try {
					TermCom.setColor("B9");
					GraphicOutput.OutCharArray(frame);
					TimeUnit.SECONDS.sleep(3);
					break;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if(maptype.equalsIgnoreCase("2")){ //desert
				try {
					TermCom.setColor("E6");
					GraphicOutput.OutCharArray(frame);
					TimeUnit.SECONDS.sleep(3);
					break;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if(maptype.equalsIgnoreCase("3")){ //night
				try {
					TermCom.setColor("");
					GraphicOutput.OutCharArray(frame);
					TimeUnit.SECONDS.sleep(3);
					break;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if(maptype.equalsIgnoreCase("4")){ //forest
				try {
					TermCom.setColor("20");
					GraphicOutput.OutCharArray(frame);
					TimeUnit.SECONDS.sleep(3);
					break;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if(maptype.equalsIgnoreCase("5")){ // help
				frame = GraphicInput.GetCharArray(x,y,"resource/menu_help");
				GraphicOutput.OutCharArray(frame);
				while(System.in.available() == 0){}
				frame = GraphicInput.GetCharArray(x,y,"resource/mainmenu");
				GraphicOutput.OutCharArray(frame);
			}
			if(maptype.equalsIgnoreCase("6")){ // exit
				System.exit(0);
			}
		}
	}
}

