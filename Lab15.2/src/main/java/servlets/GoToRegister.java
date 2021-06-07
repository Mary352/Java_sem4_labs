package servlets;

import org.apache.log4j.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet( name="Register", urlPatterns = "/GoToRegister")
public class GoToRegister extends HttpServlet {
    //private static final Logger LOG = Logger.getLogger(GoToRegister.class);
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            if(request.getAttribute("message") == null)
                request.setAttribute("message","");
          //  LOG.info("GoToRegister POST");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
        } catch (ServletException e) {
            //LOG.error(e.getMessage());
            e.printStackTrace();
        }
    }
}