package brownout;

import java.util.ArrayList;

public interface AbstractPolicyInterface {

	/**
	 * Get the deactivated containers based on different scheduling policies.
	 * @return
	 */
	 ArrayList<Container> getDeactivatedContainerList();
		
}
