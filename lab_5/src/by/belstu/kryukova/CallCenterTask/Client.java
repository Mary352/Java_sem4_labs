package by.belstu.kryukova.CallCenterTask;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Client extends Thread {
    private String name;
    private CallCenter callCenter;
    private Semaphore semaphore;

    public Client(CallCenter callCenter, Semaphore semaphore, int name) {
        this.name = String.valueOf(name);
        this.semaphore = semaphore;
        this.callCenter = callCenter;
    }

    public String getClientName() {
        return this.name;
    }

    @Override
    public void run() {
        Random rand = new Random();
        int waitingTime = rand.nextInt(600) + 100;
        System.out.println("Клиент " + this.getClientName() + " звонит...");
        boolean key  = false;
        while(!key) {
            try {
                if (semaphore.tryAcquire(waitingTime, TimeUnit.MICROSECONDS)) {
                    callCenter.connect(this);
                    System.out.println("Клиент " + this.getClientName() + " подключен. Диалог начат");
                    callCenter.showOperatorsState();
                    Thread.sleep(rand.nextInt(3000) + 500);
                    callCenter.disconnect(this);
                    semaphore.release();
                    System.out.println("Клиент " + this.getClientName() + " отсоединён. Диалог завершён");
                    key = true;
                } else {
                    System.out.println("Клиент " + this.getClientName() + " ждёт");
                    Thread.sleep(2000);
                    if (semaphore.tryAcquire(waitingTime, TimeUnit.MICROSECONDS)) {
                        System.out.println("Клиент " + this.getClientName() + " подключен. Диалог начат");
                        callCenter.connect(this);
                        callCenter.showOperatorsState();
                        Thread.sleep(rand.nextInt(3000) + 500);
                        callCenter.disconnect(this);
                        semaphore.release();
                        System.out.println("Клиент " + this.getClientName() + " отсоединён. Диалог завершён");
                        key = true;
                    }
                    else
                    {
                        System.out.println("Клиент " + this.getClientName() + " отсоединился после ожидания");
                        Thread.sleep(4000);
                        System.out.println("Клиент " + this.getClientName() + " перезванивает...");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
