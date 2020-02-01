package app.Managers;

public enum EnumManager {

    //_______________Name of file with paths

    BUNDLE_NAME("configuration"),

    //_______________CONSTANTS

    LOGIN("login"),
    PASSWORD("password"),
    EMAIL("email"),
    ACTION("action"),
    COMMAND("command"),
    POST("POST"),
    GET("GET"),
    USERNAME_DB("root"),
    PASSWORD_DB("root"),
    URL_DB("jdbc:mysql://localhost:3306/cms_db?useSSL=false&serverTimezone=UTC"),

    //________________COMMANDS,

    INDEX("INDEX"),
    SIGN_IN("SIGN_IN"),
    SIGN_UP("SIGN_UP"),
    SIGN_OUT("SIGN_OUT"),
    USER_CABINET("USER_CABINET"),
    SPEAKER_CABINET("SPEAKER_CABINET"),
    MODER_CABINET("MODER_CABINET"),
    ADMIN_CABINET("ADMIN_CABINET"),

    //________________USER_ACTIONS

    REGISTER_IN_CONFERENCE("REGISTER_IN_CONFERENCE"),

    //________________SPEAKER_ACTIONS

    OFFER_A_SPEECH("OFFER_A_SPEECH"),
    REFUSE_CONFERENCE("REFUSE_CONFERENCE"),
    CONFIRM_CONFERENCE("CONFIRM_CONFERENCE"),
    DELETE_CONFERENCE("DELETE_CONFERENCE"),

    LOGIN_ERROR("MESSAGE.LOGIN_ERROR");

    private String value;

    EnumManager(String v) {
        value = v;
    }

    @Override
    public String toString() {
        return value;
    }
}
