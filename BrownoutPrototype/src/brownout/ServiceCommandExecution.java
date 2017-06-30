package brownout;

import model.Container;

public class ServiceCommandExecution extends CommandExecution{

	/**
	 * Get service id of container id, its implementation is based on 
	 * docker inspect
	 * @param container
	 * @return
	 */
	public String getServiceByContainerName(Container container){
		String args_hostName = container.getHostName();;
		String arges_containerId = container.getContainerId();
		System.out.println("======Output Service of Stopped containers======");
		String command = "sh getServiceIdByContianerID.sh " + args_hostName + " " + arges_containerId;
		String commandResults = executeCommand(command);
		
		//Example: "com.docker.swarm.service.id": "jyyuzs6p6u2e5c362jtp9m5at", 
		int beginIndex = commandResults.indexOf(':') + 3;   //Begin with 'j'
//		int endIndex = commandResults.length() - 3;   //end with 't'
		int endIndex = commandResults.indexOf(',') - 2; //end with 't'
		String serviceId = commandResults.substring(beginIndex, endIndex); 
	    System.out.println(serviceId);
				
		return serviceId;
	}
	
	/**
	 * Restart the stopped containers (new container instances)
	 * @param serviceId
	 */
	public void updateServices(String serviceId){
		System.out.println("======Update Services====");
		String command = "sh  updateService.sh " + serviceId;
		executeCommandWithLessInfo(command);
	}
}
