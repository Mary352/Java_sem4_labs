package fit.bstu.Lab9.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public interface IConnector {

    static final String url = "jdbc:mysql://localhost:3306/lab9";
    static final String user = "root";
    static final String pass = "21122000Ad";
    static final String reconnect = "true";
    static final String encoding = "UTF-8";
    static final String useUnicode = "true";
    static final String dbTimeZone = "UTC";


    default Connection getConnect() throws ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connect = null;
        Properties prop = new Properties();
        prop.put("user",user);
        prop.put("password",pass);
        prop.put("autoReconnect",reconnect);
        prop.put("characterEncoding",encoding);
        prop.put("useUnicode",useUnicode);


        try {

            connect = DriverManager.getConnection(url, prop);

        }catch (SQLException  throwables) {
            throwables.printStackTrace();

        }
        return  connect;
    }
    default  void closeConnect(Connection connect){
        try {
            if(connect != null){
                connect.close();
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}
