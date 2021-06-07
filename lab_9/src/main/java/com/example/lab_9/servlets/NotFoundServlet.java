package com.example.lab_9.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/notfound")
public class NotFoundServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        //String id = request.getParameter("id");

                //HttpServletRequest firstReq = (HttpServletRequest) request.getAttribute("messages");
        String firstReqAttr = (String) request.getAttribute("messages");
        //String message2= null;
        if(firstReqAttr!=null)
        {
            firstReqAttr+="+ message from NotFoundServlet";
            request.setAttribute("messages", firstReqAttr);
            request.getRequestDispatcher("hello").forward(request, response);
        }
        else
            response.getWriter().println("firstReqAttr == null");

//        try {
//            writer.println("<h2>" + firstReqAttr + "</h2>");
//        } finally {
//            writer.close();
//        }
    }
}
