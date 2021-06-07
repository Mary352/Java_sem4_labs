package by.belstu.kryukova;

import java.sql.*;
import java.util.ArrayList;

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

        LOG.info("------------------Starting program------------------");

        DBHelp helper = null;

        System.out.println("-------------------1_LAST_AND_CURRENT_YEAR_BOOKS-------------------");
        Statement st = null;

        try{
            helper = new DBHelp(true);
            st = helper.getStatement();
            helper.showAllBooksPublishedLastCurrentYears(st);
            helper.closeStatment(st);
        }catch (SQLException sqlE){
            sqlE.printStackTrace();
        }

        System.out.println("-------------------2_AUTHORS_INFO-------------------");
//        try{
//            helper = new DBHelp();
//            st = helper.getStatement();
//            helper.showAllBooksPublishedLastCurrentYears(st);
//
//        }catch (SQLException sqlE){
//            sqlE.printStackTrace();
//        } finally {
//            helper.closeStatment(st);
//        }
        st = helper.getStatement();
        helper.showAuthorsInfo(st);
        helper.closeStatment(st);

        System.out.println("-------------------3_AUTHORS_WROTE_?_BOOKS-------------------");
        st = helper.getStatement();
        helper.showAuthorsWroteNumBooks(st, "2");
        helper.closeStatment(st);

        System.out.println("-------------------4_DELETE_BOOKS_OLDER_THEN_?_YEAR-------------------");
        st = helper.getStatement();
        helper.deleteBooksOlderNumYear(st, "2014");
        helper.closeStatment(st);
        helper.closeConnectionDB();

        System.out.println("---------------INSERT_DELETED_BOOKS---------------");
        ArrayList<Book> booksList = new ArrayList<>()
        {
            {
                add(new Book("1984", "Дж. Оруэлл", 2021, "Азбука-классика"));
                add(new Book("Скотный двор", "Дж. Оруэлл", 2020, "Азбука-классика"));
                add(new Book("Триумфальная арка", "Эрих Мария Ремарк", 2020, "АСТ"));
                add(new Book("Старик и море", "Э. Хемингуэй", 2021, "Эксмо"));
            }
        };

        PreparedStatement prepSt = null;

        try{
            helper = new DBHelp(false);
            prepSt = helper.getPreparedStatement();
            helper.insertBook(prepSt, booksList);
//            for (Book book:
//                 booksList) {
//                helper.insertBook(prepSt, book);
//            }
        }catch (SQLException sqlE){
            sqlE.printStackTrace();
        } finally {
            helper.closePreparedStatment(prepSt);
            helper.closeConnectionDB();
        }





//        String url = "jdbc:sqlserver://localhost:1433;databaseName=LibraryDB;integratedSecurity=true;";
//        String url2 = "jdbc:sqlserver://USER-PC\\SQLEXPRESS:1433;databaseName=LibraryDB;IntegratedSecurity = true";
//        Connection cn = null;
//        try { // 1 блок
//            cn = ConnectorDB.getConnection();
//            Statement st = null;
//
//        try { // 2 блок
//            st = cn.createStatement();
//            ResultSet rs = null;
//
//            try { // 3 блок
//                rs = st.executeQuery("SELECT * FROM Authors");
//                ArrayList<Author> lst = new ArrayList<>();
//                while (rs.next()) {
//                    String name = rs.getString(1);
//                    String country = rs.getString(2);
//                    lst.add(new Author(name, country));
//                }
//                if (lst.size() > 0) {
//                    for (Author item:
//                         lst) {
//                        System.out.println(item.getFull_name() + " " + item.getCountry());
//                    }
//                } else {
//                    System.out.println("Not found");
//                }
//
//
//            }catch (Exception throwables) {
//                throwables.printStackTrace();
//            }
//        } catch (Exception throwables) {
//            throwables.printStackTrace();
//        }
//        finally {
//            st.close();
//            cn.close();
//        }
//
//        } catch (SQLException throwables) {
//        throwables.printStackTrace();
//    }

    }
}