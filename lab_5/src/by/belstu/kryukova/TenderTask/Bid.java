package by.belstu.kryukova.TenderTask;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Bid extends Thread {
    private static ArrayList<Bid> last3Bids;

    private Integer bidId;
    private int price;
    private CyclicBarrier barrier;
    public Bid (int id, int price, CyclicBarrier barrier)
    {
        this.last3Bids = new ArrayList<>();

        this.bidId = id;
        this.price = price;
        this.barrier = barrier;
    }

    public Integer getBidId() {
        return bidId;
    }

    public int getPrice() {
        return price;
    }

    public static ArrayList<Bid> getLast3Bids() {
        return last3Bids;
    }

    @Override
    public void run()
    {
        try {
            System.out.println("Клиент " + this.bidId + " определил цену");
            Thread.sleep(new Random().nextInt(3000));
            int delta = new Random().nextInt(50);
            price += delta;
            System.out.println("Ставка " + this.bidId + ": " + price);
            this.last3Bids.add(this);
            this.barrier.await();
            this.last3Bids.clear();
            System.out.println("Подождите...");
        }
        catch (BrokenBarrierException ex)
        {
            ex.printStackTrace();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
