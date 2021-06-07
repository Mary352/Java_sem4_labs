package servlets;

import org.apache.log4j.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet( name="Auth", urlPatterns = "/GoToLogin")
public class GoToLogin extends HttpServlet {
    //private static final Logger LOG = Logger.getLogger(GoToLogin.class);
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //LOG.info("GoToLogin POST");
        response.sendRedirect("login.jsp");
    }
}