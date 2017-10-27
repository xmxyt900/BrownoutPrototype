package brownout;

import log.LogRecorder;
import monitor.UtilizationMonitor;
import property.LoadProperty;

public class BrownoutMain {

	public static double OVERLOAD_THRESHOLD = 0.0;
	public final static int TIME_INTERVAL = 60;
	
	public static LogRecorder log = new LogRecorder();
	
	public static void main(String args[]) {

		LoadProperty lp = new LoadProperty();
		OVERLOAD_THRESHOLD = lp.getOverloadedThr();
		
		new Thread(new UtilizationMonitor()).start();

	}
	}
