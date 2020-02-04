package app.commands.command;

import app.Managers.ConfigurationManager;
import app.Managers.ResourceManager;
import app.entities.User;
import app.logic.SignUpLogic;
import app.services.EmptyCommandService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpCommand implements ICommand {

    private static final Logger logger = Logger.getLogger(SignUpCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("in SignUpCommand");

        String method = request.getMethod();
        ConfigurationManager confManager = ConfigurationManager.getInstance();

        String page = confManager.getProperty(ResourceManager.SIGN_UP.toString());

        if (method.equalsIgnoreCase(ResourceManager.GET.toString())) {

            return page;

        }else if (method.equalsIgnoreCase(ResourceManager.POST.toString())){

            SignUpLogic.getInstance().doSignUp(request);

            User currentUser = (User) request.getSession().getAttribute("currentUser");

            if(currentUser != null){
                logger.info("user exists in SignUpCommand");
                page = EmptyCommandService.getInstance().getPageByRole(currentUser,request);
                request.setAttribute("command","user_cabinet");
                logger.info(request.getParameter("command")+" COMMAND REMOVED!");
                return page;
            }

            if(!Boolean.getBoolean(request.getParameter("userExistsErrorMessage")) &&
                    currentUser != null){
                page = EmptyCommandService.getInstance().getPageByRole(currentUser,request);
            }
            return page;
        }
        return page;
    }
}