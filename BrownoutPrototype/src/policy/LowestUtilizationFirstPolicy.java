package policy;

import java.util.ArrayList;
import java.util.Collections;

import brownout.BrownoutMain;
import model.CompareByContainerUtilization;
import model.Container;
import model.WorkerNode;


/**
 * This class sorts the containers based on container utilization,
 * in ascendent order. 
 * @author minxianx
 *
 */
public class LowestUtilizationFirstPolicy implements AbstractPolicyInterface{

	
	double dimmerValue;
	ArrayList<WorkerNode> wnl;
	
	public LowestUtilizationFirstPolicy(double p_dimmerValue, ArrayList<WorkerNode> p_wnl){
		this.dimmerValue = p_dimmerValue;
		this.wnl = p_wnl;
	}
	

	public ArrayList<Container> getDeactivatedContainerList() {
		// TODO Auto-generated method stub
		ArrayList<Container> deactivatedContainerList = new ArrayList<Container>();
		for(WorkerNode wn: wnl){
			Collections.sort(wn.getContainersList(), new CompareByContainerUtilization());
			
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
