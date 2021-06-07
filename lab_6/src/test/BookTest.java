package test;

import by.belstu.kryukova.Book;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class BookTest {
    @org.testng.annotations.DataProvider (name = "createBook")
    public Object[][] CreateData()
    {
        Book book = new Book("Сборник сказок", "А.С. Пушкин", 2022, "АСТ");
        return new Object[][]{
                {book}
        };
    }

    @Test (priority = 1, dataProvider = "createBook")
    public void testGetName(Book book) {
        assertEquals(book.getName(), "Сборник сказок");
    }

    @Test (priority = 2, dataProvider = "createBook")
    public void testSetName(Book book) {
        book.setName("dfsf");
        assertEquals(book.getName(), "dfsf");
    }

    @Test (priority = 1, dataProvider = "createBook")
    public void testGetAuthorFullName(Book book) {
        assertEquals(book.getAuthorFullName(), "А.С. Пушкин");
    }

    @Test (priority = 2, dataProvider = "createBook")
    public void testSetAuthorFullName(Book book) {
        book.setAuthorFullName("vhhh");
        assertEquals(book.getAuthorFullName(), "vhhh");
    }

    @Test (priority = 1, dataProvider = "createBook")
    public void testGetReleaseYear(Book book) {
        assertEquals(book.getReleaseYear(), 2022);
    }

    @Test (priority = 2, dataProvider = "createBook")
    public void testSetReleaseYear(Book book) {
        book.setReleaseYear(2023);
        assertEquals(book.getReleaseYear(), 2023);
    }

    @Test (priority = 1, dataProvider = "createBook")
    public void testGetPublishingHouse(Book book) {
        assertEquals(book.getPublishingHouse(), "АСТ");
    }

    @Test (priority = 2, dataProvider = "createBook")
    public void testSetPublishingHouse(Book book) {
        book.setPublishingHouse("ufnvk");
        assertEquals(book.getPublishingHouse(), "ufnvk");
    }
}