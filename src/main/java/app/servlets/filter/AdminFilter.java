package app.servlets.filter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import app.entities.User;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class AdminFilter implements Filter {
    Logger logger = Logger.getLogger(AdminFilter.class);

    private FilterConfig filterConfig;

    public AdminFilter()
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
        logger.info("AuthFilter initialized!");
        filterConfig = fConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException
    {
        logger.info("AuthFilter worked!");

        if (filterConfig.getInitParameter("active").equalsIgnoreCase("true")) {
            logger.info("AuthFilter active true");

            final HttpServletRequest req = (HttpServletRequest) request;
            final HttpServletResponse resp = (HttpServletResponse) response;

            User user = (User) req.getSession().getAttribute("user");

            if(user.getRole() == 4){
                filterChain.doFilter(request, response);
            }else {
                req.getRequestDispatcher("/").forward(req,resp);   // Hacker page!!
            }

        }
    }
}
