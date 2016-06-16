package MOM_Z;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Class with the Main-Method an task allocation
 * Also the Run Config Inputs are taken from here, and put in Queue and Topic
 * 
 * @author Robert Borsos
 * @version 16-06-2016
 *
 */
public class AdminMain {
	 private static final int LC = 0;
	 private static final int RR = 1;
	 private static int state = LC;
	 private static int i = 1;
		
	/*
	 * Main-Method where input starts an action
	 * 
	 * @param args arguments
	 */
	public static void main(String[] args) {
		// check if the arguments are given
		if (args.length != 1) {
			System.out.println("Arguments missing! \n Please insert in the Run Configuration the following Information \n  <ipMessageBroker>");
		} else {
			System.out.println("Queuing is activated");
			
			
			// information input to the other classes
			

			try {
				// Reader for the Systemout text
				BufferedReader systemout = new java.io.BufferedReader(new InputStreamReader(System.in));
				Admin logic = new Admin();
				
				while (true) {
					String input = null;  
			       
					input = systemout.readLine();
					
					String[] s = input.split("\\s+");

					if (s[0].equalsIgnoreCase("RR")) { 
						state = RR;
						System.out.println("State changed to Round Robin");
					} else if (s[0].equalsIgnoreCase("LC")) { 
						state = LC;
						System.out.println("State changed to Least Connection");
					} else if (s[0].equalsIgnoreCase("SHOW")) { 
						logic.show(s[1]);	
					} else if (s[0].equalsIgnoreCase("DELETE")) {
						logic.delete(s[1]);	
					} else if (s[0].equalsIgnoreCase("NEW")) {	
						logic.newDoctor(s[1], args[0]);
					} else {
						if(state == LC){
							if(logic.getQueue() != 0){
								String temp1 = s[0] + " " + Integer.toString(i);
								logic.leastConnection(temp1);
								i++;
							}
							else{
								System.err.println("No Queues created. To add one write: NEW <QueueName>");
							}
						} else if (state == RR){
							if(logic.getQueue() != 0){
								String temp1 = s[0] + " " + Integer.toString(i);
								logic.roundRobin(temp1);
								i++;
							}
							else{
								System.err.println("No Queues created. To add one write: NEW <QueueName>");
							}
						}
					}
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}