package policy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import brownout.BrownoutMain;
import model.CompareByContainerUtilization;
import model.Container;
import model.WorkerNode;


/**
 * This class sorts the containers based on container utilization,
 * in ascendent order. And the find the suitable containers with 
 * lowest utilization 
 * @author minxianx
 *
 */
public class LowestUtilizationContainerFirstPolicy implements AbstractPolicyInterface{

	
	double dimmerValue;
	ArrayList<WorkerNode> wnl;
	HashSet<String> connectionSet;
	
	public LowestUtilizationContainerFirstPolicy(double p_dimmerValue, ArrayList<WorkerNode> p_wnl){
		this.dimmerValue = p_dimmerValue;
		this.wnl = p_wnl;
	}
	

	public ArrayList<Container> getDeactivatedContainerList() {
		// TODO Auto-generated method stub
		ArrayList<Container> deactivatedContainerList = new ArrayList<Container>();
		for(WorkerNode wn: wnl){
			Collections.sort(wn.getContainersList(), new CompareByContainerUtilization());
			
			if(wn.getCpuUtil() >= BrownoutMain.OVERLOAD_THRESHOLD){
				if(wn.getContainersList().get(0).getCpuUtil() >= dimmerValue && wn.getContainersList().size()>0) {
					deactivatedContainerList.add(wn.getContainersList().get(0));
					connectionSet.add(wn.getContainersList().get(0).getConnection());
				}
				
				
				double tempUtil = Double.MAX_VALUE;
				for(Container container: wn.getContainersList()){
					double utilization = getUtilizationOfDeactivatedContainerList(deactivatedContainerList);
					if(container.getCpuUtil() <= dimmerValue && (utilization < dimmerValue)) {
						
					}
					    if(Math.abs(dimmerValue - utilization - container.getCpuUtil()) < tempUtil) {
						deactivatedContainerList.add(container);
						tempUtil = dimmerValue - getUtilizationOfDeactivatedContainerList(deactivatedContainerList);
						connectionSet.add(container.getConnection());
					    }
				}
				
				//find other connected containers
				for(Container container: wn.getContainersList()) {
					if(connectionSet.contains(container.getConnection())) {
						deactivatedContainerList.add(container);
					}
				}
			}
		}
		return deactivatedContainerList;
	}
	
	/**
	 * Get the utilization of deactivate containers
	 * @param deactivatedContainerList
	 * @return
	 */
	public double getUtilizationOfDeactivatedContainerList(ArrayList<Container> deactivatedContainerList) {
		double sumUtilization =0.0f;
		for(Container container : deactivatedContainerList) {
			sumUtilization += container.getCpuUtil();
			
		}
		
		return sumUtilization;
	}
	

}
