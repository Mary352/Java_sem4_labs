package by.main.p2p.sync;

import com.sun.messaging.ConnectionConfiguration;
import com.sun.messaging.ConnectionFactory;

import javax.jms.*;

public class Receiver {
    private ConnectionFactory factory = new ConnectionFactory();
    private JMSConsumer consumer;

    Receiver() {
        try (JMSContext context = factory.createContext("admin", "admin")) {
            factory.setProperty(ConnectionConfiguration.imqAddressList,
                    "mq://127.0.0.1:7676, mq://127.0.0.1:7676");
            Destination messageQueue = context.createQueue("P2PSyncQueue");
            consumer = context.createConsumer(messageQueue);
            Message message = consumer.receive(1000);
            System.out.println("Listening to P2PSyncQueue...");
            System.out.println("Сообщение получено: "+
                    message.getBody(by.main.p2p.Message.class));
            System.out.println("\nHere's what toString() on the message prints \n"+ message.toString());
            while (true) {
                Thread.sleep(1000);
            }
        } catch (InterruptedException | JMSException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main (String[] args){
        new Receiver();
    }
}