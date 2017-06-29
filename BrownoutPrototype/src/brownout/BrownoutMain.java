package brownout;

import monitor.UtilizationMonitor;

public class BrownoutMain {

	public final static double OVERLOAD_THRESHOLD = 0.0;
	public final static int TIME_INTERVAL = 60;
	public static void main(String args[]) {

		new Thread(new UtilizationMonitor()).start();

	}
	}
