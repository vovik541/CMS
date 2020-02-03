package app.logic;

import app.persistences.factory.MySqlDaoFactory;

import javax.servlet.http.HttpServletRequest;

public class UserCabinetLogic {

    public static void doGiveRate(HttpServletRequest request, int userId){
        int conferenceId = Integer.parseInt(request.getParameter("id"));
        int rate = Integer.parseInt(request.getParameter("rate"));
        MySqlDaoFactory.getConferenceDAO().setRate(rate, conferenceId, userId);
    }
}
