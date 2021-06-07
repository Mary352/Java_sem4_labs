package by.belstu.kryukova.thirdTask;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDP_Sender {
    /* Порт получателя, к которому собирается
подключиться сокет отправителя */
    public final static int SERVICE_PORT = 50001;

    public static void main(String[] args) throws IOException{
        try{
            // Создание экземпляра сокета отправителя.
            // Нет необходимости в привязке к определенному порту
            DatagramSocket senderSocket = new DatagramSocket();

            // Получение IP-адреса получателя
            InetAddress ip = InetAddress.getByName("localhost");

            // Создание буфера для сообщения
            byte[] sendingDataBuffer;

            // Преобразоване данных в байты и размещение в буфере
            for (int i = 1; i <= 10; i++)
            {
                String sentence = "Message #" + i + " from UDP_Sender";
                System.out.println("Sent from the Sender: " + sentence);
                sendingDataBuffer = sentence.getBytes();
                // Создать UDP-пакет
                DatagramPacket sendingPacket = new DatagramPacket(sendingDataBuffer,sendingDataBuffer.length,ip, SERVICE_PORT);

                // Отправить UDP-пакет получателю
                senderSocket.send(sendingPacket);
            }

            // Закройте соединение с сервером через сокет
            senderSocket.close();
        }
        catch(SocketException e) {
            e.printStackTrace();
        }
    }
}
