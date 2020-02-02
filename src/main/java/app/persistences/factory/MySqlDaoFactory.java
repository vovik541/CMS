package app.persistences.factory;

import app.persistences.dao.ConferenceDAO;
import app.persistences.dao.SignInDAO;
import app.persistences.dao.SignUpDAO;
import app.persistences.dao.UserDAO;

public class MySqlDaoFactory {
    public static SignInDAO getSignInDAO(){
        return new SignInDAO();
    }
    public static SignUpDAO getSignUpDAO(){
        return new SignUpDAO();
    }
    public static ConferenceDAO getConferenceDAO(){
        return new ConferenceDAO();
    }
    public static UserDAO getUserDAO(){
        return new UserDAO();
    }
}
