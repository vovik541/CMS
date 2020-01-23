package app.model;

import app.entities.User;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignUpModel {

    private static final Logger logger = Logger.getLogger(SignUpModel.class);

    public static void createUser(User user)    {

        String sql = "INSERT INTO cms_db.сustomers " +
                "(first_name, last_name, login, password, email, role)" +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement =
                    ConnectionPool.getConnection().prepareStatement(sql);

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setInt(6, user.getRole());
            preparedStatement.executeUpdate();

            logger.info("New user has been registered");
        } catch (SQLException e) {
            logger.error("The user wasn't created! ERROR in RedLogModel.createUser()");
            e.printStackTrace();
        }
    }
}
