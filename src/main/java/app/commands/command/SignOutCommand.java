package app.commands.command;

import app.Managers.ConfigurationManager;
import app.Managers.ResourceManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignOutCommand implements ICommand{

    private static final Logger logger = Logger.getLogger(SignOutCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        logger.info("in SignOutCommand");

        String page = ConfigurationManager.getInstance()
                .getProperty(ResourceManager.SIGN_OUT.toString()) + "?language="+request.getSession().getAttribute("language");

        request.getSession().invalidate();

        return page;
    }
}
