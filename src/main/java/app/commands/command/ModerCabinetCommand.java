package app.commands.command;

import app.Managers.ConfigurationManager;
import app.Managers.ResourceManager;
import app.entities.User;
import app.persistences.dao.ConferenceDAO;
import app.persistences.dao.UserDAO;
import app.persistences.factory.MySqlDaoFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ModerCabinetCommand implements ICommand{

    private static final Logger logger = Logger.getLogger(ModerCabinetCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    ConfigurationManager confManager = ConfigurationManager.getInstance();
    String page = confManager.getProperty(ResourceManager.MODER_CABINET.toString());;

    User user = (User) request.getSession().getAttribute("currentUser");

        String action = request.getParameter(ResourceManager.ACTION.toString());
        logger.info("ACTION " + action);
        ResourceManager speakerAction = ResourceManager.valueOf(action.toUpperCase());

        switch (speakerAction){
            case SET_RECORDS_PER_PAGE:
                doUserPagination(request);
                break;
            default:
                logger.info("DEFAULT IN SWITCH");
                break;
        }


        doUserPagination(request);

    return page;
    }
    public static void doUserPagination(HttpServletRequest request){

        UserDAO userDAO = MySqlDaoFactory.getUserDAO();

        String recPerPageStr = request.getParameter("recordsPerPage");
        String curPageStr = request.getParameter("currentPage");
        logger.info("STR @@@ "+ curPageStr);

        int nOfUsers = userDAO.getNumOfCustomers();
        int currentPage;
        int recordsPerPage;
        int nOfPages;

        if(recPerPageStr != null){
            recordsPerPage = Integer.parseInt(recPerPageStr);
        }else {
            recordsPerPage = 10;
        }

        if(curPageStr != null){
            currentPage = Integer.parseInt(curPageStr);
        }else {
            currentPage = 2;
        }

        logger.info(recPerPageStr +" recordsPerPage!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+currentPage);

        nOfPages = nOfUsers / recordsPerPage;

        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }

        request.setAttribute("nOfPages", nOfPages);

        List<User> users = userDAO.getUsersForPag(currentPage, recordsPerPage);

        logger.info(users.get(0).toString());

        request.getSession().setAttribute("usersForModerView", users);
    }
}
