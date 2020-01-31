package app.commands.command;

import app.Managers.ConfigurationManager;
import app.Managers.EnumManager;
import app.entities.Conference;
import app.entities.User;
import app.persistences.factory.MySqlDaoFactory;
import app.services.UserService;
import org.apache.log4j.Logger;
import sun.rmi.runtime.Log;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class UserCabinetCommand implements ICommand{

    private static final Logger logger = Logger.getLogger(UserCabinetCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        logger.info("in UserCabinetCommand");

        String page = null;

        String method = request.getMethod();
        ConfigurationManager confManager = ConfigurationManager.getInstance();

        if (method.equalsIgnoreCase(EnumManager.GET.toString())) {
            logger.info("in UserCabinet GET");
            page = confManager.getProperty(EnumManager.USER_CABINET.toString());

        }else if (method.equalsIgnoreCase(EnumManager.POST.toString())){
            logger.info("in UserCabinet POST");

            String action = request.getParameter(EnumManager.ACTION.toString());
            System.out.println("ACTION "+action);

            if(action != null){

                EnumManager speakerAction = EnumManager.valueOf(action.toUpperCase());
                User user = (User) request.getSession().getAttribute("currentUser");

                switch (speakerAction){
                    case REGISTER_IN_CONFERENCE:
                        logger.info("REGISTER_IN_CONFERENCE");
                        page = confManager.getProperty(EnumManager.USER_CABINET.toString());
                        doRegistration(request, user.getCustomerId());
                        request.getSession().setAttribute("conferencesToRegisterIn",
                                UserService.getInstance().getConfForView());
                        break;
                    default:
                        logger.info("DEFAULT IN SWITCH");
                        break;
                }
            }
        }
        return page;
    }

    private static void doRegistration(HttpServletRequest request, int userId){
        int conference_id = Integer.parseInt(request.getParameter("id"));
        MySqlDaoFactory.getConferenceDAO().registerInConf(userId,conference_id);
    }
}
