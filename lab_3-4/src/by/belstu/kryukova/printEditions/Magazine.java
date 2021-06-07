package by.belstu.kryukova.printEditions;

import by.belstu.kryukova.enums.Months;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Magazine extends PrintEdition {
    private Months month;

    public Magazine(String publisher, double price, String title, int year, int printEditNumber, Months month) {

        super(publisher, price, title, year, printEditNumber);
        this.month = month;

    }

    public void info()
    {
        System.out.println();
        System.out.println(getTitle() + " " + month + "/" + getYear() + " - " + getPrice());
    }
}
