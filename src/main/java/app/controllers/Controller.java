package app.controllers;

import app.Managers.ConfigurationManager;
import app.Managers.EnumManager;
import app.commands.command.ICommand;
import app.commands.factory.CommandFactory;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    private static final Logger logger = Logger.getLogger(Controller.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        logger.info("In Controller doGet");
        processRequest(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        logger.info("In Controller doPost");
        processRequest(request, response);
    }
    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response)
            throws ServletException, IOException {

        String page = null;

        CommandFactory client = new CommandFactory();
        ICommand command = client.defineCommand(request);
        page = command.execute(request, response);

        logger.info("PAGE IN CONTROLLER: "+ page);

        if (page != null) {

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);

        } else {
// установка страницы c cообщением об ошибке
            page = ConfigurationManager.getInstance()
                    .getProperty(EnumManager.INDEX.toString());

            logger.info(request.getContextPath() + "  -> request.getContextPath()");
            logger.info("RESPONSE TO:" + request.getContextPath() + page);
            response.sendRedirect(request.getContextPath() + page);
        }
    }
}