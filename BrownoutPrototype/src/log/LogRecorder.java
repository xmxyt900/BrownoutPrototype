package log;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


/**
 * This class is generating the logs for brownout
 * system.
 * @author minxianx
 *
 */
public class LogRecorder {
	Logger logger;
	public LogRecorder() {
		logger = Logger.getLogger("BrownoutLog");
		FileHandler fh;

		try {
			fh = new FileHandler("src/log/brownout.log");
			logger.addHandler(fh);
			SimpleFormatter sf = new SimpleFormatter();
			fh.setFormatter(sf);

//			logger.info("Brownout Log");
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		logger.info("test");

	}
	
	public void info(String info){
		logger.info(info);
	}
	
	public static void main(String[] args) {
		LogRecorder lr = new LogRecorder();
	}

}
