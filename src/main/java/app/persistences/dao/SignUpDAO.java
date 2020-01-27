package app.persistences.dao;

import app.entities.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignUpDAO {

    private static final Logger logger = Logger.getLogger(SignUpDAO.class);

    public void createUser(User user)    {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO cms_db.сustomers " +
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
                logger.warn("connection wasn't closed!! Error in SignUpModel.createUser()");
                e.printStackTrace();
            }

        }
    }
}
