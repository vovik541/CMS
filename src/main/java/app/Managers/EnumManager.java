package app.Managers;

public enum EnumManager {

    BUNDLE_NAME("configuration"),


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
