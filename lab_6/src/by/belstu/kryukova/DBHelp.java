package by.belstu.kryukova;

import java.sql.*;
import java.util.ArrayList;

import org.apache.log4j.Logger;

public class DBHelp {
    private static final Logger LOG = Logger.getLogger(DBHelp.class);

    private final static String BOOK_INSERT = "insert into Books ([Name], Author_Full_name, " +
            "Release_year, Publishing_house) " +
            "values (?,?,?,?)";

    private Connection connect;

    public DBHelp(boolean autoCommit) throws SQLException
    {
        LOG.info("getting connection");
        connect = ConnectorDB.getConnection();
        connect.setAutoCommit(autoCommit);
        LOG.info("connection established");
//        if (false) {
//            try {
//                connect.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
//            } catch (SQLException sqlEx) {
//                sqlEx.printStackTrace();
//            }
//        }
    }

    public boolean closeConnectionDB()
    {
        boolean OK_flag = false;
        LOG.info("closing connection");
        try {
            connect.close();
            OK_flag = true;
            System.out.println("CONNECTION CLOSED");
            LOG.info("connection closed");
        }catch (SQLException sqlE){
            LOG.info("error: connection not closed");
            sqlE.printStackTrace();
        }

        return OK_flag;
    }

    public PreparedStatement getPreparedStatement()
    {
        LOG.info("getting prepared statement");
        PreparedStatement ps = null;
        try {
            ps = connect.prepareStatement(BOOK_INSERT);
            LOG.info("prepared statement received");
        }catch (SQLException sqlE){
            LOG.info("error getting prepared statement");
            sqlE.printStackTrace();
        }

        return ps;
    }

    public Statement getStatement()
    {
        LOG.info("getting statement");
        Statement st = null;
        try {
            st = connect.createStatement();
            LOG.info("statement received");
        }catch (SQLException sqlE){
            LOG.info("error getting statement");
            sqlE.printStackTrace();
        }

        return st;
    }

   public void insertBook(PreparedStatement ps, ArrayList<Book> booksList)
   {                                            // Book book
//       // normal insert
//       try{
//               ps.setString(1, book.getName());
//               ps.setString(2, book.getAuthorFullName());
//               ps.setInt(3, book.getReleaseYear());
//               ps.setString(4, book.getPublishingHouse());
//
//               ps.executeUpdate();
//           System.out.println("BOOK INSERTED");
//
//           }catch (SQLException sqlE){
//               sqlE.printStackTrace();
//           }

//       try {
//           connect.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
//       }catch (SQLException sqlEx){
//           sqlEx.printStackTrace();
//       }
       LOG.info("---------------INSERT_DELETED_BOOKS---------------");
       try{
           for (Book book:
                   booksList) {
               ps.setString(1, book.getName());
               ps.setString(2, book.getAuthorFullName());
               ps.setInt(3, book.getReleaseYear());
               ps.setString(4, book.getPublishingHouse());

               ps.executeUpdate();

               connect.commit();
               System.out.println("BOOK INSERTED");
               LOG.info("BOOK INSERTED");
           }
       }
       catch (SQLException sqlE) {

           if (connect != null) {
               try {
                   System.err.println("The book is already exist. Transaction is being rolled back");
                   LOG.info("The book is already exist. Transaction is being rolled back");
                   connect.rollback();
               } catch (SQLException sqlEx) {
                   sqlEx.printStackTrace();
               }
           }
       }

//       Statement st = getStatement();
//       String selectRequest = "select * from Books where [Name] = " + book.getName();
//       try{
//           ResultSet rs = st.executeQuery(selectRequest);
//           if (rs == null)
//           {
//               ps.setString(1, book.getName());
//               ps.setString(2, book.getAuthorFullName());
//               ps.setInt(3, book.getReleaseYear());
//               ps.setString(4, book.getPublishingHouse());
//
//           }
//           else
//               throw new SQLException("This book is already exist");
//
//           ps.executeUpdate();
//           connect.commit();
//           System.out.println("BOOK INSERTED");

//       }catch (SQLException sqlE){
//           System.err.println("SQLState: " + sqlE.getSQLState()
//           + ". Error Message: " + sqlE.getMessage());
//           try
//           {
//               connect.rollback();
//           }catch (SQLException sqlEx){
//               sqlEx.printStackTrace();
//           }
//
//       }
//       finally {
//           //System.out.println("BOOKS INSERTED");
//       }
   }

