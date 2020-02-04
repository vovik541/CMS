package app.logic;

import app.Managers.ResourceManager;
import app.config.ConnectionPool;
import app.entities.Conference;
import app.entities.User;
import app.persistences.dao.ConferenceDAO;
import app.persistences.factory.MySqlDaoFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SignUpLogic {

    private static final Logger logger = Logger.getLogger(SignUpLogic.class);

    private static SignUpLogic instance= null;

    public static SignUpLogic getInstance() {
        if (instance == null)
            instance = new SignUpLogic();
        return instance;
    }

    private SignUpLogic(){

    }

    public void doRegistration(HttpServletRequest request, User user){
        int conference_id = Integer.parseInt(request.getParameter("id"));

        ConferenceDAO conferenceDAO= MySqlDaoFactory.getConferenceDAO();

        if(!conferenceDAO.isRegisteredInConf(user.getCustomerId(), conference_id)){
            conferenceDAO.registerInConf(user.getCustomerId(),conference_id);
        }
        List<Conference> conferences = UserCabinetLogic.getInstance().
                getConfToRegIn(user.getCustomerId());
        request.getSession().setAttribute("conferencesToRegisterIn",
                conferences);

    }

    public void doSignUp(HttpServletRequest request){
        String firstName;
        String lastName;
        String login;
        String password;
        String email;

        firstName = request.getParameter("firstName");
        lastName = request.getParameter("lastName");
        login = request.getParameter("login");
        password = request.getParameter("password");
        email = request.getParameter("email");

        if(isInputAlright(firstName, lastName, login, password, email)){

            if(!(checkBy(ResourceManager.LOGIN.toString(), login) ||
                    checkBy(ResourceManager.EMAIL.toString(), email))){

                User user = new User.Builder(firstName, lastName)
                        .setEmail(email)
                        .setLogin(login)
                        .setPassword(password)
                        .build();

                MySqlDaoFactory.getSignUpDAO().createUser(user);

                User currentUser = MySqlDaoFactory.getSignInDAO().getUserByLogPas(login, password);

                request.getSession().setAttribute("currentUser", currentUser);
                request.setAttribute("userExistsErrorMessage", false);
            }else {
                request.setAttribute("userExistsErrorMessage", true);
            }
            request.setAttribute("userInputErrorMessage", false);
        }else {
            request.setAttribute("userInputErrorMessage", true);
        }

    }

    public boolean isInputAlright(String firstName, String lastName, String login,
                                         String password, String email){

        if(firstName.isEmpty() || lastName.isEmpty() ||
                login.isEmpty() || password.isEmpty() || email.isEmpty()){
            return false;
        }
        return true;
    }

    public Boolean checkBy(String checkParam, String value){
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        Boolean exists = false;

        String sql = "SELECT * FROM cms_db.customers " +
                "WHERE " + checkParam + " = ?;";

        try {
            connection = ConnectionPool.getConnection();

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, value);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                exists = true;
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
