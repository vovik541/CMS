package app.services;

import app.Managers.ConfigurationManager;
import app.Managers.ResourceManager;
import app.entities.Conference;
import app.entities.Role;
import app.entities.User;
import app.logic.ModerCabinetLogic;
import app.logic.SpeakerCabinetLogic;
import app.logic.UserCabinetLogic;
import app.persistences.factory.MySqlDaoFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class EmptyCommandService {

    private static final Logger logger = Logger.getLogger(EmptyCommandService.class);

    private EmptyCommandService() {
    }

    private static EmptyCommandService instance = null;

    public static synchronized EmptyCommandService getInstance() {
        if (instance == null)
            instance = new EmptyCommandService();
        return instance;
    }

    public String getPageByRole(User user, HttpServletRequest request){

        ConfigurationManager confManager = ConfigurationManager.getInstance();
        String page;

        UserCabinetLogic userCabinetLogic = UserCabinetLogic.getInstance();

        switch (user.getRole()){
            case USER:
                logger.info("IN USER SERVICE USER");
                page = confManager.getProperty(ResourceManager.USER_CABINET.toString());
                List<Conference> conferences = userCabinetLogic.getConfToRegIn(user.getCustomerId());
                request.getSession().setAttribute("conferencesToRegisterIn",
                        conferences);
                request.getSession().setAttribute("conferencesWasPresentIn",
                        userCabinetLogic.getConfUserWasPresentIn(user.getCustomerId()));
                break;
            case SPEAKER:
                page = confManager.getProperty(ResourceManager.SPEAKER_CABINET.toString());
                request.getSession().setAttribute("speakerConfList",
                        MySqlDaoFactory.getConferenceDAO().getConfBySpeakerId(user.getCustomerId()));
                request.getSession().setAttribute("speakerRate", SpeakerCabinetLogic.getInstance().
                        getSpeakerRate(user.getCustomerId()));
                break;
            case MODER:
                page = confManager.getProperty(ResourceManager.MODER_CABINET.toString());
                request.getSession().setAttribute("speakersForOption",
                        MySqlDaoFactory.getUserDAO().getSpeakersForOption());
                ModerCabinetLogic.getInstance().doUserPagination(request);
                request.setAttribute("pastConferences",
                        MySqlDaoFactory.getConferenceDAO().getConfBeforeDateTime(getCurrentDay(),
                                getCurrentTime()));
                request.setAttribute("currentConferences",
                        MySqlDaoFactory.getConferenceDAO().getCurrentConferences(getCurrentDay(),
                                getCurrentTime()));
                request.getSession().setAttribute("conferencesToRegisterIn",
                        userCabinetLogic.getConfToRegIn(user.getCustomerId()));
                break;
            case ADMIN:
                page = confManager.getProperty(ResourceManager.ADMIN_CABINET.toString());
                break;
            default: page = confManager.getProperty(ResourceManager.SIGN_IN.toString());
                break;
        }

        return page;
    }

    public static java.sql.Date getCurrentDay(){

        Calendar c=Calendar.getInstance();

        int year=c.get(c.YEAR);
        int month=c.get(c.MONTH)+1;
        int day = c.get(c.DAY_OF_MONTH);

        String currentDateStr = year+"-"+toFormat(month)+"-"+toFormat(day)+" 00:00:00";

        java.util.Date dateStr = null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            dateStr = formatter.parse(currentDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date currentDateDB = new java.sql.Date(dateStr.getTime());

        return currentDateDB;
    }

    public static String getCurrentTime(){

        Calendar c=Calendar.getInstance();

        int hours = c.get(c.HOUR_OF_DAY);
        int minutes = c.get(c.MINUTE);

        String currentTime = toFormat(hours)+":"+toFormat(minutes)+":00";

        return currentTime;
    }

    public List<Conference> getConfForView(){

        java.sql.Date currentDate = getCurrentDay();
        String currentTime = getCurrentTime();

        List<Conference> conferences =  MySqlDaoFactory.getConferenceDAO().
                getConfAfterDateTime(currentDate,currentTime);

        return conferences;
    }

    public static String toFormat(int number){
        if(number < 10){
            return "0"+number;
        }
        return String.valueOf(number);
    }
}
