package servlets;

import connection.UserDao;
import org.apache.log4j.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Arrays;

@WebServlet(value = "/login",name="signIN")
public class AuthorizeServlet extends HttpServlet {
   // private static final Logger LOG = Logger.getLogger(AuthorizeServlet.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try {
            UserDao userDao = new UserDao();
            if (userDao.checkFor(login, password)) {
                HttpSession session = req.getSession(true);
                session.setAttribute("auth","true");
                session.setAttribute("iduser", Integer.toString(userDao.getUserId(login)));
                session.setAttribute("name", userDao.getNameUser(login));
                session.setAttribute("login_number", Integer.toString(userDao.getLoginNumber(login)));
                session.setAttribute("last_login", userDao.getLoginTimestamp(login).toString());
                if(userDao.getRoleNumber(login) == 0) {
                    session.setAttribute("user_role", "user");
                }
                else {
                    session.setAttribute("user_role", "admin");
                }
                userDao.setLastLogin(login);
                resp.addCookie(new Cookie("last_login", userDao.getLoginTimestamp(login).toString()));
                resp.addCookie(new Cookie("login_number", Integer.toString(userDao.getLoginNumber(login))));
                resp.addCookie(new Cookie("user_role", Integer.toString(userDao.getRoleNumber(login))));
              //  LOG.info("Successful authorization");
                getServletContext().getRequestDispatcher("/welcome").forward(req, resp);
            } else
                {
                    req.setAttribute("message","wrong login or password");
                    req.getRequestDispatcher("/login.jsp").forward(req,resp);
                   // LOG.error("Wrong login or password");
            }
        } catch (SQLException | ClassNotFoundException ex) {
           // LOG.error(ex.getMessage());
            req.getRequestDispatcher("/Error.jsp").forward(req,resp);
        }
    }
}