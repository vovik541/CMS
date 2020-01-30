package app.commands.command;

import app.Managers.ConfigurationManager;
import app.Managers.EnumManager;
import app.entities.Conference;
import app.entities.User;
import app.persistences.factory.MySqlDaoFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class UserCabinetCommand implements ICommand{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = null;

        String method = request.getMethod();
        ConfigurationManager confManager = ConfigurationManager.getInstance();

        if (method.equalsIgnoreCase(EnumManager.GET.toString())) {

            page = confManager.getProperty(EnumManager.USER_CABINET.toString());

        }else if (method.equalsIgnoreCase(EnumManager.POST.toString())){

            String action = request.getParameter(EnumManager.ACTION.toString());
            System.out.println("ACTION "+action);

            if(action != null){

                EnumManager speakerAction = EnumManager.valueOf(action.toUpperCase());
                User user = (User) request.getSession().getAttribute("currentUser");

                switch (speakerAction){
                    default:
                        break;
                }
            }

        }



        return page;
    }

    private static List<Conference> getConfForView(){

        Calendar c=Calendar.getInstance();

        int year=c.get(c.YEAR);
        int month=c.get(c.MONTH)+1;
        int day = c.get(c.DAY_OF_MONTH);
        int hours = c.get(c.HOUR_OF_DAY);
        int minutes = c.get(c.MINUTE);

        String currentDate = year+"-"+toFormat(month)+"-"+toFormat(day)+" 00:00:00";
        String currentTime = toFormat(hours)+":"+toFormat(minutes)+":00";

        return MySqlDaoFactory.getConferenceDAO().getConfBeforeDateTime(currentDate,currentTime);
    }

    private static String toFormat(int number){
        if(number < 10){
            return "0"+number;
        }
        return String.valueOf(number);
    }
}
