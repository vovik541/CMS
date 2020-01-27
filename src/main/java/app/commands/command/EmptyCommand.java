package app.commands.command;

import app.Managers.ConfigurationManager;
import app.Managers.EnumManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EmptyCommand implements ICommand{

    private static final Logger logger = Logger.getLogger(EmptyCommand.class);

    public EmptyCommand(){
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        logger.info("ConfigurationManager in EmptyCommand");

        return ConfigurationManager.getInstance()
                .getProperty(EnumManager.SIGN_IN.toString());
    }
}
