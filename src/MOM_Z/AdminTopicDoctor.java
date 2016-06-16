package MOM_Z;

import javax.jms.*;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * Topic class
 * 
 * @author Robert Borsos
 * @version 16-06-2016
 *
 */
public class AdminTopicDoctor implements MessageListener {
	private String user = null;
	private String password = ActiveMQConnection.DEFAULT_PASSWORD;

	private Session session = null;
	private Connection connection = null;
	private MessageProducer producer = null;
	private Destination destination = null;
	private MessageConsumer consumer = null;

	/**
	 * Class-Method from Topic
	 * 
	 * @param server ip for connection
	 * @param user username
	 * @param topic topic
	 */
	public AdminTopicDoctor(String server, String user, String topic) {
		this.user = user;

		try {
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(user, password, server);
			connection = connectionFactory.createConnection();
			connection.start();

			// Create the session
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			destination = session.createTopic(topic);

			// Create the producer.
			producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

			consumer = session.createConsumer(destination);
			consumer.setMessageListener(this);

		} catch (Exception e) {
			System.out.println("Topic connection failed");
		}
	}
	
	/**
	 * message listener
	 * 
	 */
	@Override
	public void onMessage(Message message) {

	}

	/**
	 * to send a notification to the doctor
	 * 
	 * @param s message
	 */
	public void send(String s) {
		TextMessage message;
		try {
			message = session.createTextMessage(s); // rborsos: "text"
			producer.send(message);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * close connection from JMSTopic
	 * 
	 */
	public void close() {
		try {
			// close connections
			producer.close(); 
			consumer.close();
			session.close();
			connection.close();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.err.println("Topic is closed, no more inputs!");
	}
}