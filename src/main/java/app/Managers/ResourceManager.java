package app.Managers;

public enum ResourceManager {

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
    GIVE_RATE("GIVE_RATE"),

    //________________SPEAKER_ACTIONS

    OFFER_A_SPEECH("OFFER_A_SPEECH"),
    REFUSE_CONFERENCE("REFUSE_CONFERENCE"),
    CONFIRM_CONFERENCE("CONFIRM_CONFERENCE"),
    DELETE_CONFERENCE("DELETE_CONFERENCE"),
    GET_MORE_INFO("GET_MORE_INFO"),

    //________________MODER_ACTIONS

    SET_RECORDS_PER_PAGE("SET_RECORDS_PER_PAGE"),
    GIVE_SPEAKER_ROLE("GIVE_SPEAKER_ROLE"),
    GIVE_USER_ROLE("GIVE_USER_POLE"),
    GIVE_SPEECH("GIVE_SPEECH"),
    MODER_AGREED("MODER_AGREED"),
    MODER_DISAGREED("MODER_DISAGREED"),
    CHANGE_SPEAKER("CHANGE_SPEAKER"),
    CHANGE_TIME("CHANGE_TIME"),
    CHANGE_CONFERENCE_NAME("CHANGE_CONFERENCE_NAME"),
    CHANGE_LOCATION("CHANGE_LOCATION"),


    LOGIN_ERROR("MESSAGE.LOGIN_ERROR");

    private String value;

    ResourceManager(String v) {
        value = v;
    }

    @Override
    public String toString() {
        return value;
    }
}
