package property;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * This class is loading the parameters from property
 * file.
 * @author minxianx
 *
 */
public class LoadProperty {
	Properties prop;
	
	public LoadProperty(){
		 prop = new Properties();
		InputStream input = null;
		
		try {
			input = new FileInputStream("src/property/config.properties");
			prop.load(input);
		}catch(IOException ex) {
			ex.printStackTrace();
		}finally {
			if(input != null) {
				try {
					input.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		
	}
	}
	
	/**
	 * Loading the overloaded threshold
	 * @return
	 */
	public double getOverloadedThr() {
		System.out.println(prop.getProperty("Thr"));
		return Double.parseDouble(prop.getProperty("Thr"));
	}
	
	/**
	 * Loading the scheduling time interval
	 * @return
	 */
	public double getTimeInterval() {
		System.out.println(prop.getProperty("TimeInterval"));
		return Double.parseDouble(prop.getProperty("TimeInterval"));
	}
	
	public static void main(String[] args) {
		LoadProperty lp = new LoadProperty();
		lp.getOverloadedThr();
		lp.getTimeInterval();
	}
	
	

}
