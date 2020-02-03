package app.commands.command;

import app.entities.User;

public class test {
    public static void main(String[] args) {
        System.out.println(new User.Builder().setCustomerId(1).setFirstName("Vova").setLastName("Khymych").build().toString());
    }
}
