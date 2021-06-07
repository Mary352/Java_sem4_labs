package connection;

import java.util.Date;
import javax.jms.*;
import com.sun.messaging.ConnectionFactory;
import com.sun.messaging.ConnectionConfiguration;
import org.apache.log4j.*;

public class Sender {
    private static final Logger LOG = Logger.getLogger(NewspaperDao.class);
    public void send() {
        ConnectionFactory factory = new com.sun.messaging.ConnectionFactory();
        try (JMSContext context = factory.createContext("admin", "admin")) {
            factory.setProperty(ConnectionConfiguration.imqAddressList,
                    "mq://127.0.0.1:7676,mq://127.0.0.1:7676");
            Destination messagesQueue = context.createQueue("P2PAsyncQueue");
            Message message = new Message("Hi, newspaper created", new Date());
            ObjectMessage objMsg = context.createObjectMessage(message);
            JMSProducer producer = context.createProducer();
            producer.send(messagesQueue, objMsg);
            LOG.info(message.toString());
        } catch (JMSException e) {
            System.out.println("ConnectionConfigurationError: " + e.getMessage());
            LOG.error("ConnectionConfigurationError: " + e.getMessage());
        }
    }
}
