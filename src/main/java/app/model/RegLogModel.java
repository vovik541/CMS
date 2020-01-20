package app.model;

import app.entities.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class RegLogModel {

//    private static final Logger logger = Logger.getLogger(RegLogModel.class);

    public static void createUser(User user){

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

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<User> readCustomers(){
        List<User> usersList = new LinkedList<>();

        String query = "SELECT * FROM cms_db.сustomers";

        try (ResultSet customerResSet = ConnectionPool.getConnection().
                createStatement().executeQuery(query)) {

            usersList = initialize(customerResSet);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return usersList;
    }
    public static User readCustomByLogPas(String login, String password){

        String query = "SELECT * FROM cms_db.сustomers WHERE login = ? AND password = ?";

        try {
            List<User> usersList = null;
            User user = null;

            Connection connection = ConnectionPool.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet userResSet = preparedStatement.executeQuery();

            usersList = initialize(userResSet);

            if(usersList.size()>0){
                user = usersList.get(0);
            }

            userResSet.close();
            preparedStatement.close();
            connection.close();

            return user;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static List<User> initialize(ResultSet userResSet) throws SQLException {
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
        return usersList;
    }
}
