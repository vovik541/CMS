package app.logic;

import app.entities.Conference;
import app.entities.ConferenceInfo;
import app.entities.Role;
import app.entities.User;
import app.persistences.factory.MySqlDaoFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class SpeakerCabinetLogic {

    private static final Logger logger = Logger.getLogger(SpeakerCabinetLogic.class);

    public static float getSpeakerRate(int speakerId){
        float speakerRate = 0;

        List<Integer> rates = MySqlDaoFactory.getConferenceDAO().getSpeakerRates(speakerId);

        for(int rate: rates){
            speakerRate += rate;
        }

        speakerRate = speakerRate/rates.size();

        return speakerRate;
    }

    public static void doOffer(HttpServletRequest request){

        String confName;
        String date;
        String beginsAtTime;
        String endsAtTime;
        String location;
        Boolean acceptedByModer = false;
        Boolean acceptedBySpeaker = true;
        int speakerId;

        User user = (User) request.getSession().getAttribute("currentUser");
        speakerId = user.getCustomerId();

        confName = request.getParameter("confName");
        location = request.getParameter("location");

        date = request.getParameter("date") + " 00:00:00";
        beginsAtTime = request.getParameter("beginsAtTime");
        endsAtTime = request.getParameter("endsAtTime");

        beginsAtTime = formatTime(beginsAtTime);
        endsAtTime = formatTime(endsAtTime);

        if(date != null && beginsAtTime != null && endsAtTime != null){

            Conference conference = new Conference.Builder(speakerId, confName, location,
                    date, beginsAtTime, endsAtTime)
                    .setAcceptedByModer(acceptedByModer)
                    .setAcceptedBySpeaker(acceptedBySpeaker)
                    .build();

            MySqlDaoFactory.getConferenceDAO().
                    addConference(conference);

            request.setAttribute("isInputError",false);
            request.setAttribute("isAdded",true);

        }else {
            request.setAttribute("isInputError",true);
            request.setAttribute("isAdded",false);
        }
    }

    public static String formatTime(String timeStr){

        String formatted = timeStr.substring(2,5)+":00";
        int hours = Integer.parseInt(timeStr.substring(0,2));
        hours++;
        if(hours > 24){
            return "01"+formatted;
        }else if(hours < 10){
            return "0" + hours + formatted;
        }else {
            return hours + formatted;
        }
    }

    public static void setConfExtraInfo(HttpServletRequest request){
        int conferenceId = Integer.parseInt(request.getParameter("id"));
        ConferenceInfo conferenceInfo = MySqlDaoFactory.getConferenceDAO().
                getMoreConfInfo(conferenceId);
        request.getSession().setAttribute("conferenceInfo", conferenceInfo);
    }

    public static void doDelete(HttpServletRequest request){
        int conferenceId = Integer.parseInt(request.getParameter("id"));
        MySqlDaoFactory.getConferenceDAO().deleteRegisteredInConf(conferenceId);
        MySqlDaoFactory.getConferenceDAO().deleteById(conferenceId);
    }

    public static void doConfirm(HttpServletRequest request){
        int conferenceId = Integer.parseInt(request.getParameter("id"));
        MySqlDaoFactory.getConferenceDAO().setAgreement(Role.SPEAKER, conferenceId, true);
    }

    public static void doRefuse(HttpServletRequest request){
        int conferenceId = Integer.parseInt(request.getParameter("id"));
        MySqlDaoFactory.getConferenceDAO().setAgreement(Role.SPEAKER, conferenceId, false);
    }
}
