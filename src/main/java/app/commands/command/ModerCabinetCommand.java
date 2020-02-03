package app.commands.command;

import app.Managers.ConfigurationManager;
import app.Managers.ResourceManager;
import app.entities.Conference;
import app.entities.Role;
import app.entities.User;
import app.persistences.dao.UserDAO;
import app.persistences.factory.MySqlDaoFactory;
import app.services.UserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ModerCabinetCommand implements ICommand{

    private static final Logger logger = Logger.getLogger(ModerCabinetCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    ConfigurationManager confManager = ConfigurationManager.getInstance();
    String page = confManager.getProperty(ResourceManager.MODER_CABINET.toString());

        int conferenceId;

        String action = request.getParameter(ResourceManager.ACTION.toString());
        logger.info("ACTION " + action);
        ResourceManager speakerAction = ResourceManager.valueOf(action.toUpperCase());

        switch (speakerAction){
            case SET_RECORDS_PER_PAGE:
                doUserPagination(request);
                break;
            case GIVE_USER_ROLE:
                giveUserRole(request,1);
                doUserPagination(request);
                request.getSession().setAttribute("speakersForOption", MySqlDaoFactory.getUserDAO().getSpeakersForOption());
                break;
            case GIVE_SPEAKER_ROLE:
                giveUserRole(request,2);
                doUserPagination(request);
                request.getSession().setAttribute("speakersForOption", MySqlDaoFactory.getUserDAO().getSpeakersForOption());
                break;
            case GIVE_SPEECH:
                giveSpeech(request);
                break;
            case MODER_AGREED:
                conferenceId = Integer.parseInt(request.getParameter("conferenceId"));
                MySqlDaoFactory.getConferenceDAO().setAgreement(Role.MODER,conferenceId,true);
                break;
            case MODER_DISAGREED:
                conferenceId = Integer.parseInt(request.getParameter("conferenceId"));
                MySqlDaoFactory.getConferenceDAO().setAgreement(Role.MODER,conferenceId,false);
                break;
            case CHANGE_CONFERENCE_NAME:
                changeConferenceName(request);
                break;
            case CHANGE_TIME:
                changeTime(request);
                break;
            case CHANGE_LOCATION:
                changeLocation(request);
                break;
            case CHANGE_SPEAKER:
                changeSpeaker(request);
                break;
            default:
                logger.info("DEFAULT IN SWITCH");
                break;
        }
        request.setAttribute("pastConferences",
                MySqlDaoFactory.getConferenceDAO().getConfBeforeDateTime(UserService.getCurrentDay(),
                        UserService.getCurrentTime()));
        request.setAttribute("currentConferences",
                MySqlDaoFactory.getConferenceDAO().getCurrentConferences(UserService.getCurrentDay(),
                        UserService.getCurrentTime()));

        doUserPagination(request);

    return page;
    }

    //not ready! (works without checking input)

    private static void changeSpeaker(HttpServletRequest request){
        int conferenceId = Integer.parseInt(request.getParameter("conferenceId"));
        int speakerId = Integer.parseInt(request.getParameter("speakerId"));

        MySqlDaoFactory.getConferenceDAO().changeSpeaker(speakerId, conferenceId);
    }

    //not ready! (works without checking input)

    private static void changeTime(HttpServletRequest request){
        String date = request.getParameter("date") + " 00:00:00";
        String beginsAtTime = request.getParameter("beginsAtTime");
        String endsAtTime = request.getParameter("endsAtTime");

        if(!date.equals("00:00:00") && !beginsAtTime.isEmpty() && !endsAtTime.isEmpty()) {

            int conferenceId = Integer.parseInt(request.getParameter("conferenceId"));
            beginsAtTime = beginsAtTime + ":00";
            endsAtTime = endsAtTime + ":00";

            MySqlDaoFactory.getConferenceDAO().changeDateTime(conferenceId,
                    date, beginsAtTime, endsAtTime);
        }
    }

    //not ready! (works without checking input)

    private static void changeConferenceName(HttpServletRequest request){
        String conferenceName = request.getParameter("conferenceName");
        int conferenceId = Integer.parseInt(request.getParameter("conferenceId"));

        MySqlDaoFactory.getConferenceDAO().changeConferenceName(conferenceName, conferenceId);
    }

    //not ready! (works without checking input)

    private static void changeLocation(HttpServletRequest request){
        String location = request.getParameter("location");
        int conferenceId = Integer.parseInt(request.getParameter("conferenceId"));

        MySqlDaoFactory.getConferenceDAO().changeLocation(location, conferenceId);
    }

    private static void giveSpeech(HttpServletRequest request){

        logger.info("IN");

        String confName;
        String location;

        String date = request.getParameter("date") + " 00:00:00";
        String beginsAtTime = request.getParameter("beginsAtTime");
        String endsAtTime = request.getParameter("endsAtTime");

        Boolean acceptedByModer = true;
        Boolean acceptedBySpeaker = Boolean.valueOf(request.getParameter("acceptedBySpeaker"));

        String speakerIdOpt = request.getParameter("speakerIdOpt");

        logger.info(date + " !!!!"+ beginsAtTime +" "+ endsAtTime);

        confName = request.getParameter("confName");
        location = request.getParameter("location");

        if(!date.equals("00:00:00") && !beginsAtTime.isEmpty() && !endsAtTime.isEmpty()
        && confName != null && location != null){

            logger.info("WORKS AFTER IF");

            int speakerId = Integer.parseInt(speakerIdOpt);

            beginsAtTime = SpeakerCabinetCommand.formatTime(beginsAtTime);
            endsAtTime = SpeakerCabinetCommand.formatTime(endsAtTime);

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


    private static void giveUserRole(HttpServletRequest request, int role){
        int userId = Integer.parseInt(request.getParameter("userId"));
        MySqlDaoFactory.getUserDAO().giveRole(role,userId);
    }

    public static void doUserPagination(HttpServletRequest request){

        UserDAO userDAO = MySqlDaoFactory.getUserDAO();

        String recPerPageStr = request.getParameter("recordsPerPage");
        String curPageStr = request.getParameter("currentPage");

        int nOfUsers = userDAO.getNumOfCustomers();
        int currentPage = 1;
        int recordsPerPage = 10;
        int nOfPages;

        logger.info("");

        if(recPerPageStr != null){
            logger.info("!!"+ recPerPageStr);
            recordsPerPage = Integer.parseInt(recPerPageStr);
        }

        if(curPageStr != null){
            logger.info(curPageStr.length());
            currentPage = Integer.parseInt(curPageStr);
        }

        nOfPages = nOfUsers / recordsPerPage;

        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }

        List<User> users = userDAO.getUsersForPag(currentPage, recordsPerPage);

        logger.info(users.size()+" USER SIZE");

        request.setAttribute("usersForModerView", users);

        request.setAttribute("nOfPages", nOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);
    }
}
