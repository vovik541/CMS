package app.commands.command;

import app.Managers.ConfigurationManager;
import app.Managers.ResourceManager;
import app.entities.User;
import app.persistences.dao.SignInDAO;
import app.persistences.factory.MySqlDaoFactory;
import app.services.EmptyCommandService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInCommand implements ICommand{

    private static final Logger logger = Logger.getLogger(SignInCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        logger.info("in SignInCommand");

        String page = ConfigurationManager.getInstance()
                .getProperty(ResourceManager.SIGN_IN.toString());

        String method = request.getMethod();

//        Boolean buttonPressed = Boolean.valueOf(request.getParameter("isPressed"));
//        logger.info(buttonPressed);
        User currentUser = (User)request.getSession().getAttribute("currentUser");

        //if user logged -> go to his cabinet
        if(currentUser != null){
            logger.info("user exists in SignInCommand");
            page = EmptyCommandService.getInstance().getPageByRole(currentUser,request);
            return page;
        }

        if (method.equalsIgnoreCase(ResourceManager.POST.toString())) {

            logger.info("POST in SignInCommand");

            String login = request.getParameter(ResourceManager.LOGIN.toString());
            String password = request.getParameter(ResourceManager.PASSWORD.toString());
            SignInDAO signInDAO = MySqlDaoFactory.getSignInDAO();

            currentUser = signInDAO.getUserByLogPas(login,password);

            if (currentUser != null) {

                logger.info("user logged in SignInCommand");
                request.getSession().setAttribute("currentUser", currentUser);
                page = EmptyCommandService.getInstance().getPageByRole(currentUser, request);

            } else {

                logger.info("incorrect input in SignInCommand");
                request.setAttribute("errorLoginPassMessage", true);
            }
        }
        return page;
    }
}