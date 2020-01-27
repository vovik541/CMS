package app.servlets.servlet;

import app.entities.User;
import app.models.SignInModel;

import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignInServlet extends HttpServlet {

    Logger logger = Logger.getLogger(SignInServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("In SignInServlet.doGet");
        req.getRequestDispatcher("/WEB-INF/views/authorization/signIn.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        logger.info("In SignInServlet.doPost");

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        HttpSession session = req.getSession();   //FALSE!!!
        System.out.println(session.getId());

        if(session.getAttribute("user") == null){
            User user = SignInModel.getUserByLogPas(login,password);
            if(user != null){
                logger.info("user added to session");
                session.setAttribute("user", user);
                resp.sendRedirect("/conferences");
//                moveToPage(req,resp,user.getRole());
            }else {
                doGet(req, resp);
                //MASSAGE!!!
            }
        }
    }
    public void moveToPage(final HttpServletRequest req,
                           final HttpServletResponse resp,
                           final int role)
            throws ServletException, IOException {
        logger.info("IN SWITCH");
        switch (role){
            case 1:
                logger.info("/conf");
//                RequestDispatcher dispatcher = req.getRequestDispatcher("/conferences");
//                dispatcher.forward(req, resp);
                resp.sendRedirect("/conferences");break;
        }
    }
}
