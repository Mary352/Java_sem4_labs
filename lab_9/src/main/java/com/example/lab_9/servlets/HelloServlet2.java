package com.example.lab_9.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello")
public class HelloServlet2 extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String message = (String)request.getAttribute("messages");

        if (message==null || message.equals(""))
        {
            message = "message from HelloServlet2 ";
            request.setAttribute("messages", message);
            request.getRequestDispatcher("notfound").forward(request, response);
        }
        else
            response.getWriter().println(message);

//        String path = "/notfound";
//        ServletContext servletContext = getServletContext();
//        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
//        requestDispatcher.forward(request, response);
    }
}
