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

public class AdminCabinetServlet extends HttpServlet {

    Logger logger = Logger.getLogger(AdminCabinetServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("In AdminCabinetServletServlet.doGet");
        req.getRequestDispatcher("/views/cabinets/adminCabinet.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("In AdminCabinetServlet.doPost");
        doGet(req,resp);
    }
}
