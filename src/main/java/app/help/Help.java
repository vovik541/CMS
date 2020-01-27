package app.help;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Help {
    public static void main(String[] args) throws IOException {

        Properties properties=new Properties();

        FileOutputStream messages=new FileOutputStream("src/main/resources/messages.properties");
        properties.setProperty("greetings","HELLO");

        properties.setProperty("login.enter","Sign in:");
        properties.setProperty("login.login","Login");
        properties.setProperty("login.password","Password");
        properties.setProperty("login.errorLoginPassMessage","Login or Password doesn't suit!");
        properties.store(messages,"");

        //------------------------------------------------------------------------------

        FileOutputStream messages_en=new FileOutputStream("src/main/resources/messages_en.properties");
        properties.setProperty("greetings","HELLO");

        properties.setProperty("login.enter","Sign in:");
        properties.setProperty("login.login","Login");
        properties.setProperty("login.password","Password");
        properties.setProperty("login.errorLoginPassMessage","Login or Password doesn't suit!");

        properties.store(messages_en,"");

        //------------------------------------------------------------------------------

        FileOutputStream messages_ua=new FileOutputStream("src/main/resources/messages_ua.properties");
        properties.setProperty("greetings","ПРИВІТ");

        properties.setProperty("login.enter","Вхід:");
        properties.setProperty("login.login","Логін");
        properties.setProperty("login.password","Пароль");
        properties.setProperty("login.errorLoginPassMessage","Невірний пароль або логін!");

        properties.store(messages_ua,"");

        //------------------------------------------------------------------------------

        FileOutputStream messages_ru=new FileOutputStream("src/main/resources/messages_ru.properties");
        properties.setProperty("greeting","Привет");

        properties.setProperty("login.enter","Вход:");
        properties.setProperty("login.login","Логин");
        properties.setProperty("login.password","Пароль");
        properties.setProperty("login.errorLoginPassMessage","Неверный пароль или логин!");

        properties.store(messages_ru,"");
    }
}