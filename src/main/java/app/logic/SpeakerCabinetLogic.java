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

    private static SpeakerCabinetLogic instance= null;

    public static SpeakerCabinetLogic getInstance() {
        if (instance == null)
            instance = new SpeakerCabinetLogic();
        return instance;
    }

    private SpeakerCabinetLogic(){

    }

    public float getSpeakerRate(int speakerId){
        float speakerRate = 0;

        List<Integer> rates = MySqlDaoFactory.getConferenceDAO().getSpeakerRates(speakerId);

        for(int rate: rates){
            speakerRate += rate;
        }

        speakerRate = speakerRate/rates.size();

        return speakerRate;
    }

    public void doOffer(HttpServletRequest request){

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

        if(!date.equals("00:00:00") && !beginsAtTime.isEmpty() && !endsAtTime.isEmpty()
                && confName != null && location != null){

            beginsAtTime = formatTime(beginsAtTime);
            endsAtTime = formatTime(endsAtTime);

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

    public String formatTime(String timeStr){

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

    public void setConfExtraInfo(HttpServletRequest request){
        int conferenceId = Integer.parseInt(request.getParameter("id"));
        ConferenceInfo conferenceInfo = MySqlDaoFactory.getConferenceDAO().
                getMoreConfInfo(conferenceId);
        request.getSession().setAttribute("conferenceInfo", conferenceInfo);
    }

    public void doDelete(HttpServletRequest request){
        int conferenceId = Integer.parseInt(request.getParameter("id"));
        MySqlDaoFactory.getConferenceDAO().deleteRegisteredInConf(conferenceId);
        MySqlDaoFactory.getConferenceDAO().deleteById(conferenceId);
    }

    public void doConfirm(HttpServletRequest request){
        int conferenceId = Integer.parseInt(request.getParameter("id"));
        MySqlDaoFactory.getConferenceDAO().setAgreement(Role.SPEAKER, conferenceId, true);
    }

    public void doRefuse(HttpServletRequest request){
        int conferenceId = Integer.parseInt(request.getParameter("id"));
        MySqlDaoFactory.getConferenceDAO().setAgreement(Role.SPEAKER, conferenceId, false);
    }
}
