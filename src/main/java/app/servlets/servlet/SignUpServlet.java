package app.servlets.servlet;

import app.entities.User;

import app.models.SignUpModel;
import org.apache.log4j.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class SignUpServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(SignUpServlet.class);

    @Override
    public void init(){
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("In SignUpServlet.doGet()");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/register");
        req.setCharacterEncoding("UTF-8");
        ResourceBundle bundle = ResourceBundle.getBundle("resources",
                new Locale("ru"));
        req.setAttribute("bundle", bundle);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("In SignUpServlet.doPost()");

        req.setCharacterEncoding("UTF-8");

        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        SignUpModel.createUser(new User(firstName,lastName,
                email, login, password));

        req.setAttribute("userName", firstName);
        doGet(req, resp);
    }

    @Override
    public void destroy(){
    }
}
