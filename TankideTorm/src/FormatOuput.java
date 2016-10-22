
public class FormatOutput {
	static char[][] set_footer(char[][] frame, int x,int y,String text){
		for(int i=0; i<text.length(); ++i){
			frame[y-1][((x-text.length())/2)+i] = text.charAt(i);
		}
		return frame;
	}
	
	static char[][] set_map(char[][] frame, char[][] map){
		for(int i=0; i<map.length; ++i){
			for(int t=0; t<map[0].length; ++t){
				frame[(frame.length-map.length+i)/2][t]=map[i][t];
			}
		}
		return frame;
	}
	
	static char[][] set_health(char[][] frame){
		return frame;
	}
}
