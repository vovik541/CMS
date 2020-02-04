package app.persistences.dao;

import app.entities.User;
import app.config.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class SignInDAO {

    private static final Logger logger = Logger.getLogger(SignInDAO.class);

    private static final String GET_USERS = "SELECT * FROM сustomers;";
    private static final String GET_USER_BY_LOG_PASS = "SELECT * FROM customers WHERE login = ? AND password = ?";

    public List<User> getUsers(){
        List<User> usersList;
        QueryExecutor executor = new QueryExecutor();
        Object[] arguments = {};

        ResultSet resultSet = executor.getResultSet(GET_USERS, arguments);

        usersList = initUsersList(resultSet);

        executor.close();

        return usersList;
    }

    //returns null if login or password are not existed

    public User getUserByLogPas(String login, String password){

        QueryExecutor executor = new QueryExecutor();
        Object[] arguments = {login, password};
        List<User> usersList;
        User user = null;

        ResultSet resultSet = executor.getResultSet(GET_USER_BY_LOG_PASS, arguments);
        usersList = initUsersList(resultSet);

        executor.close();

        if (usersList.size() == 1) {
            user = usersList.get(0);
            logger.info("user was found by login / password");
        }else {
            logger.warn("User wasn't gotten! Error in SignInModel.getUserByLogPas()");
        }
        logger.info("SignInDAO.getUserByLogPas() gave output");

        return user;
    }

    private List<User> initUsersList(ResultSet userResSet) {

        List<User> usersList = new LinkedList<>();
        String firstName;
        String lastName;
        String login;
        String password;
        String email;
        int customerId;
        int role;

        try {
            while (userResSet.next()){

                customerId = userResSet.getInt("customer_id");
                firstName = userResSet.getString("first_name");
                lastName = userResSet.getString("last_name");
                login = userResSet.getString("login");
                password = userResSet.getString("password");
                email = userResSet.getString("email");
                role = userResSet.getInt("role");

                usersList.add(
                        new User.Builder(firstName,lastName)
                                .setCustomerId(customerId)
                                .setLogin(login)
                                .setEmail(email)
                                .setPassword(password)
                                .setRole(role)
                                .build()
                );
            }
        } catch (SQLException e) {
            logger.error("Customers weren't read! ERROR in SignInDAO.readCustomers()");
            e.printStackTrace();
        }

//        logger.info("initUsersList worked");
        return usersList;
    }
}
