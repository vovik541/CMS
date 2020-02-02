package app.commands.command;

import app.Managers.ConfigurationManager;
import app.Managers.ResourceManager;
import app.entities.Conference;
import app.entities.ConferenceInfo;
import app.entities.Role;
import app.entities.User;
import app.persistences.factory.MySqlDaoFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class SpeakerCabinetCommand implements ICommand{

    private static final Logger logger = Logger.getLogger(SpeakerCabinetCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        logger.info("in SpeakerCabinetCommand");

        ConfigurationManager confManager = ConfigurationManager.getInstance();
        String page = confManager.getProperty(ResourceManager.SPEAKER_CABINET.toString());

        String action = request.getParameter(ResourceManager.ACTION.toString());
        System.out.println("ACTION in SpeakerCabinetCommand"+action);

        if(action != null){

            logger.info("Action != null");

            ResourceManager speakerAction = ResourceManager.valueOf(action.toUpperCase());

            switch (speakerAction){
                case OFFER_A_SPEECH:
                    doOffer(request);
                    logger.info("OFFER_A_SPEECH");
                    break;
                case DELETE_CONFERENCE:
                    logger.info("DELETE_A_CONFERENCE");
                    doDelete(request);
                    break;
                case CONFIRM_CONFERENCE:
                    logger.info("CONFIRM_A_CONFERENCE");
                    doConfirm(request);
                    break;
                case REFUSE_CONFERENCE:
                    logger.info("REFUSE_THE_CONFERENCE");
                    doRefuse(request);
                    break;
                case GET_MORE_INFO:
                    setConfExtraInfo(request);
                    break;
                default:
                    logger.info("DEFAULT IN SWITCH");
                    break;
            }

            User user = (User) request.getSession().getAttribute("currentUser");
            request.getSession().setAttribute("speakerConfList",
                    MySqlDaoFactory.getConferenceDAO().getConfBySpeakerId(user.getCustomerId()));

            request.getSession().setAttribute("speakerRate", getSpeakerRate(user.getCustomerId()));
            System.out.println(getSpeakerRate(user.getCustomerId()));
        }

        return page;
    }

    public static float getSpeakerRate(int speakerId){
        float speakerRate = 0;

        List<Integer> rates = MySqlDaoFactory.getConferenceDAO().getSpeakerRates(speakerId);

        for(int rate: rates){
            speakerRate += rate;
        }

        speakerRate = speakerRate/rates.size();

        return speakerRate;
    }

    private static void doOffer(HttpServletRequest request){

        String confName;
        String date;
        String beginsAtTime;
        String endsAtTime;
        String location;
        Boolean acceptedByModer = false;
        Boolean acceptedBySpeaker = false;
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
                Conference conference = new Conference(speakerId, confName,
                        date, beginsAtTime, endsAtTime, location, acceptedByModer,
                        acceptedBySpeaker);

                conference.setAcceptedBySpeaker(true);
                conference.setAcceptedByModer(false);

                MySqlDaoFactory.getConferenceDAO().
                        addConference(conference);

                request.setAttribute("isInputError",false);
                request.setAttribute("isAdded",true);

            }else {
                request.setAttribute("isInputError",true);
                request.setAttribute("isAdded",false);
            }
    }

    private static String formatTime(String timeStr){

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

    private static void setConfExtraInfo(HttpServletRequest request){
        int conferenceId = Integer.parseInt(request.getParameter("id"));
        ConferenceInfo conferenceInfo = MySqlDaoFactory.getConferenceDAO().
                getMoreConfInfo(conferenceId);
        request.getSession().setAttribute("conferenceInfo", conferenceInfo);
    }

    private static void doDelete(HttpServletRequest request){
        int conferenceId = Integer.parseInt(request.getParameter("id"));
        MySqlDaoFactory.getConferenceDAO().deleteRegisteredInConf(conferenceId);
        MySqlDaoFactory.getConferenceDAO().deleteById(conferenceId);
    }

    private static void doConfirm(HttpServletRequest request){
        int conferenceId = Integer.parseInt(request.getParameter("id"));
        MySqlDaoFactory.getConferenceDAO().setAgreement(Role.SPEAKER, conferenceId, true);
    }

    private static void doRefuse(HttpServletRequest request){
        int conferenceId = Integer.parseInt(request.getParameter("id"));
        MySqlDaoFactory.getConferenceDAO().setAgreement(Role.SPEAKER, conferenceId, false);
    }

    private static boolean check(int number, int maxValue){
        if(number < 1){
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