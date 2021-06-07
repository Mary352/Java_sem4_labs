package by.belstu.kryukova.store;

import by.belstu.kryukova.printEditions.PrintEdition;
import lombok.*;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class BookStore {
    private static final Logger LOG = Logger.getLogger(BookStore.class);
    private ArrayList<PrintEdition>  printEditionsList = new ArrayList<PrintEdition>();
    private double totalResult = 0;
    private Seller seller = new Seller();


    public void totalPrintEdsCost()
    {
        LOG.info("class BookStore method totalPrintEdsCost()");
        try
        {
            for (PrintEdition pe:
                    printEditionsList) {
                totalResult += pe.getPrice();
            }
            System.out.println("Общая стоимость: " + totalResult);
        }
        catch (Exception ex)
        {
            System.out.println("Пустой каталог");
        }

    }

    @Getter
    @Setter
    @NoArgsConstructor
    @EqualsAndHashCode
    @ToString
    public class Seller
    {
        public void  addPrintEdition(PrintEdition pe)
        {
            printEditionsList.add(pe);
            PrintEdition.setTotalNum(PrintEdition.getTotalNum()+1);
            System.out.println("Добавлено новое издание");
        }

        public void sellPrintEdition(PrintEdition pe)
        {
            System.out.println("В продаже " + PrintEdition.getTotalNum() + " изданий");

            if (printEditionsList.remove(pe))
            {
                PrintEdition.setTotalNum(PrintEdition.getTotalNum()-1);
                System.out.println("Продано. Остаток: " + PrintEdition.getTotalNum() + " изданий");
            }
            else
                System.out.println("Такого издания нет продаже. Остаток: " + PrintEdition.getTotalNum() + " изданий");

        }

        public PrintEdition searchPrintEdition(String title)
        {
            try {
                for (PrintEdition pe:
                        printEditionsList) {
                    if (pe.getTitle().equals(title))
                        return pe;
                }
                System.out.println("Ничего не найдено");
                return null;
            }
            catch (Exception ex)
            {
                System.out.println("Пустой каталог");
            }
            System.out.println("Ничего не найдено");
            return null;
        }

        public void sortPrintEditions()
        {
            try {
                Collections.sort(printEditionsList, new Comparator<PrintEdition>() {
                    @Override
                    public int compare(PrintEdition pe1, PrintEdition pe2) {
                        return pe1.compareTo(pe2);
                    }
                });

                for (PrintEdition pe:
                        printEditionsList) {
                    pe.info();
                }
            }
            catch (Exception ex)
            {
                System.out.println("Пустой каталог");
            }

        }
    }
}
