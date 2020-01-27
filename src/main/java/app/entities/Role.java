package app.entities;

public enum  Role {

    USER("USER"),
    SPEAKER("SPEAKER"),
    MODER("MODER"),
    ADMIN("ADMIN");

    private String value;

    Role(String v) {
    value = v;
    }

    @Override
    public String toString() {
        return value;
    }
}
