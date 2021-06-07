package com.example.lab_9;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.servlet.RequestDispatcher;

@WebServlet(name = "getSecondTaskStartPage", value ="/getSecondTaskStartPage")
public class GetSecondTaskStartPage  extends  HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("pages/SecondTaskStartPage.jsp");
        requestDispatcher.forward(req, resp);
    }
}
