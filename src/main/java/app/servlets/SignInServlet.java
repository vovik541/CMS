package app.servlets;

import app.entities.User;
import app.model.RegLogModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/signIn.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        boolean noMatches = true;

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        User user = RegLogModel.readCustomByLogPas(login,password);

        noMatches = user==null ? true : false;

        req.setAttribute("noMatches", noMatches);
        doGet(req, resp);
    }
}
