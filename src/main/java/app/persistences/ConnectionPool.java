package app.persistences;

import app.Managers.ResourceManager;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPool {

    private static DataSource ds = null;

    static {
        try {
            Context context = new InitialContext();
            ds = (DataSource)context.lookup("java:comp/env/jdbc/poolName");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

     public static Connection getConnection(){ //EXCEPTION!!!!
         Connection connection = null;

         try {
             connection = ds.getConnection();
         } catch (SQLException e) {
             e.printStackTrace();
         }
         return connection;
    }
    public static Connection getDefaultConnection(){

        Connection defaultConnection = null;

        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

            defaultConnection = DriverManager.getConnection(ResourceManager.URL_DB.toString(),
                    ResourceManager.USERNAME_DB.toString(), ResourceManager.PASSWORD_DB.toString());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return defaultConnection;
    }
}