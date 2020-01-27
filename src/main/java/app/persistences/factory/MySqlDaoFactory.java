package app.persistences.factory;

import app.persistences.dao.SignInDAO;
import app.persistences.dao.SignUpDAO;

public class MySqlDaoFactory {
    public static SignInDAO getSignInDAO(){
        return new SignInDAO();
    }
    public static SignUpDAO getSignUpDAO(){
        return new SignUpDAO();
    }
}
