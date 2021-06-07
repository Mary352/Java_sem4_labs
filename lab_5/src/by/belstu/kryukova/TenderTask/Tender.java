package by.belstu.kryukova.TenderTask;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.CyclicBarrier;

public class Tender {
    private ArrayList<Bid> last5BidsTender;

    private ArrayList<Bid> bids;
    private CyclicBarrier barrier;
    public final int BIDS_NUMBER = 3;

    public Tender()
    {
        this.bids = new ArrayList<>();
        this.barrier = new CyclicBarrier(this.BIDS_NUMBER, new Runnable() {
            @Override
            public void run() {
                Bid winner = Tender.this.defineWinner();
                System.out.println("Ставка #" + winner.getBidId() + ", цена: " + winner.getPrice() + " победил");
            }
        });
    }

    public CyclicBarrier getBarrier() {
        return barrier;
    }

    public boolean add(Bid bid)
    {
        return bids.add(bid);
    }

    public Bid defineWinner()
    {
        return Collections.min(Bid.getLast3Bids(), new Comparator<Bid>() {
            @Override
            public int compare(Bid o1, Bid o2) {
                return o1.getPrice() - o2.getPrice();//
            }
        });
    }
}
