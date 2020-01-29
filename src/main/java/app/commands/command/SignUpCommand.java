package app.commands.command;

import app.Managers.ConfigurationManager;
import app.Managers.EnumManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpCommand implements ICommand {


    private static final Logger logger = Logger.getLogger(SignUpCommand.class);

    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = null;

        EnumManager speakerAction = EnumManager.valueOf(
                request.getSession().getAttribute("action").
                        toString().toUpperCase());

        switch (speakerAction){
            case SIGN_UP:
                page = ConfigurationManager.getInstance()
                        .getProperty(EnumManager.SIGN_UP.toString());
                break;
            default:
                break;
        }

        return page;
    }
}