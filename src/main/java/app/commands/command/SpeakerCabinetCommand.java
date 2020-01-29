package app.commands.command;

import app.Managers.EnumManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SpeakerCabinetCommand implements ICommand{

    private static final Logger logger = Logger.getLogger(SpeakerCabinetCommand.class);

    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = null;
        EnumManager speakerAction = EnumManager.
                valueOf(request.getParameter("action").toUpperCase());

        switch (speakerAction){
            case OFFER_A_SPEECH:
                doOffer(request);
                break;
            default:
                break;
        }

        return page;
    }
    private static void doOffer(HttpServletRequest request){

    }
}