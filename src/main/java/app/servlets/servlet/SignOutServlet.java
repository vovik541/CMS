package app.servlets.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


    /**
     * Logout.
     * Delete session.
     */
public class SignOutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                throws ServletException, IOException {

    final HttpSession session = req.getSession();

//    session.removeAttribute("user");
    session.invalidate();
    resp.sendRedirect("/views/authorization/signOut.jsp");
    }

}

