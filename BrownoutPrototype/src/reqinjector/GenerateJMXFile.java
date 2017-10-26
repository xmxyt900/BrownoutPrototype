package reqinjector;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import trace.LoadTraceFile;

/**
 * Generate jmx file with customized number of requests
 * @author minxianx
 *
 */
public class GenerateJMXFile {

	public static void main(String[] args) throws IOException {
		
		LoadJMXFormatFile ljf = new LoadJMXFormatFile();
//		System.out.println(ljf.getLines(30, 30));
		String allThreads = null;
		
		//Header Info
		String headers = ljf.getLines(1, 24);
		
		
		String sfinalTail = ljf.getLines(2865, 2871);  
		

		
		/**
		 * ####################
		 * Thread info goes here
		 */
		//60 represents 1 minute
		int loop = 60;
		//Load from file, the requests number in each second,
		//100 means that 100 requests are sent simultaneously 
		int threads = 100;
		//Repsentes how much the requests are sending
		//1440 means 1440 minutes, 1 day.
		int threadSize = 1440;
		
		int  rampTime = 1;
		
		LoadTraceFile ltf = new LoadTraceFile();
		String[] sringlist = ltf.loadTrace().split("\n");
		
		threadSize = sringlist.length; 
		
		Writer writer = null;
		try {
		writer = new BufferedWriter(new OutputStreamWriter(
		          new FileOutputStream("src/oneday.jmx"), "utf-8"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		String finalThreads ="";
		
		for(int i = 0; i < threadSize; i++) {
		
		String threadGroup = ljf.getLines(25, 28);		
		
		
		String sloops = "          <stringProp name=\"LoopController.loops\">" + loop + "</stringProp>" + "\n";
		
		String selement = ljf.getLines(30, 30);
		
		String snumOfhreads = "        <stringProp name=\"ThreadGroup.num_threads\">" + sringlist[i] + "</stringProp>" + "\n";
		
		String srampTime = "        <stringProp name=\"ThreadGroup.ramp_time\">" + rampTime + "</stringProp>" + "\n";
		
		String threadstail = ljf.getLines(33, 38);
		
		String pageInfo = ljf.getLines(39, 592);

		
		allThreads = threadGroup + sloops + selement + snumOfhreads + srampTime +  threadstail + pageInfo;
		
		finalThreads = finalThreads + allThreads;
		}
		
		
		
		/**
		 * ######################
		 * Thead info goes above
		 */
		//Tail info
		
		finalThreads = headers + finalThreads + sfinalTail;
		
		
		writer.write(finalThreads);
		 writer.close();

		
		
	}
	
}
