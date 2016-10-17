import java.util.Arrays;

public class WelcomeTorm {
	static int[][] sandmap;
	public WelcomeTorm(int x,int y){
		WelcomeTorm.sandmap = new int[y][x];
		for(int i=0; i<y;i++){
			Arrays.fill(sandmap[i], 0);
		}
	}
	public static char[][] sand(char[][] frame, int x, int y){
		for(int i=y-2; i>=0; --i){
			for(int t=0; t<x; ++t){
				if(sandmap[i][t]==1){
					int dummy = 0;
					for(int k=0; k<4; ++k){
					//int[] direc = {-1,0,1};
						int random = (int )(Math.random() * 4 - 2);
					//if(0<t+random & t+random<x){
						if( frame[i+1][((t+random)%x+x)%x]==' ' & dummy==0){
							frame[i][t]=' ';
							sandmap[i][t]=0;
							frame[i+1][((t+random)%x+x)%x]='@';
							sandmap[i+1][((t+random)%x+x)%x]=1;
							dummy=1;
						}
					}
					//}
					if(frame[i+1][t]==' ' & dummy==0){
						frame[i][t]=' ';
						frame[i+1][t]='@';
						sandmap[i][t]=0;
						sandmap[i+1][t]=1;
					}
					else{
						sandmap[i][t]=0;
					}
				}
			}
		}
		
		int random = (int )(Math.random() * x-1);
		frame[0][random]='@';
		sandmap[0][random]=1;
		
		int cat = (int )(Math.random() * x-1);
		frame[0][cat]='@';
		sandmap[0][cat]=1;
		
		return frame;
	}
}
