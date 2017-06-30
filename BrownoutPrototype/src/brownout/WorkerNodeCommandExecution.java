package brownout;

import java.util.ArrayList;

import model.Container;
import model.WorkerNode;

public class WorkerNodeCommandExecution extends CommandExecution{
	
	/**
	 * Get the CPU utilization of each worker node
	 * 
	 * @return
	 */
	public String getWorkerNodesCPU() {
		String commandResults;
		String command = "sh getWorkerNodesCPU.sh";
		System.out.println("======Output Worker Nodes Utilization======");
		commandResults = executeCommand(command);
		return commandResults;

	}
	
	
	/**
	 * Generate the worker node information with split operations.
	 * 
	 * @param workcernodeInfo
	 * @return
	 */
	public ArrayList<WorkerNode> generateWorkNodeList(String workcernodeInfo, ArrayList<Container> p_containerList) {
		ArrayList<WorkerNode> wnl = new ArrayList<WorkerNode>();

		String[] stringLines = workcernodeInfo.split("\\r?\\n");
		String hostName = null;
		String[] containerLine;
		double cpuUtil;
		ArrayList<Container> containerListOnSameHost = new ArrayList<Container>();

		for (String singleStringLine : stringLines) {
			System.out.println(singleStringLine);
			// Example: DockerCluster1 | SUCCESS | rc=0 >>
			if (singleStringLine.contains("SUCCESS") || singleStringLine.contains("success")) {
				containerLine = singleStringLine.split(" \\| ");
				hostName = containerLine[0];
				System.out.println("host name" + hostName);
			}
			// Example: 1.0%
			if (singleStringLine.contains("%")) {
				cpuUtil = Double.parseDouble(singleStringLine.substring(0, singleStringLine.length() - 1));
				System.out.println("CPU utilization:" + cpuUtil);
				containerListOnSameHost = getContainerListByHostName(hostName, p_containerList);
				WorkerNode worknode = new WorkerNode(hostName, cpuUtil, containerListOnSameHost);
				wnl.add(worknode);
			}
		}
		return wnl;

	}
	
	
	/**
	 * Put all the containers on the same host into a single container list
	 * @param p_hostName
	 * @param p_containerList
	 * @return
	 */
	ArrayList<Container> getContainerListByHostName(String p_hostName, ArrayList<Container> p_containerList) {
		ArrayList<Container> containerListOnSameHost = new ArrayList<Container>();
		for (Container container : p_containerList) {
			if (container.getHostName().equals(p_hostName)) {
				containerListOnSameHost.add(container);
			}
		}

		return containerListOnSameHost;
	}
	
}
