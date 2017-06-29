package policy;

import java.util.ArrayList;

import brownout.BrownoutMain;
import model.Container;
import model.WorkerNode;

public class FirstComponentPolicy implements AbstractPolicyInterface{
 
	double dimmerValue;
	ArrayList<WorkerNode> wnl;
	
	public FirstComponentPolicy(double p_dimmerValue, ArrayList<WorkerNode> p_wnl){
		this.dimmerValue = p_dimmerValue;
		this.wnl = p_wnl;
	}
	
	/**
	 * This policy only puts the first container into container list to deactivate
	 */
	public ArrayList<Container> getDeactivatedContainerList(){
		ArrayList<Container> deactivatedContainerList = new ArrayList<Container>();
		for(WorkerNode wn: wnl){
			if(wn.getCpuUtil() >= BrownoutMain.OVERLOAD_THRESHOLD){
				for(Container container: wn.getContainersList()){
					deactivatedContainerList.add(container);
					break;
				}
			}
		}		
		return deactivatedContainerList;
	}
}
