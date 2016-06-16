package MOM_Z;

import java.util.ArrayList;
import java.util.List;

/**
 * Admin Class is handling the logic for the programm
 * 
 * @author Robert Borsos
 * @version 16-06-2016
 */
public class Admin {
	private List<AdminQueueDoctor> queueList = new ArrayList<AdminQueueDoctor>(); //List for the queue objects
	private List<AdminTopicDoctor> topicList = new ArrayList<AdminTopicDoctor>(); //List for the topic objects
	private List<String> queueName = new ArrayList<String>(); //List for the queue names
	private int doctorCount = 0; //Counter for Round Robin
	
	/**
	 * Make new Doctor/Queue and adding it to the lists above
	 * @param name Name for new Queue
	 * @param server IP Adress from the Message Broker
	 */
	public void newDoctor(String name, String server){
		AdminQueueDoctor queue = new AdminQueueDoctor("tcp://" + server + ":61616", name);
		AdminTopicDoctor topic = new AdminTopicDoctor("tcp://" + server + ":61616", name, name);
		queueList.add(queue);
		topicList.add(topic);
		queueName.add(name);
		System.out.println("Queue created");
	}
	
	/**
	 * Add Patient to a queue through Least connection
	 * @param patient Patient which will be added to queue
	 */
	public void leastConnection(String patient){
		int tempSize = 100;
		int tempQueue = 0;
		for(int i = 0; i < queueName.size(); i++){ //search through all queues
			if(queueList.get(i).getSize() < tempSize){
				tempSize = queueList.get(i).getSize();
				tempQueue = i;
			}	
		}
		queueList.get(tempQueue).addPatient(patient); //add Patient to queue
	}
	
	/**
	 * Adding Patient to a queue through Round Robin
	 * @param patient Patient which will be added to queue
	 */
	public void roundRobin(String patient){
		queueList.get(this.nextDoctor()).addPatient(patient); //add Patient to queue
		doctorCount++;
	}
	
	/**
	 * 
	 * @return next Queue for Round Robin
	 * @return if no more queue, than start at 0 again
	 */
	public int nextDoctor(){		
		if(doctorCount <= queueList.size()){
			return doctorCount;
		}
		else{
			doctorCount = 0;
			return doctorCount;
		}
	}

	/**
	 * Get the next patient for a specific queue
	 * @param name Name of the queue, from which the next patient should be get
	 */
	public void show(String name) {
		boolean found = false;
		String tempPatient;
		for(int i = 0; i < this.queueName.size(); i++){
			if(queueName.get(i).equals(name) && queueList.get(i).getSize() != 0){
				tempPatient = queueList.get(i).getPatient();
				System.out.println("Next Patient: " + tempPatient);
				topicList.get(i).send(tempPatient);
				found = true;
			} else if (queueList.get(i).getSize() == 0){ 
				System.out.println("Queue is empty");
				found = true;
			}
		}
		if(found == false){
			System.out.println("Queue not found"); // If no queue named like that is found
		}
	}

	/**
	 * delete queue if not empty
	 * @param name Name of the queue which should be deleted
	 */
	public void delete(String name) {
		for(int i = 0; i < this.queueName.size(); i++){
			if(queueName.get(i).equals(name)){
				if(queueList.get(i).getSize() == 0){ //.getSize() from Queue which knows about the queue content
					queueList.get(i).close();
					topicList.get(i).close();
				}
				else {
					System.out.println(name + " not empty");
				}
			}
		}		
	}
	
	/**
	 * Using getQueue to get known about all queues
	 * @return Returns the value of queue
	 */
	public int getQueue(){
		return queueName.size();
	}
}
