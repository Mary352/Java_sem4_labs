package by.belstu.kryukova.printEditions;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class SchoolBook extends Book {
    private int _class;

public SchoolBook(String publisher, double price, String title, int year, int printEditNumber, String aFirstName, String aLastName, int _class)
{
    super(publisher, price, title, year, printEditNumber, aFirstName, aLastName);
    this._class = _class;
}

    public void info()
    {
        System.out.println();
        System.out.println(getTitle() + " " + _class + " класс " + getAuthorFirstName().substring(0, 1) + "." + getAuthorLastName() + " " + getYear() + " - " + getPrice());
    }
}
