package app.services;

import app.Managers.ConfigurationManager;
import app.Managers.ResourceManager;
import app.entities.Conference;
import app.entities.Role;
import app.entities.User;
import app.persistences.dao.ConferenceDAO;
import app.persistences.factory.MySqlDaoFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    public String getPageByRole(User user, HttpServletRequest request){

        ConfigurationManager confManager = ConfigurationManager.getInstance();
        String page;

        switch (user.getRole()){
            case USER:
                logger.info("IN USER SERVICE USER");
                page = confManager.getProperty(ResourceManager.USER_CABINET.toString());
                List<Conference> conferences = getConfToRegIn(user.getCustomerId());
                request.getSession().setAttribute("conferencesToRegisterIn",
                        conferences);
                request.getSession().setAttribute("conferencesWasPresentIn",
                        getConfUserWasPresentIn(user.getCustomerId()));
                break;
            case SPEAKER:
                page = confManager.getProperty(ResourceManager.SPEAKER_CABINET.toString());
                request.getSession().setAttribute("speakerConfList",
                        MySqlDaoFactory.getConferenceDAO().getConfBySpeakerId(user.getCustomerId()));
                break;
            case MODER:
                page = confManager.getProperty(ResourceManager.MODER_CABINET.toString());
                break;
            case ADMIN:
                page = confManager.getProperty(ResourceManager.ADMIN_CABINET.toString());
                break;
            default: page = confManager.getProperty(ResourceManager.SIGN_IN.toString());
                break;
        }

        return page;
    }

    private java.sql.Date getCurrentDay(){

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

    private String getCurrentTime(){

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
                getConfBeforeDateTime(currentDate,currentTime);

        return conferences;
    }

    private String toFormat(int number){
        if(number < 10){
            return "0"+number;
        }
        return String.valueOf(number);
    }
    private int parseRoleToInt(Role role){

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
    public void doRegistration(HttpServletRequest request, int userId){
        int conference_id = Integer.parseInt(request.getParameter("id"));

        ConferenceDAO conferenceDAO= MySqlDaoFactory.getConferenceDAO();

        if(!conferenceDAO.isRegisteredInConf(userId, conference_id)){
            conferenceDAO.registerInConf(userId,conference_id);
        }
    }

    public List<Conference> getConfToRegIn(int userId){

        //here we got all conferences that didn't pass/end until now

        List<Conference> conferences = UserService.getInstance().getConfForView();

        //id of conferences that user registered in

        ArrayList<Integer> confId = MySqlDaoFactory.getConferenceDAO().getRegisteredConfId(userId);

        //writing "registered" in conferences

        for(Conference conference: conferences){
            for (Integer conferenceId:confId){
                if(conference.getConferenceId()==conferenceId){
                    conference.setRegistered(true);
                }
            }
        }
        return conferences;
    }
    public List<Conference> getConfUserWasPresentIn(int userId){
        List<Conference> conferences = MySqlDaoFactory.getConferenceDAO().
                getConfUserWasPresentIn(userId,getCurrentDay(),getCurrentTime());
        return conferences;
    }
}
