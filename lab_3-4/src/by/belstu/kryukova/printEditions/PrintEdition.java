package by.belstu.kryukova.printEditions;

import by.belstu.kryukova.Main;
import by.belstu.kryukova.interfaces.PE_Sell;
import lombok.*;
import org.apache.log4j.Logger;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
//@SuperBuilder
public abstract class PrintEdition implements PE_Sell, Comparable<PrintEdition> {
    private static final Logger LOG = Logger.getLogger(PrintEdition.class);
    private int year;
// количество экземпляров одного издания (тираж) - справочная инфа
private int printEditNumber;
private String title;
// счётчик количества изданий в магазине
private static int totalNum = 0;
private String publisher;
private double price;


    public static int getTotalNum() {
        return totalNum;
    }

    public static void setTotalNum(int totalNum) {
        PrintEdition.totalNum = totalNum;
    }

    public PrintEdition(String publisher, double price, String title, int year, int printEditNumber)
{
    //totalNum += 1;

    this.publisher = publisher;
    this.price = price;
    this.title = title;
    this.year = year;
}


public void info()
{
    LOG.info("class PrintEdition method info()");
    System.out.println();
    System.out.println(title + " " + " Изготовитель " + publisher + ", " + year + " - " + price);
}

public int compareTo(PrintEdition pe2)
{
    if (year>pe2.getYear())
        return 1;
    else if(year<pe2.getYear())
        return -1;
    else
        return 0;
}
}
