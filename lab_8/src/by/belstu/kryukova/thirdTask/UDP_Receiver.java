package by.belstu.kryukova.thirdTask;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDP_Receiver {
    // UDP-сокет получателя будет запущен на этом порту
    public final static int SERVICE_PORT=50001;

    public static void main(String[] args) throws IOException {
        try{
            // Создание нового экземпляра DatagramSocket, чтобы получать сообщения от отправителя
            DatagramSocket serverSocket = new DatagramSocket(SERVICE_PORT);

            // Создание буфера для хранения получаемых данных
            byte[] receivingDataBuffer = new byte[32];

            // Создание экземпляра UDP-пакета для хранения данных отправителя с использованием буфера для полученных данных
            DatagramPacket inputPacket = new DatagramPacket(receivingDataBuffer, receivingDataBuffer.length);
            System.out.println("Waiting for a Sender to connect...");

            while (true)
            {
                // Получение данных от клиента и сохранение их в inputPacket
                serverSocket.receive(inputPacket);

                // Вывод на экран отправленных клиентом данных
                String receivedData = new String(inputPacket.getData());
                System.out.println("Received from the UDP_Sender: " + receivedData);
            }

            // Закрытие соединения сокетов
            //serverSocket.close();
        }
        catch (SocketException e){
            e.printStackTrace();
        }
    }
}
