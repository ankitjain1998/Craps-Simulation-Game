/**
 * Name: Ankit Jain, Natasha Gawande 
ID: 96065117, 29802946
UCINetID: jaina2, ngawande

this class creates the file for each thread as well. this class also outputs starting and ending strings to the thread files. 
 */

import java.io.FileNotFoundException;
import java.io.*;

public class FileCreator {
	private PrintWriter file;
	private String startTime;
	private String endTime;
	/**
	 * constructor which creates the file and begins writing to thread file
	 * @param threadName thread name in which the simulation and file is named after 
	 * catches FileNotFoundException if the threadName is not found 
	 */
	public FileCreator(String threadName) { 
		String fileName = "src/" + threadName + ".txt";
		try {
			this.file = new PrintWriter(fileName);
			this.file.write("\r\n*** STARTING SIMULATION ***\r\n");
	/*		this.startTime = "Starting (" + threadName + ") simulation: "
					+ System.currentTimeMillis();
			this.file.write("\r\n" + startTime+ "\r\n");*/
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
	}
	/** method to write to file created from fileCreator clas 
	 * 
	 * @param entry String message to be written to file 
	 */
	public void writeFile (String entry) {
		this.file.write(entry);
	}
	
	/**
	 * 
	 * @return getter method to get StartTime of the simulation 
	 */
	public String getStart() {
		return startTime;
	}
	
	/**
	 * 
	 * @return getter method to return endTime of the simulation 
	 */
	public String getEnd() {
		return endTime;
	}
	/**
	 * 
	 * @return starting and ending time strings of the simulation 
	 * not used??
	 */
	public String timeDetails() {
		return this.startTime + "\n" + this.endTime;
	}
	/**
	 * closes the file, can no longer write to the file
	 * executed when the simulation ends 
	 * @param name file name 
	 */
	public void closeFile(String name) {
		/*this.endTime = "End time  (" + name + ") simulation: "
				+ System.currentTimeMillis();
		this.file.write("\r\n" + endTime + "\r\n");*/
		
		this.file.write("\r\n\r\n*** END OF SIMULATION ***\r\n");
		this.file.close();

		
	}
}
