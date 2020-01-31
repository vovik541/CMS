package app.persistences.dao;

import app.entities.User;
import app.persistences.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class SignInDAO {

    private static final Logger logger = Logger.getLogger(SignInDAO.class);

    public List<User> getUsers(){
        List<User> usersList;

        String query = "SELECT * FROM сustomers";

        try (ResultSet customerResSet = ConnectionPool.getConnection().
                createStatement().executeQuery(query)) {

            usersList = initUsersList(customerResSet);
//            logger.info("Customers were read");
        } catch (SQLException e) {
            logger.error("Customers weren't read! ERROR in SignInModel.readCustomers()");
            e.printStackTrace();
            return null;
        }

        return usersList;
    }

    //returns null if login or password are not existed
    public User getUserByLogPas(String login, String password){

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet userResSet = null;

        String query = "SELECT * FROM customers WHERE login = ? AND password = ?";

        try {
            List<User> usersList = null;
            User user = null;

            connection = ConnectionPool.getConnection();

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            userResSet = preparedStatement.executeQuery();

            usersList = initUsersList(userResSet);

            if (usersList.size() == 1) {
                user = usersList.get(0);
                logger.info("user was found by login / password");
            }

            logger.info("SignInDAO.getUserByLogPas() gave output");
            return user;

        } catch (SQLException e) {
            logger.error("User wasn't gotten! Error in SignInModel.getUserByLogPas()");
            e.printStackTrace();
        }finally {
            try {
                userResSet.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                logger.warn("connection wasn't closed!! Error in SignInModel.getUserByLogPas()");
                e.printStackTrace();
            }
        }
        return null;
    }

    private List<User> initUsersList(ResultSet userResSet) throws SQLException {
        User user;
        List<User> usersList = new LinkedList<>();

        while (userResSet.next()){
            user = new User(
                    userResSet.getInt("customer_id"),
                    userResSet.getString("first_name"),
                    userResSet.getString("last_name"),
                    userResSet.getString("login"),
                    userResSet.getString("password"),
                    userResSet.getString("email"),
                    userResSet.getInt("role")
            );
            usersList.add(user);
        }
//        logger.info("initUsersList worked");
        return usersList;
    }
}
