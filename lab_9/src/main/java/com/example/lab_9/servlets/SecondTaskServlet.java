package com.example.lab_9.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Enumeration;

@WebServlet(name = "SecondTaskServlet", value = "/SecondTaskServlet")
public class SecondTaskServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        PrintWriter out= response.getWriter();

//        response.setContentType("text/html; charset=UTF-8");
//        response.setCharacterEncoding("UTF-8");
        out.println("Current time " + (LocalDateTime.now()).toLocalTime() );
        out.println("Protocol "+ request.getProtocol());
        out.println("Sender IP" + request.getRemoteAddr());
        out.println("Hostname " + request.getRemoteHost() + " "
                + request.getRemoteUser());
        out.println("Method " + request.getMethod());
        out.println("URL "+ request.getRequestURL());
        out.println("-----------------------------------------");
        Enumeration< String > e = request.getHeaderNames();
        while (e.hasMoreElements()) {
            String name = e.nextElement();
            String value = request.getHeader(name);
            out.println(name + " = " + value);
        }
    }
}
