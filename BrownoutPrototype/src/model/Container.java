package model;

/**
 * The Container class stores the data of each container, including container
 * id, container cpu utilization and memory utilization.
 * @author minxianx
 *
 */
public class Container {

	String hostName;
	String containerId;
	double cpuUtil;
	double memUtil;
	String connection;
	public Container(String p_hostName, String p_containerId, double p_cpuUtil, double p_memUtil){
		this.hostName = p_hostName;
		this.containerId = p_containerId;
		this.cpuUtil = p_cpuUtil;
		this.memUtil = p_memUtil;
	}
	
	public Container(String p_hostName, String p_containerId, double p_cpuUtil, double p_memUtil, String p_connnection){
		this.hostName = p_hostName;
		this.containerId = p_containerId;
		this.cpuUtil = p_cpuUtil;
		this.memUtil = p_memUtil;
		this.connection = p_connnection;
	}
	
	
	/**
	 * @return the containerId
	 */
	public String getContainerId() {
		return containerId;
	}
	/**
	 * @param containerId the containerId to set
	 */
	public void setContainerId(String containerId) {
		this.containerId = containerId;
	}
	/**
	 * @return the cpuUtil
	 */
	public double getCpuUtil() {
		return cpuUtil;
	}
	/**
	 * @param cpuUtil the cpuUtil to set
	 */
	public void setCpuUtil(double cpuUtil) {
		this.cpuUtil = cpuUtil;
	}
	/**
	 * @return the memUtil
	 */
	public double getMemUtil() {
		return memUtil;
	}
	/**
	 * @param memUtil the memUtil to set
	 */
	public void setMemUtil(double memUtil) {
		this.memUtil = memUtil;
	}

	/**
	 * @return the hostName
	 */
	public String getHostName() {
		return hostName;
	}

	/**
	 * @param hostName the hostName to set
	 */
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	
	
	/**
	 * @return the connection
	 */
	public String getConnection() {
		return connection;
	}

	/**
	 * @param hostName the connection to set
	 */
	public void setConnection(String connection) {
		this.hostName = connection;
	}
	
}
