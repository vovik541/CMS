package app.commands.command;

import app.Managers.ConfigurationManager;
import app.Managers.ResourceManager;
import app.entities.User;
import app.persistences.dao.SignInDAO;
import app.persistences.dao.SignUpDAO;
import app.persistences.factory.MySqlDaoFactory;
import app.services.UserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpCommand implements ICommand {

    private static final Logger logger = Logger.getLogger(SignUpCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("in SignUpCommand");

        String method = request.getMethod();
        ConfigurationManager confManager = ConfigurationManager.getInstance();

        String page = confManager.getProperty(ResourceManager.SIGN_UP.toString());

        if (method.equalsIgnoreCase(ResourceManager.GET.toString())) {

            return page;

        }else if (method.equalsIgnoreCase(ResourceManager.POST.toString())){

            doSignUp(request);

            User currentUser = (User) request.getSession().getAttribute("currentUser");

            if(currentUser != null){
                logger.info("user exists in SignUpCommand");
                page = UserService.getInstance().getPageByRole(currentUser,request);
                request.setAttribute("command","user_cabinet");
                logger.info(request.getParameter("command")+" COMMAND REMOVED!");
                return page;
            }

            if(!Boolean.getBoolean(request.getParameter("userExistsErrorMessage")) &&
                    currentUser != null){
                page = UserService.getInstance().getPageByRole(currentUser,request);
            }
            return page;
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

        if(isInputAlright(firstName,lastName,login,password,email)){

            SignInDAO signInDAO = MySqlDaoFactory.getSignInDAO();
            SignUpDAO signUpDAO = MySqlDaoFactory.getSignUpDAO();

            if(!(signUpDAO.checkBy(ResourceManager.LOGIN.toString(), login) ||
                    signUpDAO.checkBy(ResourceManager.EMAIL.toString(), email))){

                User user = new User(firstName, lastName, email, login, password);
                MySqlDaoFactory.getSignUpDAO().createUser(user);

                User currentUser = signInDAO.getUserByLogPas(login, password);

                request.getSession().setAttribute("currentUser", currentUser);
                request.setAttribute("userExistsErrorMessage", false);
            }else {
                request.setAttribute("userExistsErrorMessage", true);
            }
            request.setAttribute("userInputErrorMessage", false);
        }

        request.setAttribute("userInputErrorMessage", true);
    }
    private static boolean isInputAlright(String firstName, String lastName, String login,
                                          String password, String email){

        if(firstName.isEmpty() || lastName.isEmpty() ||
                login.isEmpty() || password.isEmpty() || email.isEmpty()){
            return false;
        }
        return true;
    }
}