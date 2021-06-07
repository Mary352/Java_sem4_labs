package by.belstu.kryukova;

import by.belstu.kryukova.enums.Months;
import by.belstu.kryukova.printEditions.Book;
import by.belstu.kryukova.printEditions.Magazine;
import by.belstu.kryukova.printEditions.SchoolBook;
import by.belstu.kryukova.store.BookStore;
import by.belstu.kryukova.store.Seller_class;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class Main {


    static {
        //PropertyConfigurator.configure("log4j.properties");
        new DOMConfigurator().doConfigure("log/log4j.xml",
                LogManager.getLoggerRepository());
    }
    private static final Logger LOG = Logger.getLogger(Main.class);

    public static void main(String[] args) {

        LOG.info("Starting program________________");

// TODO минимум один абстрактный класс и один внутренний класc
//  Seller: сортировку книг на
//  основе года издания,
//  найти печатное издание по названию,
//  продать,
//  добавить новое издание
        Seller_class sellerClass = new Seller_class();
        BookStore bookStore = new BookStore();
        BookStore bookStore2 = new BookStore();

        // PrintEdition - abstract
        //PrintEdition pe1 = new PrintEdition("Эксмо", 4.69, "Открытка С днём Рождения", 2020, 8000);

        Book pe2 = new Book("Эксмо", 24.98, "Клевер", 2015, 100000, "Анастасия", "Налимова");
        SchoolBook pe3 = new SchoolBook("Ветер", 19.99, "Химия", 2018, 800000, "Сергей", "Боровин", 11);
        Magazine pe4 = new Magazine("Славита", 1.78, "Кроссворды", 2020, 8500, Months.Apr);

//        seller.addPrintEdition(pe1, bookStore);
//        sellerClass.addPrintEdition(pe2, bookStore);
//        sellerClass.addPrintEdition(pe2, bookStore);
//        sellerClass.addPrintEdition(pe3, bookStore);
//        sellerClass.addPrintEdition(pe4, bookStore);
//        sellerClass.addPrintEdition(pe4, bookStore);
//
//        sellerClass.sortPrintEditions(bookStore);
//        System.out.println();
//        sellerClass.sellPrintEdition(pe3, bookStore);
//        sellerClass.sellPrintEdition(pe2, bookStore);
//        sellerClass.sellPrintEdition(pe2, bookStore);
//        sellerClass.addPrintEdition(pe2, bookStore);
//        sellerClass.addPrintEdition(pe3, bookStore);
//        sellerClass.addPrintEdition(pe4, bookStore);
//        (sellerClass.searchPrintEdition(bookStore, "Кроссворды")).info();

        bookStore.getSeller().addPrintEdition(pe2);
        bookStore.getSeller().addPrintEdition(pe2);
        bookStore.getSeller().addPrintEdition(pe4);
        bookStore.getSeller().addPrintEdition(pe3);
        bookStore.getSeller().addPrintEdition(pe4);

        bookStore.getSeller().sortPrintEditions();
        System.out.println();

        bookStore.getSeller().sellPrintEdition(pe3);
        bookStore.getSeller().sellPrintEdition(pe2);
        bookStore.getSeller().sellPrintEdition(pe2);

        bookStore.getSeller().addPrintEdition(pe2);
        bookStore.getSeller().addPrintEdition(pe4);
        bookStore.getSeller().addPrintEdition(pe3);

        (bookStore.getSeller().searchPrintEdition("Кроссворды")).info();

        bookStore2.getSeller().addPrintEdition(pe2);
        bookStore2.getSeller().addPrintEdition(pe4);
        bookStore2.getSeller().sortPrintEditions();
        bookStore2.totalPrintEdsCost();
    }
}
