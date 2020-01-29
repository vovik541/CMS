package app.commands.command;

import app.Managers.ConfigurationManager;
import app.Managers.EnumManager;
import app.entities.User;
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

        User currentUser = (User)request.getSession().getAttribute("currentUser");
        String page;


        //if user logged -> go to his cabinet
        if(currentUser != null){
            logger.info("USER EXISTS in SignInCommand");
            page = SignInCommand.getPageByRole(currentUser.getRole());
            return page;
        }

        logger.info("ConfigurationManager in EmptyCommand");
        return ConfigurationManager.getInstance()
                .getProperty(EnumManager.SIGN_IN.toString());
    }
}
