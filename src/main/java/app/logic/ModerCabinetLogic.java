package app.logic;

import app.CommonsEmail.MailCommons;
import app.entities.Conference;
import app.entities.User;
import app.persistences.dao.UserDAO;
import app.persistences.factory.MySqlDaoFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ModerCabinetLogic {

    private static final Logger logger = Logger.getLogger(ModerCabinetLogic.class);

    private static ModerCabinetLogic instance= null;

    public static ModerCabinetLogic getInstance() {
        if (instance == null)
            instance = new ModerCabinetLogic();
        return instance;
    }

    private ModerCabinetLogic(){

    }

    //not ready! (works without checking input)

    public void changeSpeaker(HttpServletRequest request){
        int conferenceId = Integer.parseInt(request.getParameter("conferenceId"));
        int speakerId = Integer.parseInt(request.getParameter("speakerId"));

        MySqlDaoFactory.getConferenceDAO().changeSpeaker(speakerId, conferenceId);
    }

    //not ready! (works without checking input)

    public void changeTime(HttpServletRequest request){
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

    public void changeConferenceName(HttpServletRequest request){
        String conferenceName = request.getParameter("conferenceName");
        int conferenceId = Integer.parseInt(request.getParameter("conferenceId"));

        MySqlDaoFactory.getConferenceDAO().changeConferenceName(conferenceName, conferenceId);
    }

    //not ready! (works without checking input)

    public void changeLocation(HttpServletRequest request){
        String location = request.getParameter("location");
        int conferenceId = Integer.parseInt(request.getParameter("conferenceId"));

        MySqlDaoFactory.getConferenceDAO().changeLocation(location, conferenceId);
    }

    public void giveSpeech(HttpServletRequest request){

        String confName;
        String location;

        String date = request.getParameter("date") + " 00:00:00";
        String beginsAtTime = request.getParameter("beginsAtTime");
        String endsAtTime = request.getParameter("endsAtTime");

        Boolean acceptedByModer = true;
        Boolean acceptedBySpeaker = Boolean.valueOf(request.getParameter("acceptedBySpeaker"));

        String speakerIdOpt = request.getParameter("speakerIdOpt");

        confName = request.getParameter("confName");
        location = request.getParameter("location");

        if(!date.equals("00:00:00") && !beginsAtTime.isEmpty() && !endsAtTime.isEmpty()
                && confName != null && location != null){

            int speakerId = Integer.parseInt(speakerIdOpt);

            beginsAtTime = SpeakerCabinetLogic.getInstance().formatTime(beginsAtTime);
            endsAtTime = SpeakerCabinetLogic.getInstance().formatTime(endsAtTime);

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

    public void sentLetter(HttpServletRequest request){
        String subject = request.getParameter("subject");
        String message = request.getParameter("message");

        if(subject.isEmpty() || message.isEmpty()){
            request.setAttribute("letterError", true);
        }else {
            int conferenceId = Integer.parseInt(request.getParameter("conferenceId"));
            List<String> emails = MySqlDaoFactory.getConferenceDAO().getEmailsByConf(conferenceId);

            if(emails.size() > 0){
                MailCommons.sendMail(emails, subject, message);
            }

            request.setAttribute("letterError", false);
        }
    }

    public void giveUserRole(HttpServletRequest request, int role){
        int userId = Integer.parseInt(request.getParameter("userId"));
        MySqlDaoFactory.getUserDAO().giveRole(role,userId);
    }

    public void doUserPagination(HttpServletRequest request){

        UserDAO userDAO = MySqlDaoFactory.getUserDAO();

        String recPerPageStr = request.getParameter("recordsPerPage");
        String curPageStr = request.getParameter("currentPage");

        int nOfUsers = userDAO.getNumOfCustomers();
        int currentPage = 1;
        int recordsPerPage = 10;
        int nOfPages;

        if(recPerPageStr != null){
            recordsPerPage = Integer.parseInt(recPerPageStr);
        }

        if(curPageStr != null){
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
