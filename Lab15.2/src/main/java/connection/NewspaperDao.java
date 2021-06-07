package connection;

import org.apache.log4j.*;

import java.sql.*;
import java.util.ArrayList;

public class NewspaperDao {
    //private static final Logger LOG = Logger.getLogger(NewspaperDao.class);
    private final ConnectionCreator connectionCreator = new ConnectionCreator();

    public NewspaperDao() throws SQLException {
    }

    public void addNewspaper(String newspapernumber, int iduser) throws SQLException, ClassNotFoundException {
        String query = " INSERT INTO newspapers(newspapernumber, userid) "
                + " VALUES (?, ?)";
        java.sql.Connection connection = connectionCreator.createConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, newspapernumber);
        statement.setInt(2, iduser);
        statement.executeUpdate();
        //LOG.info("Newspaper added");
        Sender sender = new Sender();
        sender.send();
    }

    public ArrayList<Newspaper> select(int userid) {
        ArrayList<Newspaper> newspapers = new ArrayList<Newspaper>();
        try {
            java.sql.Connection connection = connectionCreator.createConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT idnewspaper, newspapernumber FROM newspapers where userid =" + userid);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String newspapernumber = resultSet.getString("newspapernumber");
                Newspaper product = new Newspaper(id, newspapernumber, userid);
                newspapers.add(product);
            }
            //LOG.info("Newspapers selected");
        } catch (Exception ex) {
            System.out.println(ex);
            //LOG.error(ex.getMessage());
        }
        return newspapers;
    }

    public Newspaper selectOne(int id) {
        Newspaper newspaper = null;
        try {
            java.sql.Connection connection = connectionCreator.createConnection();
            String sql = "SELECT idnewspaper, newspapernumber, userid FROM newspapers WHERE idnewspaper=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {

                    int newspaperId = resultSet.getInt(1);
                    String newspapernumber = resultSet.getString(2);
                    int userid = resultSet.getInt(3);
                    newspaper = new Newspaper(newspaperId, newspapernumber, userid);
                }
                //LOG.info("Newspaper selected");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            //LOG.error(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex);
            //LOG.error(ex.getMessage());
        }
        return newspaper;
    }

    public int update(Newspaper newspaper) throws SQLException {
        java.sql.Connection connection = connectionCreator.createConnection();
        String sql = "UPDATE newspapers SET newspapernumber = ? WHERE idnewspaper = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, newspaper.getNumber());
            preparedStatement.setInt(2, newspaper.getId());
            //LOG.info("Newspaper updated");
            return preparedStatement.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex);
            //LOG.error(ex.getMessage());
        }
        return 0;
    }

    public int delete(int id) throws SQLException {
        java.sql.Connection connection = connectionCreator.createConnection();
        String sql = "DELETE FROM newspapers WHERE idnewspaper = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            //LOG.info("Newspaper deleted");
            return preparedStatement.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex);
            //LOG.error(ex.getMessage());
        }
        return 0;
    }
}
