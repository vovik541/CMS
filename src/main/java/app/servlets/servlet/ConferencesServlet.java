package app.servlets.servlet;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ConferencesServlet extends HttpServlet {

    Logger logger = Logger.getLogger(ConferencesServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("In ConferencesServlet.doGet");
        req.getRequestDispatcher("/views/conferences.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        logger.info("In ConferencesServlet.doPost");

        HttpSession session = req.getSession();
        doGet(req,resp);
    }
}
