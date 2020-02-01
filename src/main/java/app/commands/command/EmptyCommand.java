package app.commands.command;

import app.Managers.ConfigurationManager;
import app.Managers.ResourceManager;
import app.entities.User;
import app.services.UserService;
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

        logger.info("in EmptyCommand");

        User currentUser = (User)request.getSession().getAttribute("currentUser");
        String page;

        //if user logged -> go to his cabinet
        if(currentUser != null){
            logger.info("USER EXISTS in EmptyCommand");
            page = UserService.getInstance().getPageByRole(currentUser,request);
            return page;
        }

        if(request.getParameter("EmptyCommandSignUp") != null){
            return ConfigurationManager.getInstance()
                    .getProperty(ResourceManager.SIGN_UP.toString());
        }

        logger.info("default page = sign_in");
        page = ConfigurationManager.getInstance()
                .getProperty(ResourceManager.SIGN_IN.toString());

        return page;
    }
}