   public boolean deleteBooksOlderNumYear(Statement st, String year)
   {
       boolean OK_flag = false;
       LOG.info("-------------------4_DELETE_BOOKS_OLDER_THEN_?_YEAR-------------------");

       String deleteRequest = "delete from Books where Release_year > " + year;
       try{
           st.executeUpdate(deleteRequest);
           System.out.println("BOOKS DELETED");
           LOG.info("BOOKS DELETED");
           OK_flag = true;
       }catch (SQLException sqlE){
           LOG.error("error deleting books");
           sqlE.printStackTrace();
       }

       return OK_flag;
   }

   public boolean showAllBooksPublishedLastCurrentYears(Statement st)
   {
       LOG.info("-------------------1_LAST_AND_CURRENT_YEAR_BOOKS-------------------");
       boolean OK_flag = false;
       ResultSet rs = null;

       try{
           LOG.info("db select request");
           rs = st.executeQuery("select * from Books where Release_year = YEAR(GETDATE()) or Release_year = YEAR(GETDATE()) - 1");
           ArrayList<Book> booksList = new ArrayList<>();

           while (rs.next())
           {
               String name = rs.getString(1);
               String authorFullName = rs.getString(2);
               int releaseYear = rs.getInt(3);
               String publishingHouse = rs.getString(4);

               booksList.add(new Book(name, authorFullName, releaseYear, publishingHouse));

                }
                if (booksList.size() > 0) {
                    LOG.info("SELECTED BOOKS");
                    for (Book book: booksList)
                    {
                        System.out.println(book.getName() + " - " + book.getAuthorFullName()  + " - "
                                + book.getReleaseYear() + " - " + book.getPublishingHouse());

                        LOG.info(book.getName() + " - " + book.getAuthorFullName()  + " - "
                                + book.getReleaseYear() + " - " + book.getPublishingHouse());
                    }
                    OK_flag = true;
                    LOG.info("END LIST");
                } else {
                    System.out.println("No books found");
                    LOG.info("No books found");
                    OK_flag = false;
                }

       }catch (SQLException sqlE){
           LOG.error("error db select request");
           sqlE.printStackTrace();
       }

       return OK_flag;
   }

    public boolean showAuthorsInfo(Statement st)
    {
        LOG.info("-------------------2_AUTHORS_INFO-------------------");
       return executeQueryAuthors(st, "select * from Authors");

        //        ResultSet rs = null;
//
//        try{
//            rs = st.executeQuery("select * from Authors");
//            ArrayList<Author> authorsList = new ArrayList<>();
//
//            while (rs.next())
//            {
//                String name = rs.getString(1);
//                String country = rs.getString(2);
//                authorsList.add(new Author(name, country));
//            }
//            if (authorsList.size() > 0) {
//               for (Author item : authorsList) {
//                        System.out.println(item.getFull_name() + " - " + item.getCountry());
//                    }
//                } else {
//                    System.out.println("No authors found");
//                }
//
//        }catch (SQLException sqlE){
//            sqlE.printStackTrace();
//        }
    }

