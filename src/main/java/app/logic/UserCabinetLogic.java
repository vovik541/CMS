package app.logic;

import app.entities.Conference;
import app.entities.User;
import app.persistences.dao.ConferenceDAO;
import app.persistences.factory.MySqlDaoFactory;
import app.services.EmptyCommandService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class UserCabinetLogic {

    private static UserCabinetLogic instance= null;

    public static UserCabinetLogic getInstance() {
        if (instance == null)
            instance = new UserCabinetLogic();
        return instance;
    }

    private UserCabinetLogic(){

    }

    public void doGiveRate(HttpServletRequest request, int userId){
        int conferenceId = Integer.parseInt(request.getParameter("id"));
        int rate = Integer.parseInt(request.getParameter("rate"));
        MySqlDaoFactory.getConferenceDAO().setRate(rate, conferenceId, userId);
    }

    public List<Conference> getConfToRegIn(int userId){

        //here we got all conferences that didn't pass/end until now

        List<Conference> conferences = EmptyCommandService.getInstance().getConfForView();

        //id of conferences that user registered in

        ArrayList<Integer> confId = MySqlDaoFactory.getConferenceDAO().getRegisteredConfId(userId);

        //writing "registered" in conferences

        for(Conference conference: conferences){
            for (Integer conferenceId:confId){
                if(conference.getConferenceId()==conferenceId){
                    conference.setRegistered(true);
                }
            }
        }
        return conferences;
    }
    public List<Conference> getConfUserWasPresentIn(int userId){
        EmptyCommandService service = EmptyCommandService.getInstance();
        List<Conference> conferences = MySqlDaoFactory.getConferenceDAO().
                getConfUserWasPresentIn(userId, service.getCurrentDay(), service.getCurrentTime());
        return conferences;
    }
}
