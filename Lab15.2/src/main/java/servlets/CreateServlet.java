package servlets;

import connection.Newspaper;
import connection.NewspaperDao;
import org.apache.log4j.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/create")
public class CreateServlet extends HttpServlet {
    //private static final Logger LOG = Logger.getLogger(CreateServlet.class);
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       // LOG.info("CreateServlet GET");
        getServletContext().getRequestDispatcher("/secret/create.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String newspapernumber = request.getParameter("newspapernumber");
            HttpSession session = request.getSession(true);
            int iduser = Integer.parseInt(session.getAttribute("iduser").toString());
            NewspaperDao newspaperDao = new NewspaperDao();
            newspaperDao.addNewspaper(newspapernumber, iduser);
           // LOG.info("CreateServlet POST");
            response.sendRedirect(request.getContextPath()+"/welcome");
        }
        catch(Exception ex) {
            request.setAttribute("message", "fail");
          //  LOG.error(ex.getMessage());
            getServletContext().getRequestDispatcher("/secret/create.jsp").forward(request, response);
        }
    }
}
