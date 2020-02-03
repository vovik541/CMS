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
    private static final String GIVE_ROLE = "UPDATE customers SET role = ? WHERE customers.customer_id = ?;";
    private static final String GET_SPEAKERS_FOR_OPTION="SELECT customer_id, first_name, last_name \n" +
            "FROM customers WHERE role = '2';";

//    public List<User> getSpeakersForOption(){
//        List<User>
//    }

    public void giveRole(int role ,int userId){

        QueryExecutor executor = new QueryExecutor();
        Object[] arguments = {role, userId};

        executor.executeStatement(GIVE_ROLE, arguments);

        executor.close();

    }

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

        String firstName;
        String lastName;
        String login;
        String email;
        int customerId;
        int role;

        try {
            while (resultSet.next()){

                customerId = resultSet.getInt("customer_id");
                firstName = resultSet.getString("first_name");
                lastName = resultSet.getString("last_name");
                login = resultSet.getString("login");
                email = resultSet.getString("email");
                role = resultSet.getInt("role");

                users.add(
                        new User.Builder(firstName, lastName)
                                .setCustomerId(customerId)
                                .setLogin(login)
                                .setEmail(email)
                                .setRole(role)
                                .build()
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
}
