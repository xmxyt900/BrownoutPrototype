package reqinjector;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoadJMXFormatFile {
	int startLine;
	int endLine;
	int lineNo;
	
	LoadJMXFormatFile(){
		
	}
	
	
	
	public String getLines(int p_startLine, int p_endLine) {
		BufferedReader br = null;
		FileReader fr =null;
		StringBuffer sb = new StringBuffer();
		
		this.startLine = p_startLine - 1;
		this.endLine = p_endLine - 1;
		
		if(endLine < startLine) {
			System.out.println("Start line should not be larger than end line");
			System.exit(0);
		}
		
		
		try {
			fr = new FileReader("src/WeaveShopGrid5k5min.jmx");
			br = new BufferedReader(fr);
			
			String currentLine; 
			
			//Skip lines
			for(lineNo = 0; lineNo < startLine; lineNo++) {
//				System.out.println(lineNo +" "  + br.readLine());
				br.readLine();
			}
			
			while ( (currentLine = br.readLine()) != null && lineNo <= endLine) {
//				System.out.println(currentLine);
				sb.append(currentLine + "\n");
				lineNo++; 
			}
		}catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(br!=null) {
					br.close();
				}
				if(fr != null) {
					fr.close();
				}
			}catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return sb.toString();
	}
	
	

}
