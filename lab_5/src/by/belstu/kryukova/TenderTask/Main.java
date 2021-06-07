package by.belstu.kryukova.TenderTask;

import java.util.Random;

public class Main {

    public static void main(String[] args) {


        //-----------2---------------------------
//        Tender tender = new Tender();
//
//        int startPrice = new Random().nextInt(100);
//
//        for (int i = 0; i < tender.BIDS_NUMBER; i++) {
//            Bid thread = new Bid(i, startPrice, tender.getBarrier());
//
//            tender.add(thread);
//            thread.start();
//        }

        // OK
        Tender tender2 = new Tender();

        int startPrice2 = new Random().nextInt(100);

        for (int i = 0; i < 9; i++) {
            Bid thread = new Bid(i, startPrice2, tender2.getBarrier());

            tender2.add(thread);
            thread.start();
        }
    }
}
