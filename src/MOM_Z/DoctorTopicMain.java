package MOM_Z;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Class with the Main-Method an task allocation
 * If this class gets started, the doctor gets known about the next patient
 * 
 * @author Robert Borsos
 * @version 16-06-2016
 *
 */
public class DoctorTopicMain {

	/*
	 * Main-Method where input starts an action
	 * 
	 * @param args arguments
	 */
	public static void main(String[] args) {
		// check if the arguments are given
		if (args.length != 2) {
			System.out.println("Arguments missing! \n Please insert in the Run Configuration the following Information \n  <ipMessageBroker> <Doctor/Queue>");
		} else {
			System.out.println("Next Patient will shown here");
			
			// information input to the other classes
			DoctorTopic topic = new DoctorTopic("tcp://" + args[0] + ":61616", args[1], args[1]);

			try {
				// Reader for the Systemout text
				BufferedReader systemout = new java.io.BufferedReader(new InputStreamReader(System.in));

				while (true) {
					String input = null;  		       
					input = systemout.readLine();
					String[] s = input.split("\\s+");

					if (s[0].equalsIgnoreCase("EXIT")) { // if match than close functions
						System.out.println("Closing queue...");
						topic.close(); // call method "close" from Topic.java
						System.exit(0); // exit with 0 -> no problems
					} 
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}