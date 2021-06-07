package by.belstu.kryukova;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectorDB {

    public static Connection getConnection() throws SQLException {

        ResourceBundle resource = ResourceBundle.getBundle("db");
        String url = resource.getString("db.url");

        return DriverManager.getConnection(url);
    }
}


