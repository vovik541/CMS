package app.servlets;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {
    /**
     * TRUEEEEEE database connector.
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        final ServletContext servletContext =
                servletContextEvent.getServletContext();

        servletContext.setAttribute("dao", "dao");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }


}
