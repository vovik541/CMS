package app.persistences.dao;

import app.entities.User;
import app.persistences.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignUpDAO {

    private static final Logger logger = Logger.getLogger(SignUpDAO.class);

    public void createUser(User user)    {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO сustomers " +
                "(first_name, last_name, login, password, email, role)" +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try {

            connection = ConnectionPool.getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql);

            int role = 1;

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setInt(6, role);
            preparedStatement.executeUpdate();

            connection.commit();
            logger.info("New user has been registered");
        } catch (SQLException e) {
            logger.error("The user wasn't created! ERROR in SignUpModel.createUser()");
            try {
                connection.rollback();
                logger.warn("The transaction was rolled back! SignUpModel.createUser()");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                logger.warn("connection wasn't closed!! Error in SignUpDAO.createUser()");
                e.printStackTrace();
            }

        }
    }
    public Boolean checkBy(String checkParam, String value){
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        Boolean exists = false;

        String sql = "SELECT * FROM сustomers " +
                "WHERE " + checkParam + " = ?";

        try {
            connection = ConnectionPool.getConnection();

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, value);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println(checkParam + " " + value);

            while (resultSet.next()){
                exists = true;
                System.out.println(resultSet.getString("login"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                logger.warn("connection wasn't closed!! Error in SignUpDAO.checkBy()");
                e.printStackTrace();
            }
        return exists;
        }
    }
}
