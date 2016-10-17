
public class Footer {
	static char[][] set_footer(char[][] frame, int x,int y,String text){
		for(int i=0; i<text.length(); ++i){
			frame[y-1][((x-text.length())/2)+i] = text.charAt(i);
		}
		return frame;
	}
}
