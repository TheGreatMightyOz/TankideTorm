import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class GraphicInput {
	public static char[][] GetCharArray(int x, int y, String filename){
		char[][] fileData=new char[y][x];
		for(int i=0;i<y;++i){
			Arrays.fill(fileData[i], ' ');
		}
		//System.out.println(fileData[0][5]==' ');
		//List<String> fileData = new ArrayList<String>();
		try{
			  Scanner file=new Scanner (new File(filename));
			  int i=0;
			    while(file.hasNextLine() & i<y){
			        String line= file.nextLine();
			        line = line + new String(new char[x-line.length()]).replace("\0", " ");
			        //System.out.println(line);
			        fileData[i] = line.toCharArray();
			        ++i;
			    }
			    file.close();
			    //System.out.println("No error");
			}
			catch (IOException e){
			    System.out.println("IOException");
			}
		//System.out.println(fileData[0][5]==' ');
		return fileData;
	}
}
