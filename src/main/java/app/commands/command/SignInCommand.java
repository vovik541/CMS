package app.commands.command;

import app.Managers.ConfigurationManager;
import app.Managers.EnumManager;
import app.entities.Role;
import app.entities.User;
import app.persistences.dao.SignInDAO;
import app.persistences.factory.MySqlDaoFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInCommand implements ICommand{

    private static final Logger logger = Logger.getLogger(SignInCommand.class);

    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = null;
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        SignInDAO signInDAO = MySqlDaoFactory.getSignInDAO();

        User currentUser = signInDAO.getUserByLogPas(login,password);

        logger.info("ConfMan in SignInCommand");
        ConfigurationManager confManager = ConfigurationManager.getInstance();

        if (currentUser != null) {

            request.getSession().setAttribute("currentUser", currentUser);
            page = getPageByRole(currentUser.getRole());

        } else {
            request.setAttribute("errorLoginPassMessage", true);
            page = confManager.getProperty(EnumManager.SIGN_IN.toString());
        }
        return page;
    }

    public static String getPageByRole(Role role){

        ConfigurationManager confManager = ConfigurationManager.getInstance();
        String page;

        switch (role){
            case USER:
                page = confManager.getProperty(EnumManager.USER_CABINET.toString());
                break;
            case SPEAKER:
                page = confManager.getProperty(EnumManager.SPEAKER_CABINET.toString());
                break;
            case MODER:
                page = confManager.getProperty(EnumManager.MODER_CABINET.toString());
                break;
            case ADMIN:
                page = confManager.getProperty(EnumManager.ADMIN_CABINET.toString());
                break;
            default: page = confManager.getProperty(EnumManager.INDEX.toString());
                break;
        }

        return page;
    }
}