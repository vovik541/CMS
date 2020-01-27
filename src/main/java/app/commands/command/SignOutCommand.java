package app.commands.command;

import app.Managers.ConfigurationManager;
import app.Managers.EnumManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignOutCommand implements ICommand{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = ConfigurationManager.getInstance()
                .getProperty(EnumManager.SIGN_OUT.toString());

        request.getSession().invalidate();

        return page;
    }
}
