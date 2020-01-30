package app.commands.command;

import app.Managers.ConfigurationManager;
import app.Managers.EnumManager;
import app.entities.User;
import app.persistences.dao.SignInDAO;
import app.persistences.dao.SignUpDAO;
import app.persistences.factory.MySqlDaoFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpCommand implements ICommand {

    private static final Logger logger = Logger.getLogger(SignUpCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = ConfigurationManager.getInstance()
                .getProperty(EnumManager.SIGN_UP.toString());

        Boolean buttonPressed = Boolean.valueOf(request.getParameter("isPressed"));

        if(buttonPressed){
            doSignUp(request);
            User currentUser = (User) request.getSession().getAttribute("currentUser");

            if(!Boolean.getBoolean(request.
                    getParameter("userExistsErrorMessage")) && currentUser != null){
                page = ConfigurationManager.getInstance().
                        getProperty(EnumManager.USER_CABINET.toString());
            }
        }

        return page;
    }
    private static void doSignUp(HttpServletRequest request){
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

        SignInDAO signInDAO = MySqlDaoFactory.getSignInDAO();
        SignUpDAO signUpDAO = MySqlDaoFactory.getSignUpDAO();


        if(!(signUpDAO.checkBy(EnumManager.LOGIN.toString(), login) ||
                signUpDAO.checkBy(EnumManager.EMAIL.toString(), email))){

            User user = new User(firstName, lastName, email, login, password);
            MySqlDaoFactory.getSignUpDAO().createUser(user);

            User currentUser = signInDAO.getUserByLogPas(login, password);

            request.getSession().setAttribute("currentUser", currentUser);
            request.setAttribute("userExistsErrorMessage", false);
        }else {
            request.setAttribute("userExistsErrorMessage", true);
        }
    }
}