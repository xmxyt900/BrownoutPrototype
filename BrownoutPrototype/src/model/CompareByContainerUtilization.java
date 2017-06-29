package model;

import java.util.Comparator;

	/**
	 ** Sort the component instances with component utilization 
     *  From ascending order
	 * @author minxianx
	 *
	 */
	public class CompareByContainerUtilization implements Comparator<Object> {

		public int compare(Object o1, Object o2){
			Container coc1 = (Container) o1;
			Container coc2 = (Container) o2;
			
			if(coc1.getCpuUtil() < coc2.getCpuUtil()){
				return -1;
			}
			if(coc1.getCpuUtil() > coc2.getCpuUtil()){
				return 1;
			}
			
			return 0;
		}
	}
	

