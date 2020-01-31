package app.commands.command;

import app.Managers.ConfigurationManager;
import app.Managers.EnumManager;
import app.entities.User;
import app.persistences.dao.SignInDAO;
import app.persistences.factory.MySqlDaoFactory;
import app.services.UserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInCommand implements ICommand{

    private static final Logger logger = Logger.getLogger(SignInCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = ConfigurationManager.getInstance()
                .getProperty(EnumManager.SIGN_IN.toString());

        String method = request.getMethod();

//        Boolean buttonPressed = Boolean.valueOf(request.getParameter("isPressed"));
//        logger.info(buttonPressed);

        if (method.equalsIgnoreCase(EnumManager.POST.toString())) {

            String login = request.getParameter(EnumManager.LOGIN.toString());
            String password = request.getParameter(EnumManager.PASSWORD.toString());
            SignInDAO signInDAO = MySqlDaoFactory.getSignInDAO();

            User currentUser = signInDAO.getUserByLogPas(login,password);

            logger.info("ConfMan in SignInCommand");

            if (currentUser != null) {

                request.getSession().setAttribute("currentUser", currentUser);
                page = UserService.getInstance().getPageByRole(currentUser, request);

            } else {
                request.setAttribute("errorLoginPassMessage", true);
            }
        }
        return page;
    }
}