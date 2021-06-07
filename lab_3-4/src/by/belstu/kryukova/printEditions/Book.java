package by.belstu.kryukova.printEditions;

import lombok.*;
import by.belstu.kryukova.interfaces.PE_Sell;
import org.apache.log4j.Logger;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
//@SuperBuilder
public class Book extends PrintEdition {
    private static final Logger LOG = Logger.getLogger(Book.class);
private String authorFirstName;
private String authorLastName;

    public Book(String publisher, double price, String title, int year, int printEditNumber, String aFirstName, String aLastName) {

        super(publisher, price, title, year, printEditNumber);

        authorFirstName = aFirstName;
        authorLastName = aLastName;
    }

public void info()
{
    LOG.info("class Book method info()");
    System.out.println();
    System.out.println(authorFirstName + " " + authorLastName + " \"" + getTitle() + "\"" + ", " + getYear() + " - " + getPrice());
}
}