    public boolean showAuthorsWroteNumBooks(Statement st, String numBooks)
    {
        // TODO: 1 test - try "abcd..." - assertFalse
        //  2 test - try numBooks < 0 assertFalse
        //  3 test - norm

        String selectRequest = "";
        int intNumBooks = -1;
        // no expectedExceptions in tests when catch here
        try {
            intNumBooks = Integer.parseInt(numBooks);
        }
        catch (NumberFormatException e)
        {
            e.printStackTrace();
            return false;
        }
        LOG.info("-------------------3_AUTHORS_WROTE_?_BOOKS-------------------");
        if (intNumBooks >= 0)
        {
            selectRequest = "select * from Authors where Full_name in (select Full_name from (select Authors.Full_name, COUNT(Books.[Name]) [count_col] " +
                    "from Authors join Books on Books.Author_Full_name = Authors.Full_name " +
                    "group by Authors.Full_name) as newTable where [count_col] = " + numBooks + ")";
            return executeQueryAuthors(st, selectRequest);
        }

        return false;
    }

    public boolean executeQueryAuthors(Statement st, String selectRequest)
    {
        boolean OK_flag = false;
        ResultSet rs = null;
        try{
            LOG.info("db select request");
            rs = st.executeQuery(selectRequest);
            ArrayList<Author> authorsList = new ArrayList<>();

            while (rs.next())
            {
                String name = rs.getString(1);
                String country = rs.getString(2);
                authorsList.add(new Author(name, country));
            }
            if (authorsList.size() > 0) {
                LOG.info("SELECTED AUTHORS");
                for (Author item : authorsList) {
                    System.out.println(item.getFull_name() + " - " + item.getCountry());
                    LOG.info(item.getFull_name() + " - " + item.getCountry());
                    OK_flag = true;
                }
                LOG.info("END LIST");
            } else {
                System.out.println("No authors found");
                LOG.info("No authors found");
                OK_flag = false;
            }
        }catch (SQLException sqlE){
            LOG.error("error db select request");
            sqlE.printStackTrace();
        }

        return OK_flag;
    }

    public boolean executeQueryBooks(Statement st, String selectRequest)
    {
        boolean OK_flag = false;
        ResultSet rs = null;
        try{
            LOG.info("db select request");
            rs = st.executeQuery(selectRequest);
            ArrayList<Book> booksList = new ArrayList<>();

            while (rs.next())
            {
                String name = rs.getString(1);
                String authorFullName = rs.getString(2);
                int releaseYear = rs.getInt(3);
                String publishingHouse = rs.getString(4);
                booksList.add(new Book(name, authorFullName, releaseYear, publishingHouse));
            }
            if (booksList.size() > 0) {
                LOG.info("SELECTED BOOKS");
                for (Book book : booksList) {
                    System.out.println(book.getName() + " - " + book.getAuthorFullName() + " - " + book.getPublishingHouse()
                            + " - " + book.getReleaseYear());
                    LOG.info(book.getName() + " - " + book.getAuthorFullName() + " - " + book.getPublishingHouse()
                            + " - " + book.getReleaseYear());
                }
                LOG.info("END LIST");

                OK_flag = true;
            } else {
                System.out.println("No authors found");
                LOG.info("No authors found");
                OK_flag = false;
            }
        }catch (SQLException sqlE){
            LOG.error("error db select request");
            sqlE.printStackTrace();
        }
        return OK_flag;
    }

   public boolean closePreparedStatment(PreparedStatement ps)
   {
       boolean OK_flag = false;
       LOG.info("closing prepared statment");
       if (ps != null)
       {
           try{
               ps.close();
               OK_flag = true;
               LOG.info("prepared statment closed");
           }catch (SQLException sqlE){
               LOG.error("error closing prepared statment");
               sqlE.printStackTrace();
           }
       }
       return OK_flag;
   }

    public boolean closeStatment(Statement st)
    {
        boolean OK_flag = false;
        LOG.info("closing statment");
        if (st != null)
        {
            try{
                st.close();
                OK_flag = true;
                LOG.info("statment closed");
            }catch (SQLException sqlE){
                LOG.error("error closing statment");
                sqlE.printStackTrace();
            }
        }
        return OK_flag;
    }
}
