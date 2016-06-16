package MOM_Z;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * Queue class
 * 
 * @author Robert Borsos
 * @version 16-06-2016
 *
 */
public class AdminQueueDoctor {
	private static ConnectionFactory connectionFactory;
	private static String password = ActiveMQConnection.DEFAULT_PASSWORD;
	private static Connection connection = null;
	private static Session session = null;
	private static Destination dest;
	private static MessageConsumer show = null;
	private static int i = 0;
	private static String defaultName = ActiveMQConnection.DEFAULT_USER;
	private static String queue;

	/**
	 * Class-Method from Queue
	 * 
	 * @param server ip for connection
	 * @param queue chattopic
	 */
	public AdminQueueDoctor(String server, String queue) {
		this.queue = queue;
		try {
			connectionFactory = new ActiveMQConnectionFactory(defaultName, password, server);
			connection = connectionFactory.createConnection();
			connection.start();

			// Create the session
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			// setting destination address
			dest = session.createQueue(queue);
			
			// Create the consumer
			show = session.createConsumer(dest);

		} catch (Exception e) {
			System.out.println("Queue connection failed");
		}
	}
	
	/**
	 * Add Patient to Queue
	 * @param patient Patient which will be added to queue
	 */
	public void addPatient(String patient){
		TextMessage msg;
		try {
			//Destination dest = session.createQueue(queue);
			// Create the producer.
			MessageProducer mail = session.createProducer(dest);
			System.err.println("Patient added");
			msg = session.createTextMessage(patient);
			mail.send(msg);
			i++;
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Receives the TextMessage (Patient) from the queue 
	 * 
	 * @return Returns the next Patient
	 */
	public String getPatient(){
		String temp = null;
		try {
			TextMessage msg = (TextMessage) show.receive(300);
			//System.out.println("" + msg);
			//System.out.println("Next Patient: " + msg.getText());
			temp = msg.getText();
			i--;
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}
	
	/**
	 * 
	 * @return size of patients in queue
	 */
	public int getSize(){
		return i;
	}

	/**
	 * close the connection for JMSQueue
	 * 
	 */
	public void close() {
		try {
			// close connections 
			show.close();
			session.close();
			connection.close();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Queue is closed, no more inputs!");
	}
}
