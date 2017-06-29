package policy;

import java.util.ArrayList;

import model.Container;

public interface AbstractPolicyInterface {

	/**
	 * Get the deactivated containers based on different scheduling policies.
	 * @return
	 */
	 ArrayList<Container> getDeactivatedContainerList();
		
}
