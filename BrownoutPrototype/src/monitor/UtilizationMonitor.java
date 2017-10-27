package monitor;

import java.util.ArrayList;

import brownout.BrownoutController;
import brownout.BrownoutMain;
import brownout.ComposeFileExecution;
import brownout.ContainerCommandExecution;
import brownout.ServiceCommandExecution;
import brownout.WorkerNodeCommandExecution;
import log.LogRecorder;
import model.Container;
import model.WorkerNode;
import policy.LowestUtilizationContainerFirstPolicy;

public class UtilizationMonitor extends AbstractMonitor{


	
	@Override
	void monitor() {
		// TODO Auto-generated method stub

		ComposeFileExecution composeFileExecution = new ComposeFileExecution();
		composeFileExecution.initialDeploymentByComposeFile();
		
		try {
			Thread.sleep(60*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		// Execute commands for containers
		ContainerCommandExecution containerCommandExecution = new ContainerCommandExecution();
		String containersInfo = containerCommandExecution.getContainersCPU();
		ArrayList<Container> containerList = containerCommandExecution.generateContainerList(containersInfo);
		
		for(Container container: containerList){
			System.out.println(container.getHostName() + " " + container.getContainerId() + " " + container.getCpuUtil() + " " + container.getMemUtil());
			BrownoutMain.log.info(container.getHostName() + " " + container.getContainerId() + " " + container.getCpuUtil() + " " + container.getMemUtil());
		}

		// Execute commands for worker nodes
		WorkerNodeCommandExecution workerNodeCommandExecution = new WorkerNodeCommandExecution();
		String workcernodeInfo = workerNodeCommandExecution.getWorkerNodesCPU();
		ArrayList<WorkerNode> workerNodeList = workerNodeCommandExecution.generateWorkNodeList(workcernodeInfo, containerList);

		//Output worker node and containers information
		outputWokerNodeAndContainerInfo(workerNodeList);
		
		//Get the dimmer value
		BrownoutController bc = new BrownoutController();
		double dimmerValue = bc.getDimmerValue(workerNodeList);
		
		//Get the containers that should be deactivated
//		ArrayList<Container> deactivatedContainerList = (new FirstComponentPolicy(dimmerValue, workerNodeList)).getDeactivatedContainerList();
		ArrayList<Container> deactivatedContainerList = (new LowestUtilizationContainerFirstPolicy(dimmerValue, workerNodeList)).getDeactivatedContainerList();

		//Stop the containers in the list
		containerCommandExecution.stopContatiners(deactivatedContainerList);
		
		//update service deployment
		composeFileExecution.updateDeploymentByComposeFile();

		//Prepare to restart the containers
		ServiceCommandExecution serviceCommandExecution = new ServiceCommandExecution();		
		for(Container container: deactivatedContainerList){
			String serviceId;
			serviceId = serviceCommandExecution.getServiceByContainerName(container);
			//Restart the containers
			serviceCommandExecution.updateServices(serviceId);
		}
		System.out.println("######Thread is sleeping######");
		BrownoutMain.log.info("######Thread is sleeping######");
	}
	
}
