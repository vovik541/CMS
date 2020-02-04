/*
package app.filters;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import app.Managers.ResourceManager;
import app.entities.Role;
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

            User user = (User) session.getAttribute("currentUser");

            String commandFromFront = request.getParameter(ResourceManager.COMMAND.toString());

            logger.info("COMMAND IN FACTORY "+ commandFromFront);

            if(commandFromFront != null || !commandFromFront.isEmpty()){
                ResourceManager command = ResourceManager.valueOf(commandFromFront.toUpperCase());
                if((command == ResourceManager.MODER_CABINET ||
                        command == ResourceManager.ADMIN_CABINET ||
                        command == ResourceManager.USER_CABINET||
                        command == ResourceManager.SPEAKER_CABINET)&& user == null){
                    resp.sendRedirect("/");
                }else {
                    if(user != null){
                        switch (command){
                            case ADMIN_CABINET:
                                if(user.getRole() == Role.ADMIN){
                                    filterChain.doFilter(request, response);
                                }else{
                                    resp.sendRedirect("/");
                                }
                            case MODER_CABINET:
                                if(user.getRole() == Role.MODER){
                                    filterChain.doFilter(request, response);
                                }else{

                                }
                            case SPEAKER_CABINET:
                                if(user.getRole() == Role.SPEAKER){
                                    filterChain.doFilter(request, response);
                                }else{
                                    resp.sendRedirect("/");
                                }
                            case USER_CABINET:
                                if(user.getRole() == Role.USER){
                                    filterChain.doFilter(request, response);
                                }else{
                                    resp.sendRedirect("/");
                                }
                            default:filterChain.doFilter(request, response);
                        }

                    }
                }
            }
        }
    }
}
*/
