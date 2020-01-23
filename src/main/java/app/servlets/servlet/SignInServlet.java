package app.servlets;

import app.entities.User;
import app.models.SignInModel;
import app.servlets.filter.SimpleServletFilter;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignInServlet extends HttpServlet {

    Logger logger = Logger.getLogger(SimpleServletFilter.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("In SignInServlet.doGet");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/signIn.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        logger.info("In SignInServlet.doPost");
        boolean noMatches = true;
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        User user = SignInModel.getUserByLogPas(login,password);

        HttpSession session = req.getSession();
        session.setAttribute("user", user);
//        noMatches = user==null ? true : false;
//        req.setAttribute("noMatches", noMatches);
        resp.sendRedirect("/logout");
//        doGet(req, resp);
    }
}
