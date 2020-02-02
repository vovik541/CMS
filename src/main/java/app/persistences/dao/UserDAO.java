package app.persistences.dao;

import app.entities.User;
import org.apache.log4j.Logger;
import sun.rmi.runtime.Log;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserDAO {

    Logger logger = Logger.getLogger(UserDAO.class);

    private static final String GET_NUMBER_OF_CUSTOMERS = "SELECT COUNT(customer_id) AS total FROM customers;";
    private static final String GET_CUSTOMERS_FOR_PAGINATION = "SELECT customer_id, first_name, last_name," +
            "login, email, role FROM customers ORDER BY first_name LIMIT ?, ?;";

    public List<User> getUsersForPag(int currentPge, int recordsPerPage){

        List<User> users;
        QueryExecutor executor = new QueryExecutor();
        Object[] arguments = {(currentPge-1)*recordsPerPage, recordsPerPage};

        ResultSet resultSet = executor.getResultSet(GET_CUSTOMERS_FOR_PAGINATION, arguments);

        users = getUsersFromResSet(resultSet);

        executor.close();

        return users;
    }

    public int getNumOfCustomers(){

        QueryExecutor executor = new QueryExecutor();
        Object[] arguments = {};
        int nOfUsers = 0;

        ResultSet resultSet = executor.getResultSet(GET_NUMBER_OF_CUSTOMERS,arguments);

        try {
            if (resultSet.next()){
                nOfUsers = resultSet.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        executor.close();

        return  nOfUsers;
    }

    private static List<User> getUsersFromResSet(ResultSet resultSet){

        List<User> users = new LinkedList<>();

        try {
            while (resultSet.next()){
                users.add(new User(
                        resultSet.getInt("customer_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),
                        resultSet.getString("login"),
                        resultSet.getInt("role")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
}
