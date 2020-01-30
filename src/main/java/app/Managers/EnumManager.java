package app.Managers;

public enum EnumManager {

    BUNDLE_NAME("configuration"),

    LOGIN("login"),
    PASSWORD("password"),
    EMAIL("email"),

    COMMAND("command"),
    ACTION("cation"),


    INDEX("INDEX"),
    SIGN_IN("SIGN_IN"),
    SIGN_UP("SIGN_UP"),
    SIGN_OUT("SIGN_OUT"),
    OFFER_A_SPEECH("OFFER_A_SPEECH"),
    FORWARD_TO_SIGN_UP("FORWARD_TO_SIGN_UP"),
    FORWARD_TO_SIGN_IN("FORWARD_TO_SIGN_IN"),
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
