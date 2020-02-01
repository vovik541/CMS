package app.commands.command;

import app.Managers.ConfigurationManager;
import app.Managers.ResourceManager;
import app.entities.Conference;
import app.entities.User;
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

                ResourceManager speakerAction = ResourceManager.valueOf(action.toUpperCase());
                User user = (User) request.getSession().getAttribute("currentUser");

                UserService userService = UserService.getInstance();

                switch (speakerAction){
                    case REGISTER_IN_CONFERENCE:
                        page = confManager.getProperty(ResourceManager.USER_CABINET.toString());

                        logger.info("REGISTER_IN_CONFERENCE");
                        userService.doRegistration(request, user.getCustomerId());

                        List<Conference> conferences = userService.getConfToRegIn(user.getCustomerId());
                        request.getSession().setAttribute("conferencesToRegisterIn",
                                conferences);
                        break;
                    case GIVE_RATE:

                        break;
                    default:
                        logger.info("DEFAULT IN SWITCH");
                        break;
                }
            }
        }
        return page;
    }
    private void doGiveRate(int userId, int conferenceId){

    }

}
