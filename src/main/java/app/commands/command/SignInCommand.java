package app.commands.command;

import app.Managers.ConfigurationManager;
import app.Managers.EnumManager;
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

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = ConfigurationManager.getInstance()
                .getProperty(EnumManager.SIGN_IN.toString());

        Boolean buttonPressed = Boolean.valueOf(request.getParameter("isPressed"));
        logger.info(buttonPressed);

        if(buttonPressed){

            String login = request.getParameter(EnumManager.LOGIN.toString());
            String password = request.getParameter(EnumManager.PASSWORD.toString());
            SignInDAO signInDAO = MySqlDaoFactory.getSignInDAO();

            User currentUser = signInDAO.getUserByLogPas(login,password);

            logger.info("ConfMan in SignInCommand");

            if (currentUser != null) {

                request.getSession().setAttribute("currentUser", currentUser);
                page = getPageByRole(currentUser, request);

            } else {
                request.setAttribute("errorLoginPassMessage", true);
            }
        }
        return page;
    }

    public static String getPageByRole(User user, HttpServletRequest request){

        ConfigurationManager confManager = ConfigurationManager.getInstance();
        String page;

        switch (user.getRole()){
            case USER:
                page = confManager.getProperty(EnumManager.USER_CABINET.toString());
                break;
            case SPEAKER:
                page = confManager.getProperty(EnumManager.SPEAKER_CABINET.toString());
                request.getSession().setAttribute("speakerConfList",
                        MySqlDaoFactory.getConferenceDAO().getConfBySpeakerId(user.getCustomerId()));
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