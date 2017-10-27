package brownout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This class is used for executing shell commands on worker nodes from the
 * master node.
 * 
 * @author minxianx
 *
 */
public class CommandExecution {

	/**
	 * Execute the shell commands and output the results (normal outputs and
	 * errors).
	 * @param command
	 */
	String executeCommand(String command) {

		String input = null;
		String error = null;
		StringBuffer sb = new StringBuffer("");

		try {
			Process process = Runtime.getRuntime().exec(command);
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));

			BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

			while ((input = stdInput.readLine()) != null) {
				System.out.println(input);
				// Append the line to string
				// "\n" is required to added, otherwise all the outputs will be
				// in a single line
				sb.append(input + "\n");
			}

			while ((error = stdError.readLine()) != null) {
				System.out.println(error);
			}

		} catch (IOException e) {
			System.out.println("Exception: ");
			e.printStackTrace();
			System.exit(-1);
		}

		return sb.toString();
	}

	
	/**
	 * Execute the shell commands and output the simplified results (not put same info as
	 * previous).
	 * 
	 * @param command
	 */
	String executeCommandWithLessInfo(String command) {

		String input = null;
		String error = null;
		String tempInput = null;
		StringBuffer sb = new StringBuffer("");

		try {
			Process process = Runtime.getRuntime().exec(command);
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));

			BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

			while ((input = stdInput.readLine()) != null) {
				if(!(input.equals(tempInput))){
				System.out.println(input);
				}
				
				// Append the line to string
				// "\n" is required to added, otherwise all the outputs will be
				// in a single line
				sb.append(input + "\n");
				tempInput = input;
			}

			while ((error = stdError.readLine()) != null) {
				System.out.println(error);
			}

		} catch (IOException e) {
			System.out.println("Exception: ");
			e.printStackTrace();
			System.exit(-1);
		}

		return sb.toString();
	}
	
}
