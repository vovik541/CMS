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
        EnumManager speakerAction = EnumManager.
                valueOf(request.getParameter("action").toUpperCase());

        ConfigurationManager confManager = ConfigurationManager.getInstance();

        switch (speakerAction){
            case OFFER_A_SPEECH:
                doOffer(request);
                page = confManager.getProperty(EnumManager.SPEAKER_CABINET.toString());
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

        Conference conference = new Conference(speakerId, confName,
                date, beginsAt, endsAt, location, acceptedByModer,
                acceptedBySpeaker);

        MySqlDaoFactory.getConferenceDAO().
                addConference(conference,false,true);

    }
    private static String toFormat(int number){
        if(number < 10){
            return "0"+number;
        }
        return String.valueOf(number);
    }
}