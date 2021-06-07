package by.belstu.kryukova.secondTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;

public class MainServerThread extends Thread{

    private PrintStream os; // передача
    private BufferedReader is; // прием
    private InetAddress addr; // адрес клиента
    public MainServerThread(Socket s) throws IOException {
        os = new PrintStream(s.getOutputStream());
        is = new BufferedReader(new InputStreamReader(s.getInputStream()));
        addr = s.getInetAddress();
    }

    public void disconnect() {
        try {
            if (os != null) {
                os.close();
            }
            if (is != null) {
                is.close();
            }
            System.out.println(addr.getHostName() + " disconnecting");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            this.interrupt();
        }
    }

    public void run() {
        int i = 0;
        String str, intStr;
        try {
            while ((str = is.readLine()) != null) {
                Thread.sleep(3000);
                //intStr = str.replaceAll("User-PC/192.168.0.111", "");
                intStr=str;
                System.out.println("USER-PC_" + intStr);
                if(Integer.valueOf(intStr) % 2 == 0)
                {
                    os.println("Message " + ++i);
                    System.out.println("Message " + i + " to " +
                            addr.getHostName() + "_" + intStr);
                }

            }
        } catch (IOException | InterruptedException e) {
// если клиент не отвечает, соединение с ним разрывается
            System.err.println("Disconnect");
        } finally {
            disconnect(); // уничтожение потока
        }
    }
}
