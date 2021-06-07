package by.belstu.kryukova.secondTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

public class MyClient {

    public static void main(String[] args) {
        Socket socket = null;
        BufferedReader br = null;
        String str;
        Random random = new Random();
        int clientNum;

        try {
            // установка соединения с сервером
            socket = new Socket(InetAddress.getLocalHost(), 7071);
            PrintStream ps =
                    new PrintStream(socket.getOutputStream());
            br = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            clientNum = random.nextInt(500) + 1;
            for (int i = 1; i <= 10; i++) {
                // посылаем запрос
                //str = String.valueOf(InetAddress.getLocalHost()) + String.valueOf(clientNum);
                str = String.valueOf(clientNum);
                ps.println(str);
                // выводим в консоль полученный ответ
                System.out.println(br.readLine());
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
