package app.commands.command;

import app.Managers.ConfigurationManager;
import app.Managers.ResourceManager;
import app.entities.User;
import app.persistences.dao.ConferenceDAO;
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
    String page = confManager.getProperty(ResourceManager.ADMIN_CABINET.toString());;

    User user = (User) request.getSession().getAttribute("currentUser");

//    doUserPagination(request,user);

    return page;
    }
    private static void doUserPagination(HttpServletRequest request){

        ConferenceDAO conferenceDAO = MySqlDaoFactory.getConferenceDAO();

        int recordsPerPage = 10;
        int nOfUsers = conferenceDAO.getNumOfCustomers();
        int nOfPages = nOfUsers / recordsPerPage;

        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }

//        List<User> users = conferenceDAO.

    }
}
