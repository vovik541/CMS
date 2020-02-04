package app.commands.command;

import app.Managers.ConfigurationManager;
import app.Managers.ResourceManager;
import app.entities.Role;
import app.logic.ModerCabinetLogic;
import app.persistences.factory.MySqlDaoFactory;
import app.services.EmptyCommandService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

        ModerCabinetLogic moderCabinetLogic = ModerCabinetLogic.getInstance();

        switch (speakerAction){
            case SET_RECORDS_PER_PAGE:
                moderCabinetLogic.doUserPagination(request);
                break;
            case GIVE_USER_ROLE:
                moderCabinetLogic.giveUserRole(request,1);
                moderCabinetLogic.doUserPagination(request);
                request.getSession().setAttribute("speakersForOption", MySqlDaoFactory.getUserDAO().getSpeakersForOption());
                break;
            case GIVE_SPEAKER_ROLE:
                moderCabinetLogic.giveUserRole(request,2);
                moderCabinetLogic.doUserPagination(request);
                request.getSession().setAttribute("speakersForOption", MySqlDaoFactory.getUserDAO().getSpeakersForOption());
                break;
            case GIVE_SPEECH:
                moderCabinetLogic.giveSpeech(request);
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
                moderCabinetLogic.changeConferenceName(request);
                break;
            case CHANGE_TIME:
                moderCabinetLogic.changeTime(request);
                break;
            case CHANGE_LOCATION:
                moderCabinetLogic.changeLocation(request);
                break;
            case CHANGE_SPEAKER:
                moderCabinetLogic.changeSpeaker(request);
                break;
            default:
                logger.info("DEFAULT IN SWITCH");
                break;
        }
        request.setAttribute("pastConferences",
                MySqlDaoFactory.getConferenceDAO().getConfBeforeDateTime(EmptyCommandService.getCurrentDay(),
                        EmptyCommandService.getCurrentTime()));
        request.setAttribute("currentConferences",
                MySqlDaoFactory.getConferenceDAO().getCurrentConferences(EmptyCommandService.getCurrentDay(),
                        EmptyCommandService.getCurrentTime()));

        moderCabinetLogic.doUserPagination(request);

    return page;
    }

}
