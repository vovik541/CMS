package app.help;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Help {
    public static void main(String[] args) throws IOException {

        Properties properties=new Properties();

        FileOutputStream messages=new FileOutputStream("src/main/resources/messages.properties");
        properties.setProperty("register","Register");
        properties.setProperty("signOut","Sign Out");
        properties.setProperty("submit","Submit");
        properties.setProperty("delete","Delete");
        properties.setProperty("confirm","Confirm");
        properties.setProperty("refuse","Refuse");
        properties.setProperty("status","Status:");
        properties.setProperty("accepted","ACCEPTED");
        properties.setProperty("notAccepted","NOT ACCEPTED");
        properties.setProperty("show","Show");


        properties.setProperty("user.registerInConference","Register in conference");
        properties.setProperty("user.visited","Conferences you have visited");
        properties.setProperty("user.registered","You are registered.");
        properties.setProperty("user.giveARate","Give a rate.");
        properties.setProperty("user.rate","Rate:");
        properties.setProperty("user.haveRated","You have rated a speaker:");

        properties.setProperty("login.enter","Sign in:");
        properties.setProperty("login.login","Login");
        properties.setProperty("login.password","Password");
        properties.setProperty("login.errorLoginPassMessage","Login or Password doesn't suit!");

        properties.setProperty("speaker","Speaker");
        properties.setProperty("speaker.offer","Offer a Speech");
        properties.setProperty("speaker.confName","Topic / Theme:");
        properties.setProperty("speaker.beginsAt","Begins at:");
        properties.setProperty("speaker.endsAt", "Ends at:");
        properties.setProperty("speaker.location", "Location:");
        properties.setProperty("speaker.date","Date:");
        properties.setProperty("speaker.confAdded","The Conference has been added.");
        properties.setProperty("speaker.incorrectInput","Incorrect input! Please, check all fields and try again.");
        properties.setProperty("speaker.moreDetails","More details:");
        properties.setProperty("speaker.accByModer","Accepted by moder:");
        properties.setProperty("speaker.present","Present:");
        properties.setProperty("speaker.registered","Registered:");
        properties.setProperty("speaker.denyMessage","You can't delete conf accepted by Moder");

        properties.store(messages,"");

        //------------------------------------------------------------------------------

        FileOutputStream messages_en=new FileOutputStream("src/main/resources/messages_en.properties");

        properties.setProperty("register","Register");
        properties.setProperty("signOut","Sign Out");

        properties.setProperty("user.registerInConference","Register in conference");
        properties.setProperty("user.visited","Conferences you have visited");
        properties.setProperty("user.registered","You are registered.");
        properties.setProperty("user.giveARate","Give a rate.");
        properties.setProperty("user.rate","Rate.");
        properties.setProperty("user.haveRated","You have rated a speaker:");

        properties.setProperty("login.enter","Sign in:");
        properties.setProperty("login.login","Login");
        properties.setProperty("login.password","Password");
        properties.setProperty("login.errorLoginPassMessage","Login or Password doesn't suit!");

        properties.setProperty("speaker","Speaker");
        properties.setProperty("speaker.offer","Offer a Speech");
        properties.setProperty("speaker.confName","Topic / Theme:");
        properties.setProperty("speaker.beginsAt","Begins at:");
        properties.setProperty("speaker.endsAt", "Ends at:");
        properties.setProperty("speaker.location", "Location");
        properties.setProperty("speaker.date","Date:");

        properties.store(messages_en,"");

        //------------------------------------------------------------------------------

        FileOutputStream messages_ua=new FileOutputStream("src/main/resources/messages_ua.properties");
        properties.setProperty("register","Register");
        properties.setProperty("signOut","Sign Out");

        properties.setProperty("user.registerInConference","Register in conference");
        properties.setProperty("user.visited","Conferences you have visited");
        properties.setProperty("user.registered","You are registered.");
        properties.setProperty("user.giveARate","Give a rate.");
        properties.setProperty("user.rate","Rate.");
        properties.setProperty("user.haveRated","You have rated a speaker:");

        properties.setProperty("login.enter","Sign in:");
        properties.setProperty("login.login","Login");
        properties.setProperty("login.password","Password");
        properties.setProperty("login.errorLoginPassMessage","Login or Password doesn't suit!");

        properties.setProperty("speaker","Speaker");
        properties.setProperty("speaker.offer","Offer a Speech");
        properties.setProperty("speaker.confName","Topic / Theme:");
        properties.setProperty("speaker.beginsAt","Begins at:");
        properties.setProperty("speaker.endsAt", "Ends at:");
        properties.setProperty("speaker.location", "Location");
        properties.setProperty("speaker.date","Date:");

        properties.store(messages_ua,"");

        //------------------------------------------------------------------------------

        FileOutputStream messages_ru=new FileOutputStream("src/main/resources/messages_ru.properties");
        properties.setProperty("register","Register");
        properties.setProperty("signOut","Sign Out");

        properties.setProperty("user.registerInConference","Register in conference");
        properties.setProperty("user.visited","Conferences you have visited");
        properties.setProperty("user.registered","You are registered.");
        properties.setProperty("user.giveARate","Give a rate.");
        properties.setProperty("user.rate","Rate.");
        properties.setProperty("user.haveRated","You have rated a speaker:");

        properties.setProperty("login.enter","Sign in:");
        properties.setProperty("login.login","Login");
        properties.setProperty("login.password","Password");
        properties.setProperty("login.errorLoginPassMessage","Login or Password doesn't suit!");

        properties.setProperty("speaker","Speaker");
        properties.setProperty("speaker.offer","Offer a Speech");
        properties.setProperty("speaker.confName","Topic / Theme:");
        properties.setProperty("speaker.beginsAt","Begins at:");
        properties.setProperty("speaker.endsAt", "Ends at:");
        properties.setProperty("speaker.location", "Location");
        properties.setProperty("speaker.date","Date:");

        properties.store(messages_ru,"");
    }
}