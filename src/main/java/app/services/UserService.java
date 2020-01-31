package app.services;

import app.Managers.ConfigurationManager;
import app.Managers.EnumManager;
import app.entities.Conference;
import app.entities.Role;
import app.entities.User;
import app.persistences.factory.MySqlDaoFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.List;

public class UserService {

    private static final Logger logger = Logger.getLogger(UserService.class);

    private UserService() {
    }

    private static UserService instance = null;

    public static synchronized UserService getInstance() {
        if (instance == null)
            instance = new UserService();
        return instance;
    }

    public static String getPageByRole(User user, HttpServletRequest request){

        ConfigurationManager confManager = ConfigurationManager.getInstance();
        String page;

        switch (user.getRole()){
            case USER:
                logger.info("IN USER SERVICE USER");
                page = confManager.getProperty(EnumManager.USER_CABINET.toString());
                request.getSession().setAttribute("conferencesToRegisterIn",
                        UserService.getInstance().getConfForView());
//                request.setAttribute("action","user_cabinet");
//                logger.info(request.getParameter("action"));
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
            default: page = confManager.getProperty(EnumManager.SIGN_IN.toString());
                break;
        }

        return page;
    }

    public List<Conference> getConfForView(){

        Calendar c=Calendar.getInstance();

        int year=c.get(c.YEAR);
        int month=c.get(c.MONTH)+1;
        int day = c.get(c.DAY_OF_MONTH);
        int hours = c.get(c.HOUR_OF_DAY);
        int minutes = c.get(c.MINUTE);

        logger.info("TIME AND DATE ARE GOTTEN");

        String currentDate = year+"-"+toFormat(month)+"-"+toFormat(day)+" 00:00:00";
        String currentTime = toFormat(hours)+":"+toFormat(minutes)+":00";

        logger.info(currentDate +"!!!"+currentTime );
        List<Conference> conferences =  MySqlDaoFactory.getConferenceDAO().
                getConfBeforeDateTime(currentDate,currentTime);
        System.out.println("CONSISTS OF !!!!!!!!!!!!!!!!!!!!!!!"+conferences.size());

        return conferences;
    }

    private static String toFormat(int number){
        if(number < 10){
            return "0"+number;
        }
        return String.valueOf(number);
    }
    private static int parseRoleToInt(Role role){

        switch (role){
            case USER:
                return 1;
            case SPEAKER:
                return 2;
            case MODER:
                return 3;
            case ADMIN:
                return 4;
        }

        return -1;
    }
}
