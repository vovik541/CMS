package app.commands.command;

import app.Managers.ConfigurationManager;
import app.Managers.EnumManager;
import app.entities.Conference;
import app.entities.Role;
import app.entities.User;
import app.persistences.factory.MySqlDaoFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SpeakerCabinetCommand implements ICommand{

    private static final Logger logger = Logger.getLogger(SpeakerCabinetCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        logger.info("in SpeakerCabinetCommand");

        ConfigurationManager confManager = ConfigurationManager.getInstance();
        String page = confManager.getProperty(EnumManager.SPEAKER_CABINET.toString());

        String action = request.getParameter(EnumManager.ACTION.toString());
        System.out.println("ACTION in SpeakerCabinetCommand"+action);

        if(action != null){

            logger.info("Action != null");

            EnumManager speakerAction = EnumManager.valueOf(action.toUpperCase());

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
                default:
                    logger.info("DEFAULT IN SWITCH");
                    break;
            }

            User user = (User) request.getSession().getAttribute("currentUser");
            request.getSession().setAttribute("speakerConfList",
                    MySqlDaoFactory.getConferenceDAO().getConfBySpeakerId(user.getCustomerId()));
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

    private static void doDelete(HttpServletRequest request){
//        logger.info("doDelete");
        int conferenceId = Integer.parseInt(request.getParameter("id"));
//        logger.info("id = :"+conferenceId);
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