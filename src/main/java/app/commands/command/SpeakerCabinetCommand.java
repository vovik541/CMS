package app.commands.command;

import app.Managers.ConfigurationManager;
import app.Managers.ResourceManager;
import app.entities.User;
import app.logic.SpeakerCabinetLogic;
import app.persistences.factory.MySqlDaoFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static app.logic.SpeakerCabinetLogic.*;

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
            SpeakerCabinetLogic logic = SpeakerCabinetLogic.getInstance();

            switch (speakerAction){
                case OFFER_A_SPEECH:
                    logic.doOffer(request);
                    logger.info("OFFER_A_SPEECH");
                    break;
                case DELETE_CONFERENCE:
                    logger.info("DELETE_A_CONFERENCE");
                    logic.doDelete(request);
                    break;
                case CONFIRM_CONFERENCE:
                    logger.info("CONFIRM_A_CONFERENCE");
                    logic.doConfirm(request);
                    break;
                case REFUSE_CONFERENCE:
                    logger.info("REFUSE_THE_CONFERENCE");
                    logic.doRefuse(request);
                    break;
                case GET_MORE_INFO:
                    logic.setConfExtraInfo(request);
                    break;
                default:
                    logger.info("DEFAULT IN SWITCH");
                    break;
            }

            User user = (User) request.getSession().getAttribute("currentUser");
            request.getSession().setAttribute("speakerConfList",
                    MySqlDaoFactory.getConferenceDAO().getConfBySpeakerId(user.getCustomerId()));

            request.getSession().setAttribute("speakerRate", logic.getSpeakerRate(user.getCustomerId()));
        }

        return page;
    }
}