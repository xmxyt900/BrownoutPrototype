package brownout;

import java.util.ArrayList;

import model.WorkerNode;

/**
 * This class is used to:
 * calculate the number of overloaded hosts;
 * compute the dimmer value;
 * obtain the deactivated container list based on different policies.
 * @author minxianx
 *
 */
public class BrownoutController {
	final double OVERLOAD_THRESHOLD = 0.0;
	
	
	public BrownoutController(){
		
	}
	
	/**
	 * The host is overloded if its utilization is above the threshold.
	 * This fucntion computes the number of overloaded hosts.
	 * @param p_workerNodeList
	 * @return
	 */
	int getNumberOfOverLoadedHosts(ArrayList<WorkerNode> p_workerNodeList ){
		int numberOfOverlaodedHosts = 0;
		for(WorkerNode wn : p_workerNodeList){
			if(wn.getCpuUtil() > OVERLOAD_THRESHOLD){
				numberOfOverlaodedHosts++;
			}
		}
		return numberOfOverlaodedHosts;
	}
	
	/**
	 * The dimmer value is applied to control how much utilization will be reduced.
	 * The dimmer value is calculated as sqrt(num(overlaoded hosts)/num(total hosts)).
	 * @param p_workerNodeList
	 * @return
	 */
	public double getDimmerValue(ArrayList<WorkerNode> p_workerNodeList){
		double dimmerValue = 0.0f;
		int numberOfOverloadedHosts = getNumberOfOverLoadedHosts(p_workerNodeList);
		int numberOfHosts = p_workerNodeList.size();
		//Also void the case that numberofHosts is 0
		if(numberOfOverloadedHosts == 0){
			dimmerValue =0.0f;
		}else{
		dimmerValue = Math.sqrt(numberOfOverloadedHosts / numberOfHosts);
		}
		return dimmerValue;
	}
	
}
