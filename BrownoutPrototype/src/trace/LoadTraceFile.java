package trace;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoadTraceFile {

	
	public LoadTraceFile(){
		
	}
	
	public String loadTrace() {
		BufferedReader br = null;
		FileReader fr = null;
		StringBuffer sb = new StringBuffer();

		try {
			fr = new FileReader("src/trace/trace-day-wiki.txt");
			br = new BufferedReader(fr);

			String currentLine;

			// Skip lines


			while ((currentLine = br.readLine()) != null ) {
				// System.out.println(currentLine);
				sb.append(currentLine + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
				if (fr != null) {
					fr.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
//		System.out.println(sb);
		return sb.toString();
		
	}


	

}
