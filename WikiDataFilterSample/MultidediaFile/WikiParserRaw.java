import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * This class is used to parse the useful information from the raw data file of Wikipedia trace.
 * We aim to collect the number of requests at per second. 
 * @author minxianx
 *
 */
public class WikiParserRaw {
	
	public static void main(String[] args) {

		try {
			//The raw data from Wikipedia trace, downloaded from http://www.wikibench.eu/?page_id=60 
			//"samplewikirawdata01" is a sample file from wikipedia trace.
			BufferedReader br = new BufferedReader(new FileReader("src/samplewikirawdata01"  ));
			//The output file is the parsed results from "samplewikirawdata01"
			BufferedWriter bw = new BufferedWriter(new FileWriter("src/sampleparseddata01"));

	

				String string = null;
				String[] stringLine = null;
				String time = null;
				Date date;
				String tempTime = "1190146292829";
				int count = 0;
				int tempCount = 0;
				int secondInterval = 0;
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/' 'HH:mm:ss");
				while ((string = br.readLine()) != null) {

					count++;
					stringLine = string.split(" ");
					//Get the time and the corresponding number of requests
					time = stringLine[1].substring(0, 10) + stringLine[1].substring(11, 14);

					if (!time.substring(0, 10).equals(tempTime.substring(0, 10))) {

						date = new Date(Long.parseLong(tempTime));

						bw.write(simpleDateFormat.format(date) + "\t" + secondInterval++ + "\t" + (count - tempCount)
								+ "\n");

						tempTime = time;
						tempCount = count;

					}

				}

			
			bw.close();
			br.close();
			System.out.println("complete");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
