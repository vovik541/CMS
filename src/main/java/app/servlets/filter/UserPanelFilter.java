package app.servlets.filter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import app.entities.User;
import app.models.SignInModel;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

public class UserFilter implements Filter {
    Logger logger = Logger.getLogger(UserFilter.class);

    private FilterConfig filterConfig;

    public UserFilter()
    {
    }

    @Override
    public void destroy()
    {
        filterConfig = null;
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException
    {
        logger.info("UserFilter initialized!");
        filterConfig = fConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException
    {
        logger.info("Simple Filter works!");
        // Если фильтр активной, то выполнить проверку
        if (filterConfig.getInitParameter("active").equalsIgnoreCase("true")) {
            logger.info("active true");

            final HttpServletRequest req = (HttpServletRequest) request;
            final HttpServletResponse res = (HttpServletResponse) response;

            // Если открывается главная страница, то выполняем проверку

            final String login = req.getParameter("login");
            final String password = req.getParameter("password");


            final HttpSession session = req.getSession();
            User user;

            if(session.getAttribute("user") != null){
                logger.info("forwarding to reg");
//                ServletContext ctx = filterConfig.getServletContext();
//                RequestDispatcher dispatcher = ctx.getRequestDispatcher("/registration.jsp");
//                dispatcher.forward(request, response);
                res.sendRedirect("/registration.jsp");
                return;
            }else if((user= SignInModel.getUserByLogPas(login,password)) != null){
                logger.info("forwarding to main");
                session.setAttribute("user", user);
                ServletContext ctx = filterConfig.getServletContext();
                RequestDispatcher dispatcher = ctx.getRequestDispatcher("/main.jsp");
                dispatcher.forward(request, response);
                return;
            }else {
                logger.info("forwarding to login");
//                ServletContext ctx = filterConfig.getServletContext();
//                RequestDispatcher dispatcher = ctx.getRequestDispatcher("/login");
//                dispatcher.forward(request, response);
                res.sendRedirect("/login");
                return;
            }
        }
    }
}
