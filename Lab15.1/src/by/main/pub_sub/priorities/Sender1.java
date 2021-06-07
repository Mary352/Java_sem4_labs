package by.main.pub_sub.priorities;

import com.sun.messaging.ConnectionConfiguration;
import com.sun.messaging.ConnectionFactory;

import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSException;

public class Sender1 {
    public static void main(String[] args){
        ConnectionFactory factory= new ConnectionFactory();
        try(JMSContext context = factory.createContext("admin", "admin")){
            factory.setProperty(ConnectionConfiguration.imqAddressList,
                    "mq://127.0.0.1:7676,mq://127.0.0.1:7676");
            Destination priceInfo  = context.createTopic("PubSub");
            while (true) {
                context.createProducer().setPriority(0).send(priceInfo, "message with priority 0");
                System.out.println("Сообщение отправлено");
                Thread.sleep(1000);
            }
        } catch (JMSException | InterruptedException e) {
            System.out.println("ConnectionConfigurationError: " + e.getMessage());
        }
    }
}