package connection;

import org.apache.log4j.*;

import java.sql.*;

public class UserDao {
    //private static final Logger LOG = Logger.getLogger(UserDao.class);

    private final ConnectionCreator connectionCreator = new ConnectionCreator();

    public UserDao() throws SQLException {
    }

    public boolean isExists(String login) throws SQLException, ClassNotFoundException {
        Connection connection = connectionCreator.createConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT login FROM users WHERE login='" + login + "'");
        ResultSet resultSet = statement.executeQuery();
        //LOG.info("Check user exist");
        if (resultSet.next()) {
            return true;
        } else {
            return false;
        }
    }

    public void addUser(String login, String password,String name) throws SQLException, ClassNotFoundException {
        String query = " INSERT INTO users(login, password,last_login,login_number,name, role) "
                + " VALUES (?, ?, ?,0,?, 1)";
        Connection connection = connectionCreator.createConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1,login);
        statement.setInt(2,password.hashCode());
        statement.setString(4,name);
        java.util.Date date = new java.util.Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        statement.setTimestamp(3,timestamp);
        statement.executeUpdate();
        //LOG.info("User added");
    }

    public void setLastLogin(String login) throws SQLException, ClassNotFoundException {
        Connection connection = connectionCreator.createConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE users SET last_login = ? WHERE login = ?" );
        java.util.Date date = new java.util.Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        statement.setTimestamp(1,timestamp);
        statement.setString(2, login);
        statement.executeUpdate();
       // LOG.info("last_login updated");
    }

    public String getNameUser(String login) throws SQLException, ClassNotFoundException {
        Connection connection = connectionCreator.createConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT name FROM users WHERE login='" + login + "'");
        //LOG.info("Get name user");
        if (resultSet.next()) {
            return resultSet.getString(1);
        } else {
           // LOG.error("Fail");
            throw new SQLException();
        }
    }
    public int getUserId(String login) throws SQLException, ClassNotFoundException {
        Connection connection = connectionCreator.createConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT id FROM users WHERE login='" + login + "'");
        //LOG.info("Get user id");
        if (resultSet.next()) {
            return resultSet.getInt(1);
        } else {
           // LOG.error("Fail");
            throw new SQLException();
        }
    }

    public boolean checkFor(String login, String password) throws SQLException, ClassNotFoundException {
        Connection connection = connectionCreator.createConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT password FROM users WHERE login='" + login + "'");
       // LOG.info("Trying of authorization");
        if (resultSet.next()) {
            int retrievedPassword = resultSet.getInt(1);
            if (retrievedPassword == password.hashCode()) {
                PreparedStatement pstatement = connection.prepareStatement("UPDATE users SET login_number = login_number+1 WHERE login='" + login + "'");
                pstatement.executeUpdate();
                return true;
            }
        }
        return false;
    }

    public Timestamp getLoginTimestamp(String login) throws SQLException, ClassNotFoundException {
        Connection connection = connectionCreator.createConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT last_login FROM users WHERE login='" + login + "'");
        Timestamp result;
        //LOG.info("Get last_login");
        if (resultSet.next()) {
            result = resultSet.getTimestamp(1);
        } else {
           // LOG.error("Fail");
            throw new SQLException();
        }
        return result;
    }

    public int getLoginNumber(String login) throws SQLException, ClassNotFoundException {
        Connection connection = connectionCreator.createConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT login_number FROM users WHERE login='" + login + "'");
        ResultSet resultSet = statement.executeQuery();
      //  LOG.info("Get login_number");
        int result;
        if (resultSet.next()) {
            result = resultSet.getInt(1);
        } else {
           // LOG.error("Fail");
            throw new SQLException();
        }
        return result;
    }
    public int getRoleNumber(String login) throws SQLException, ClassNotFoundException {
        Connection connection = connectionCreator.createConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT role FROM users WHERE login='" + login + "'");
        ResultSet resultSet = statement.executeQuery();
      //  LOG.info("Get user role");
        int result;
        if (resultSet.next()) {
            result = resultSet.getInt(1);
        } else {
          //  LOG.error("Fail");
            throw new SQLException();
        }
        return result;
    }

}
