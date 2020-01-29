package app.persistences;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    protected static final String USERNAME = "root";
    protected static final String PASSWORD = "root";
    protected static final String URL = "jdbc:mysql://localhost:3306/cms_db?useSSL=false&serverTimezone=UTC";


    public static Connection getConnection() throws SQLException {


        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}