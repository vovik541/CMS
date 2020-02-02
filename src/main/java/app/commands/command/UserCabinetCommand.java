package app.commands.command;

import app.Managers.ConfigurationManager;
import app.Managers.ResourceManager;
import app.entities.Conference;
import app.entities.User;
import app.persistences.factory.MySqlDaoFactory;
import app.services.UserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class UserCabinetCommand implements ICommand{

    private static final Logger logger = Logger.getLogger(UserCabinetCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        logger.info("in UserCabinetCommand");

        String page = null;

        String method = request.getMethod();
        ConfigurationManager confManager = ConfigurationManager.getInstance();

        if (method.equalsIgnoreCase(ResourceManager.GET.toString())) {
            logger.info("in UserCabinet GET");
            page = confManager.getProperty(ResourceManager.USER_CABINET.toString());

        }else if (method.equalsIgnoreCase(ResourceManager.POST.toString())){
            logger.info("in UserCabinet POST");

            String action = request.getParameter(ResourceManager.ACTION.toString());
            System.out.println("ACTION "+action);

            if(action != null){

                page = confManager.getProperty(ResourceManager.USER_CABINET.toString());

                ResourceManager speakerAction = ResourceManager.valueOf(action.toUpperCase());
                User user = (User) request.getSession().getAttribute("currentUser");

                UserService userService = UserService.getInstance();

                switch (speakerAction){
                    case REGISTER_IN_CONFERENCE:
                        logger.info("REGISTER_IN_CONFERENCE");
                        userService.doRegistration(request, user);
                        break;
                    case GIVE_RATE:
                        logger.info("GIVE_RATE");
                        doGiveRate(request, user.getCustomerId());
                        break;
                    default:
                        logger.info("DEFAULT IN SWITCH");
                        break;
                }


                request.getSession().setAttribute("conferencesWasPresentIn",
                        userService.getConfUserWasPresentIn(user.getCustomerId()));
            }
        }
        return page;
    }
    private void doGiveRate(HttpServletRequest request, int userId){
        int conferenceId = Integer.parseInt(request.getParameter("id"));
        int rate = Integer.parseInt(request.getParameter("rate"));
        MySqlDaoFactory.getConferenceDAO().setRate(rate, conferenceId, userId);
    }
}
