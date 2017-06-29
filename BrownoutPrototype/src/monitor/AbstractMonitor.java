package monitor;

import java.util.ArrayList;

import brownout.BrownoutMain;
import model.Container;
import model.WorkerNode;

abstract class AbstractMonitor implements Runnable{

	abstract void monitor();
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
		monitor();
		try{
			Thread.sleep(BrownoutMain.TIME_INTERVAL * 1000);
		}catch (InterruptedException e) {
            e.printStackTrace(); 
        }
		
		}
	}
	
	/**
	 * Formatted output of worker nodes and containers information
	 * @param p_workNodeList
	 */
	public void outputWokerNodeAndContainerInfo(ArrayList<WorkerNode> p_workNodeList){
		System.out.println(String.format("%20s", "Worker Node Name") + " " + String.format("%10s", "CPU %") + " "
				+ String.format("%20s", "Con Id") + " " + String.format("%15s", "Con CPU %") + " "
				+ String.format("%20s", "Con MEM %"));

		for (WorkerNode wn : p_workNodeList) {
			for (Container container : wn.getContainersList()) {
				System.out.println(
						String.format("%20s", wn.getWorkerNodeName()) + " " + String.format("%10s", wn.getCpuUtil())
								+ "    " + String.format("%20s", container.getContainerId()) + " "
								+ String.format("%15s", container.getCpuUtil()) + " "
								+ String.format("%15s", container.getMemUtil()));
			}
		}
	}

}
