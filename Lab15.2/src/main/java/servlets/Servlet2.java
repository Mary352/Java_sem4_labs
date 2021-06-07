package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Servlet2")
public class Servlet2 extends HttpServlet {
    String name ;
    ServletConfig config;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("id",3);
        resp.setContentType("text/html");
        resp.setStatus(1);
        req.getRequestDispatcher("Servlet1").include(req,resp);
        PrintWriter writer = resp.getWriter();
        writer.println(resp.getStatus());
        writer.println(req.getAttribute("id"));
    }
}