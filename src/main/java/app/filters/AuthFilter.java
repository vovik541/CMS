package app.filters;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

public class AuthFilter implements Filter {
    Logger logger = Logger.getLogger(AuthFilter.class);

    private FilterConfig filterConfig;

    public AuthFilter()
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

            final HttpSession session = req.getSession();

            if(session.getAttribute("user") == null){
                logger.info("user hasn't access to page! redirecting to /login");
                resp.sendRedirect("/login");
            }else {
                logger.info("AuthServlet worked good");
                filterChain.doFilter(request, response);
            }
        }
    }
}
