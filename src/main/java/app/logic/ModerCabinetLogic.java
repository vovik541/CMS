package app.logic;

import app.entities.Conference;
import app.entities.User;
import app.persistences.dao.UserDAO;
import app.persistences.factory.MySqlDaoFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static app.logic.SpeakerCabinetLogic.formatTime;

public class ModerCabinetLogic {

    private static final Logger logger = Logger.getLogger(ModerCabinetLogic.class);

    //not ready! (works without checking input)

    public static void changeSpeaker(HttpServletRequest request){
        int conferenceId = Integer.parseInt(request.getParameter("conferenceId"));
        int speakerId = Integer.parseInt(request.getParameter("speakerId"));

        MySqlDaoFactory.getConferenceDAO().changeSpeaker(speakerId, conferenceId);
    }

    //not ready! (works without checking input)

    public static void changeTime(HttpServletRequest request){
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

    public static void changeConferenceName(HttpServletRequest request){
        String conferenceName = request.getParameter("conferenceName");
        int conferenceId = Integer.parseInt(request.getParameter("conferenceId"));

        MySqlDaoFactory.getConferenceDAO().changeConferenceName(conferenceName, conferenceId);
    }

    //not ready! (works without checking input)

    public static void changeLocation(HttpServletRequest request){
        String location = request.getParameter("location");
        int conferenceId = Integer.parseInt(request.getParameter("conferenceId"));

        MySqlDaoFactory.getConferenceDAO().changeLocation(location, conferenceId);
    }

    public static void giveSpeech(HttpServletRequest request){

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

    public static void giveUserRole(HttpServletRequest request, int role){
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
