import java.util.ArrayList;

public class FormatOutput {
	static private char[][] lastFrame;
	
	public static void setLastFrame(char[][] frame){
		lastFrame=frame;
	}
	
	static char[][] set_footer(String text){
		int x=lastFrame[0].length;
		int y=lastFrame.length;
		for(int i=0; i<x; ++i){
			lastFrame[y-1][i] = ' ';
		}
		for(int i=0; i<text.length(); ++i){
			lastFrame[y-1][((x-text.length())/2)+i] = text.charAt(i);
		}
		return lastFrame;
	}
	
	static char[][] set_map(char[][] map){
		//System.out.println(lastFrame.length);
		for(int i=0; i<map.length; ++i){
			for(int t=0; t<map[0].length; ++t){
				lastFrame[(lastFrame.length - map.length)/2+i][(lastFrame[0].length - map[0].length)/2+t]=map[i][t];
			}
		}
		return lastFrame;
	}
	
	static char[][] set_health(){
		ArrayList<Player> playerList = GameBoard.getPlayerList();
		for(int i=0; i<playerList.size(); ++i){
			
		}
		return lastFrame;
	}
	static char[][] set_empty(int x,int y){
		char[][] a = new char[y][x];
		for(int i=0; i<y;++i){
			for(int t=0;t<x;++t){
				a[i][t]=' ';
			}
		}
		return a;
	}
}
