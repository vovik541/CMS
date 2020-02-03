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

import static app.logic.ModerCabinetLogic.*;

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

}
