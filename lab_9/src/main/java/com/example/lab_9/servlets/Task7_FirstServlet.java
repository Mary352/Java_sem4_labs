package com.example.lab_9.servlets;

import java.io.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.servlet.RequestDispatcher;

@WebServlet("/Task7_FirstServlet")
public class Task7_FirstServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String message = (String)request.getAttribute("messages");
//
//        if (message==null || message.equals(""))
//        {
//            message = "message from Task7_FirstServlet ";
//            request.setAttribute("messages", message);
//            request.getRequestDispatcher("Task7_SecondServlet").forward(request, response);
//        }
//        else
//            response.getWriter().println(message);
        //response.getWriter().println("response first");
        String path = "/Task7_SecondServlet";
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
        //request.getServletContext().getRequestDispatcher("/Task7_SecondServlet").forward(request, response);

        //        String respString = resp.getHeader("Data");
//        if(respString==null || respString.equals("")) {
//            req.setAttribute("req",req);
//            req.setAttribute("resp",resp);
//            req.getRequestDispatcher("Task7_SecondServlet").forward(req, resp);
//        }
//        else{
//            resp.getWriter().println(respString);
//        }
    }
}

