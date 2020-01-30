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



        return null;
    }
}
