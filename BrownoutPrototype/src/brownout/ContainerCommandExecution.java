package brownout;

import java.util.ArrayList;

import model.Container;

public class ContainerCommandExecution extends CommandExecution{

	/**
	 * Get Container utilization of all running containers
	 * 
	 * @return
	 */
	public String getContainersCPU() {
		String commandResults;
		String command = "sh getContainersUtil.sh";
		System.out.println("======Output Containers Utilization======");
		commandResults = executeCommand(command);
		return commandResults;
	}
	
	/**
	 * Generate the containers with split operations from string
	 * 
	 * @param containersInfo
	 * @return
	 */
	public ArrayList<Container> generateContainerList(String containersInfo) {
		ArrayList<Container> cl = new ArrayList<Container>();
		String[] stringLines = containersInfo.split("\\r?\\n");
		String hostName = null;
		String[] containerLine;

		String containerId;
		double cpuUtil;
		double memUtil;

		for (String singleStringLine : stringLines) {
			// Example: DockerCluster1 | SUCCESS | rc=0 >>
			if (singleStringLine.contains("SUCCESS") || singleStringLine.contains("success")) {
				containerLine = singleStringLine.split(" \\| ");
				hostName = containerLine[0];
			}
			// Example: CONTAINER CPU % MEM USAGE / LIMIT MEM % NET I/O BLOCK
			// I/O PIDS
			// Example: 04474571a642 0.00% 55.21 MiB / 3.36 GiB 1.60% 4.22 kB /
			// 3.34 kB 0 B / 0 B 21
			if (singleStringLine.contains("%") && !singleStringLine.contains("CPU")) {
				containerLine = singleStringLine.split("\\s+ ");
				containerId = containerLine[0];
				cpuUtil = Double.parseDouble(containerLine[1].substring(0, containerLine[1].length() - 1));

				memUtil = Double.parseDouble(containerLine[3].substring(0, containerLine[3].length() - 1));

				Container container = new Container(hostName, containerId, cpuUtil, memUtil);
				cl.add(container);
			}
		}
		return cl;

	}
	
	
	/**
	 * Stop (deactivate) the containers in the deactivated container list
	 * @param p_deactivatedContainerList
	 * @return
	 */
	public String stopContatiners(ArrayList<Container> p_deactivatedContainerList) {
		String commandResults = null;
		String command;
		System.out.println("======Stop Containers======");
		String args_hostName;
		String arges_containerId;

		for (Container container : p_deactivatedContainerList) {
			args_hostName = container.getHostName();
			arges_containerId = container.getContainerId();
			// This shell file requires two parameters
			command = "sh stopContainer.sh " + args_hostName + " " + arges_containerId;
			commandResults = executeCommand(command);

		}
		return commandResults;
	}
	
	
}
