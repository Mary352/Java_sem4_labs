package servlets;

import connection.NewspaperDao;
import org.apache.log4j.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
   // private static final Logger LOG = Logger.getLogger(DeleteServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            NewspaperDao newspaperDao = new NewspaperDao();
            int id = Integer.parseInt(request.getParameter("id"));
            newspaperDao.delete(id);
           // LOG.info("DeleteServlet POST");
            response.sendRedirect(request.getContextPath() + "/welcome");
        }
        catch(Exception ex) {
           // LOG.error(ex.getMessage());
            getServletContext().getRequestDispatcher("/Error.jsp").forward(request, response);
        }
    }
}