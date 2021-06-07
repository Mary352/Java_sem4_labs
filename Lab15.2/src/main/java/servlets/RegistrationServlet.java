package servlets;

import connection.UserDao;
import org.apache.log4j.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Arrays;

@WebServlet(value="/register",name = "signUP")
public class RegistrationServlet extends HttpServlet {
    //private static final Logger LOG = Logger.getLogger(RegistrationServlet.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        try {
            UserDao userDao = new UserDao();
            if(login.equals("") || password.equals("") || name.equals("")) {
                req.setAttribute("message", "fill all fields");
             //   LOG.error("Fill all fields");
                req.getRequestDispatcher("/register.jsp").forward(req, resp);
                return;
            }
            if (userDao.isExists(login)) {
                req.setAttribute("message","This login already exists");
             //   LOG.error("This login is already exist");
                req.getRequestDispatcher("/register.jsp").forward(req,resp);
            } else {
                userDao.addUser(login, password,name);
                req.setAttribute("message","registration complete");
              //  LOG.info("Registration complete");
                req.getRequestDispatcher("/login.jsp").forward(req,resp);
            }
        } catch( SQLException | ClassNotFoundException ex) {
          //  LOG.error(ex.getMessage());
            req.getRequestDispatcher("/Error.jsp").forward(req,resp);
        }
    }
}