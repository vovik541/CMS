package app.commands.command;

import app.Managers.ConfigurationManager;
import app.Managers.EnumManager;
import app.entities.Conference;
import app.entities.User;
import app.persistences.factory.MySqlDaoFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SpeakerCabinetCommand implements ICommand{

    private static final Logger logger = Logger.getLogger(SpeakerCabinetCommand.class);

    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = null;

        EnumManager speakerAction = EnumManager.valueOf(
                request.getSession().getAttribute("action").
                        toString().toUpperCase());

        ConfigurationManager confManager = ConfigurationManager.getInstance();

        User user = (User) request.getSession().getAttribute("currentUser");

        switch (speakerAction){
            case OFFER_A_SPEECH:
                request.getSession().removeAttribute("action");
                doOffer(request);
                page = confManager.getProperty(EnumManager.SPEAKER_CABINET.toString());
                request.getSession().setAttribute("speakerConfList",
                        MySqlDaoFactory.getConferenceDAO().getConfBySpeakerId(user.getCustomerId()));
                break;
            default:
                break;
        }

        return page;
    }
    private static void doOffer(HttpServletRequest request){


        String confName;
        String date;
        String beginsAt;
        String endsAt;
        String location;
        Boolean acceptedByModer = false;
        Boolean acceptedBySpeaker = false;
        int speakerId;

        int year;
        int month;
        int day;
        int begHour;
        int begMin;
        int endHour;
        int endMin;

        User user = (User) request.getSession().getAttribute("currentUser");
        speakerId = user.getCustomerId();

        confName = request.getParameter("confName");
        location = request.getParameter("location");

//        try {
//            confName = URLDecoder.decode(confName, "UTF-8");
//            location = URLDecoder.decode(location, "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }

        try {
            year = Integer.parseInt(request.getParameter("year"));
            month = Integer.parseInt(request.getParameter("month"));
            day = Integer.parseInt(request.getParameter("day"));
            begHour = Integer.parseInt(request.getParameter("begHour"));
            begMin = Integer.parseInt(request.getParameter("begMin"));
            endHour = Integer.parseInt(request.getParameter("endHour"));
            endMin = Integer.parseInt(request.getParameter("endMin"));

            date = year + "-" + toFormat(month) + "-" + toFormat(day);
            beginsAt = toFormat(begHour) + ":" + toFormat(begMin) + ":00";
            endsAt = toFormat(endHour) + ":" + toFormat(endMin) + ":00";

            logger.info("before IF");
            if(check(month,12) && check(day,31) &&
                    check(begHour,24) && check(begMin,60) &&
                    check(endHour, 24) && check(endMin,60)){

                Conference conference = new Conference(speakerId, confName,
                        date, beginsAt, endsAt, location, acceptedByModer,
                        acceptedBySpeaker);

                MySqlDaoFactory.getConferenceDAO().
                        addConference(conference,false,true);

                request.setAttribute("isInputError",false);
                request.setAttribute("isAdded",true);
            }

            request.setAttribute("isInputError",true);
            request.setAttribute("isAdded",false);

        }catch (NumberFormatException ex){
            request.setAttribute("isInputError",true);
            request.setAttribute("isAdded",false);
        }

    }
    private static boolean check(int number, int maxValue){
        if(number < 0){
            return false;
        }
        if (number > maxValue){
            return false;
        }
        return true;
    }
    private static String toFormat(int number){
        if(number < 10){
            return "0"+number;
        }
        return String.valueOf(number);
    }
}