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

        properties.setProperty("speaker.offer","Offer a Speech");
        properties.setProperty("speaker.confName","Topic / Theme:");
        properties.setProperty("speaker.year","Year:");
        properties.setProperty("speaker.month","Month:");
        properties.setProperty("speaker.day","Day:");
        properties.setProperty("speaker.beginsAt","Begins at:");
        properties.setProperty("speaker.endsAt", "Ends at:");
        properties.setProperty("speaker.hour","At (hour):");
        properties.setProperty("speaker.minute","At (min):");
        properties.setProperty("speaker.location", "Location");

        properties.store(messages,"");

        //------------------------------------------------------------------------------

        FileOutputStream messages_en=new FileOutputStream("src/main/resources/messages_en.properties");
        properties.setProperty("greetings","HELLO");

        properties.setProperty("login.enter","Sign in:");
        properties.setProperty("login.login","Login");
        properties.setProperty("login.password","Password");
        properties.setProperty("login.errorLoginPassMessage","Login or Password doesn't suit!");

        properties.setProperty("speaker.offer","Offer a Speech");
        properties.setProperty("speaker.confName","Topic / Theme:");
        properties.setProperty("speaker.year","Year:");
        properties.setProperty("speaker.month","Month:");
        properties.setProperty("speaker.day","Day:");
        properties.setProperty("speaker.beginsAt","Begins at:");
        properties.setProperty("speaker.endsAt", "Ends at:");
        properties.setProperty("speaker.hour","At (hour):");
        properties.setProperty("speaker.minute","At (min):");
        properties.setProperty("speaker.location", "Location");

        properties.store(messages_en,"");

        //------------------------------------------------------------------------------

        FileOutputStream messages_ua=new FileOutputStream("src/main/resources/messages_ua.properties");
        properties.setProperty("greetings","ПРИВІТ");

        properties.setProperty("login.enter","Вхід:");
        properties.setProperty("login.login","Логін");
        properties.setProperty("login.password","Пароль");
        properties.setProperty("login.errorLoginPassMessage","Невірний пароль або логін!");

        properties.setProperty("speaker.offer","Запропонувати доклад");
        properties.setProperty("speaker.confName","Тема доклада:");
        properties.setProperty("speaker.year","Рік:");
        properties.setProperty("speaker.month","Місяь:");
        properties.setProperty("speaker.day","День:");
        properties.setProperty("speaker.beginsAt","Початок в:");
        properties.setProperty("speaker.endsAt", "Конец в:");
        properties.setProperty("speaker.hour","о Годині:");
        properties.setProperty("speaker.minute","Хвилин:");
        properties.setProperty("speaker.location", "Місце проведення заходу:");

        properties.store(messages_ua,"");

        //------------------------------------------------------------------------------

        FileOutputStream messages_ru=new FileOutputStream("src/main/resources/messages_ru.properties");
        properties.setProperty("greeting","Привет");

        properties.setProperty("login.enter","Вход:");
        properties.setProperty("login.login","Логин");
        properties.setProperty("login.password","Пароль");
        properties.setProperty("login.errorLoginPassMessage","Неверный пароль или логин!");

        properties.setProperty("speaker.offer","Предложить Доклад");
        properties.setProperty("speaker.confName","Тема доклада:");
        properties.setProperty("speaker.year","Год (20**):");
        properties.setProperty("speaker.month","Месяц (1-12):");
        properties.setProperty("speaker.day","Число Дня:");
        properties.setProperty("speaker.beginsAt","Начало в:");
        properties.setProperty("speaker.endsAt", "Конец в:");
        properties.setProperty("speaker.hour","Час (0-24):");
        properties.setProperty("speaker.minute","Минут (0-60):");
        properties.setProperty("speaker.location", "Место проведения мероприятия");

        properties.store(messages_ru,"");
    }
}