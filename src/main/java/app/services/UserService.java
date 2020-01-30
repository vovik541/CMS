package app.services;

import app.Managers.ConfigurationManager;
import app.Managers.EnumManager;
import app.entities.User;
import app.persistences.factory.MySqlDaoFactory;

import javax.servlet.http.HttpServletRequest;

public class UserService {

    private UserService() {
    }

    private static UserService instance = null;

    public static synchronized UserService getInstance() {
        if (instance == null)
            instance = new UserService();
        return instance;
    }

    public static String getPageByRole(User user, HttpServletRequest request){

        ConfigurationManager confManager = ConfigurationManager.getInstance();
        String page;

        switch (user.getRole()){
            case USER:
                page = confManager.getProperty(EnumManager.USER_CABINET.toString());
                break;
            case SPEAKER:
                page = confManager.getProperty(EnumManager.SPEAKER_CABINET.toString());
                request.getSession().setAttribute("speakerConfList",
                        MySqlDaoFactory.getConferenceDAO().getConfBySpeakerId(user.getCustomerId()));
                break;
            case MODER:
                page = confManager.getProperty(EnumManager.MODER_CABINET.toString());
                break;
            case ADMIN:
                page = confManager.getProperty(EnumManager.ADMIN_CABINET.toString());
                break;
            default: page = confManager.getProperty(EnumManager.INDEX.toString());
                break;
        }

        return page;
    }
}
