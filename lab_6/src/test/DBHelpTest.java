package test;

import by.belstu.kryukova.Book;
import by.belstu.kryukova.DBHelp;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import org.apache.log4j.LogManager;
import org.apache.log4j.xml.DOMConfigurator;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.testng.Assert.*;
public class DBHelpTest {

    @org.testng.annotations.DataProvider (name = "booksInsert")
    public Object[][] CreateData()
    {
        Book bookInsertDelete = new Book("Сборник сказок", "А.С. Пушкин", 2022, "АСТ");
        Book bookInsertDelete2 = new Book("Макбет", "У.Шекспир", 2023, "Эксмо");
        return new Object[][]{
                {bookInsertDelete, bookInsertDelete2}
        };
    }

    @org.testng.annotations.Ignore
    @org.testng.annotations.DataProvider (name = "createHelperAutoCommitTrue")
    public Object[][] CreateHelper()
    {
        try {
            DBHelp helper = new DBHelp(true);
            return new Object[][]{
                    {helper}
            };
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return new Object[][]{
                {null}
        };
    }

    @org.testng.annotations.DataProvider (name = "createHelperAutoCommitFalse")
    public Object[][] CreateOtherHelper()
    {
        try {
            DBHelp helper = new DBHelp(false);
            return new Object[][]{
                    {helper}
            };
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return new Object[][]{
                {null}
        };
    }

    @org.testng.annotations.BeforeSuite
    public void setUp() {
        //DataProvider


        {
            //PropertyConfigurator.configure("log4j.properties");
            new DOMConfigurator().doConfigure("log/log4j.xml",
                    LogManager.getLoggerRepository());
        }
         //final Logger LOG = Logger.getLogger(Main.class);

    }

//    @org.testng.annotations.Test (dataProvider = "createHelperAutoCommitTrue", priority = 1)
//    public void testCloseConnectionDB_expectedFailedTest(DBHelp helper) {
//      assertTrue(helper.closeConnectionDB());
//    }
//
//    @org.testng.annotations.Test (timeOut = 1_000, dataProvider = "createHelperAutoCommitTrue", priority = 2)
//    public void testGetPreparedStatement(DBHelp helper) {
//
//        assertNotNull(helper.getPreparedStatement());
//        helper.closeConnectionDB();
//    }
//
//    @org.testng.annotations.Test (dataProvider = "createHelperAutoCommitTrue", priority = 3)
//    public void testGetStatement(DBHelp helper) {
//
//            assertNotNull(helper.getStatement());
//            helper.closeConnectionDB();
//    }
//
//    @org.testng.annotations.Test (dataProvider = "createHelperAutoCommitTrue", priority = 4)
//    public void testClosePreparedStatment(DBHelp helper) {
//        PreparedStatement ps = helper.getPreparedStatement();
//        assertTrue(helper.closePreparedStatment(ps));
//    }
//
//    @org.testng.annotations.Test (dataProvider = "createHelperAutoCommitTrue", priority = 5)
//    public void testCloseStatment(DBHelp helper) {
//        Statement st = helper.getStatement();
//        assertTrue(helper.closeStatment(st));
//    }

    // см логи в bin
    @org.testng.annotations.Ignore
    @org.testng.annotations.Test (dataProvider = "booksInsert", priority = 6)
    public void testInsertBook(Book b1, Book b2) {
        try {
            DBHelp helper = new DBHelp(false);
            PreparedStatement prepSt = helper.getPreparedStatement();
//DataProvider
            ArrayList<Book> booksList = new ArrayList<>()
            {
                {
                    add(b1);
                    add(b2);
                }
            };

            System.out.println("booksListSize = " + booksList.size());
            System.out.println(b1.getName());
            System.out.println(b2.getName());

            helper.insertBook(prepSt, booksList);
            String selectFindRequest1 = "select * from Books where [Name] = " + "\'" + b1.getName() + "\'";
            String selectFindRequest2 = "select * from Books where [Name] = " + "\'" + b2.getName() + "\'";

            Statement st = helper.getStatement();
            //helper.executeQueryBooks(st, selectFindRequest1);
            assertNotNull(st.executeQuery(selectFindRequest1));
            assertNotNull(st.executeQuery(selectFindRequest2));
            //System.out.println(helper.executeQueryBooks(prepSt, selectFindRequest2));

           assertTrue(helper.executeQueryBooks(st, selectFindRequest1)&helper.executeQueryBooks(st, selectFindRequest2));
//            assertTrue(helper.executeQueryBooks(st, selectFindRequest2));

            helper.closePreparedStatment(prepSt);
            helper.closeConnectionDB();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    // after insert only
    @org.testng.annotations.Ignore
    @org.testng.annotations.Test (dataProvider = "createHelperAutoCommitTrue", priority = 7)
    public void testDeleteBooksOlderNumYear(DBHelp helper) {
        Statement st = helper.getStatement();
       assertTrue(helper.deleteBooksOlderNumYear(st, "2021"));
        helper.closeStatment(st);
        helper.closeConnectionDB();
    }


//    @org.testng.annotations.Test (dataProvider = "createHelperAutoCommitTrue", priority = 6)
//    public void testShowAllBooksPublishedLastCurrentYears(DBHelp helper) {
//
//         Statement st = helper.getStatement();
//         assertTrue(helper.showAllBooksPublishedLastCurrentYears(st));
//         helper.closeStatment(st);
//        helper.closeConnectionDB();
//    }
//
//    @org.testng.annotations.Test (dataProvider = "createHelperAutoCommitTrue", priority = 6)
//    public void testShowAuthorsInfo(DBHelp helper) {
//
//            Statement st = helper.getStatement();
//            assertTrue(helper.showAuthorsInfo(st));
//            helper.closeStatment(st);
//        helper.closeConnectionDB();
//    }
//
    @org.testng.annotations.Test (dataProvider = "createHelperAutoCommitTrue", priority = 6)
    public void testShowAuthorsWroteNumBooks_tryNumBooksOnlyNumbersGreaterZeroInString(DBHelp helper) {

            Statement st = helper.getStatement();
            assertTrue(helper.showAuthorsWroteNumBooks(st, "2"));
            helper.closeStatment(st);
        helper.closeConnectionDB();
    }

    @org.testng.annotations.Test (dataProvider = "createHelperAutoCommitTrue", priority = 6)
    public void testShowAuthorsWroteNumBooks_tryNumBooksNotOnlyNumbersInString(DBHelp helper) {

            Statement st = helper.getStatement();
            assertFalse(helper.showAuthorsWroteNumBooks(st, "ab*cd+r@7"));
            helper.closeStatment(st);
            helper.closeConnectionDB();
    }

    @org.testng.annotations.Test (dataProvider = "createHelperAutoCommitTrue", priority = 6)
    public void testShowAuthorsWroteNumBooks_tryNumBooksOnlyNumbersLessZeroInString(DBHelp helper) {

            Statement st = helper.getStatement();
            assertFalse(helper.showAuthorsWroteNumBooks(st, "-5"));
            helper.closeStatment(st);
        helper.closeConnectionDB();
    }

    @org.testng.annotations.Test (dataProvider = "createHelperAutoCommitTrue", priority = 6)
    public void testExecuteQueryAuthors_trySelectQueryInSelectRequestVariable(DBHelp helper) {

            Statement st = helper.getStatement();
            assertTrue(helper.executeQueryAuthors(st, "select * from Authors"));
            helper.closeStatment(st);
        helper.closeConnectionDB();
    }

//    @org.testng.annotations.Test (dataProvider = "createHelperAutoCommitTrue", priority = 6)
//    public void testExecuteQueryAuthors_trySymbolsInSelectRequestVariable(DBHelp helper) {
//
//            Statement st = helper.getStatement();
//            assertFalse(helper.executeQueryAuthors(st, "abc"));
//            helper.closeStatment(st);
//        helper.closeConnectionDB();
//    }
//
//    @org.testng.annotations.Test (dataProvider = "createHelperAutoCommitTrue", priority = 6)
//    public void testExecuteQueryBooks_trySelectQueryInSelectRequestVariable(DBHelp helper) {
//        Statement st = helper.getStatement();
//        assertTrue(helper.executeQueryAuthors(st, "select * from Books"));
//        helper.closeStatment(st);
//        helper.closeConnectionDB();
//    }
//
//    @org.testng.annotations.Test (dataProvider = "createHelperAutoCommitTrue", priority = 6)
//    public void testExecuteQueryBooks_trySymbolsInSelectRequestVariable(DBHelp helper) {
//        Statement st = helper.getStatement();
//        assertFalse(helper.executeQueryAuthors(st, "shbfs"));
//        helper.closeStatment(st);
//        helper.closeConnectionDB();
//    }


}