package app.Managers;

public enum EnumManager {

    BUNDLE_NAME("configuration"),

    POST("POST"),
    GET("GET"),

    LOGIN("login"),
    PASSWORD("password"),
    EMAIL("email"),

    COMMAND("command"),
    ACTION("action"),


    OFFER_A_SPEECH("OFFER_A_SPEECH"),
    REFUSE_CONFERENCE("REFUSE_CONFERENCE"),
    CONFIRM_CONFERENCE("CONFIRM_CONFERENCE"),
    DELETE_CONFERENCE("DELETE_CONFERENCE"),
    FORWARD_TO_SIGN_UP("FORWARD_TO_SIGN_UP"),
    FORWARD_TO_SIGN_IN("FORWARD_TO_SIGN_IN"),

    INDEX("INDEX"),
    SIGN_IN("SIGN_IN"),
    SIGN_UP("SIGN_UP"),
    SIGN_OUT("SIGN_OUT"),
    LOGIN_ERROR("MESSAGE.LOGIN_ERROR"),

    USER_CABINET("USER_CABINET"),
    SPEAKER_CABINET("SPEAKER_CABINET"),
    MODER_CABINET("MODER_CABINET"),
    ADMIN_CABINET("ADMIN_CABINET");

    private String value;

    EnumManager(String v) {
        value = v;
    }

    @Override
    public String toString() {
        return value;
    }
}
