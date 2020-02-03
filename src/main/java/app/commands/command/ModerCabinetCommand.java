package app.commands.command;

import app.Managers.ConfigurationManager;
import app.Managers.ResourceManager;
import app.entities.Conference;
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
    String page = confManager.getProperty(ResourceManager.MODER_CABINET.toString());;

    User user = (User) request.getSession().getAttribute("currentUser");

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
                break;
            case GIVE_SPEAKER_ROLE:
                giveUserRole(request,2);
                doUserPagination(request);
                break;
            case GIVE_SPEECH:
                giveSpeech(request);
                break;
            default:
                logger.info("DEFAULT IN SWITCH");
                break;
        }

        doUserPagination(request);

    return page;
    }

    private static void giveSpeech(HttpServletRequest request){

        String confName;
        String date = request.getParameter("date") + " 00:00:00";
        String beginsAtTime = request.getParameter("beginsAtTime");
        String endsAtTime = request.getParameter("endsAtTime");
        String location;
        Boolean acceptedByModer = true;
        Boolean acceptedBySpeaker = Boolean.valueOf(request.getParameter("acceptedBySpeaker"));

        int speakerId = 8;

        confName = request.getParameter("confName");
        location = request.getParameter("location");


        beginsAtTime = SpeakerCabinetCommand.formatTime(beginsAtTime);
        endsAtTime = SpeakerCabinetCommand.formatTime(endsAtTime);

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

        request.getSession().setAttribute("usersForModerView", users);

        request.setAttribute("nOfPages", nOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);
    }
}
