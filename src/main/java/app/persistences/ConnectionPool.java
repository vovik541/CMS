package app.persistences;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
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
}