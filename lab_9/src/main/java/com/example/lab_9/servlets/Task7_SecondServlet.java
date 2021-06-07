package com.example.lab_9.servlets;

import java.io.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.servlet.RequestDispatcher;
import java.io.IOException;

@WebServlet("/Task7_SecondServlet")
class Task7_SecondServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        //HttpServletRequest firstReq = (HttpServletRequest) request.getAttribute("messages");
//        String firstReqAttr = (String) request.getAttribute("messages");
//        //String message2= null;
//        if(firstReqAttr!=null)
//        {
//            firstReqAttr+=" message from Task7_FirstServlet";
//            request.setAttribute("messages", firstReqAttr);
//            request.getRequestDispatcher("Task7_SecondServlet").forward(request, response);
//        }
//        else
//            response.getWriter().println("firstReqAttr == null");

        //response.getWriter().println("response second");

        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        String id = request.getParameter("id");

        try {
            writer.println("<h2>Not Found: " + id + "</h2>");
        } finally {
            writer.close();
        }

//        String respString= null;
//
//        if(firstReq!=null && firstRes!=null){
//            respString= "QueryString: "+firstReq.getRequestURI() + "<br/>RespString: " +
//                    firstRes.getHeaderNames();
//        }
//
//        resp.setContentType("text/html");
//        resp.setHeader("Data",respString);
//        req.getRequestDispatcher("Task7_FirstServlet").forward(req,resp);
    }
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("init");
    }
}