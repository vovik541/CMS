package app.logic;

public class SignUpLogic {
    public static boolean isInputAlright(String firstName, String lastName, String login,
                                         String password, String email){

        if(firstName.isEmpty() || lastName.isEmpty() ||
                login.isEmpty() || password.isEmpty() || email.isEmpty()){
            return false;
        }
        return true;
    }
}
