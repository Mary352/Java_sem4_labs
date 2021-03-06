package fit.bstu.Lab9.Servlets;

import fit.bstu.Lab9.Classes.Cookies;
import fit.bstu.Lab9.Classes.Hasher;
import fit.bstu.Lab9.DB.User;
import fit.bstu.Lab9.DB.UserService;
import org.apache.log4j.Logger;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.xml.transform.Result;

@WebServlet(name = "loginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        login(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        login(req, resp);
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=Cp1251");
        String br = "<br/>";
        PrintWriter out = null;
        try {
            out = resp.getWriter();
            String url = "jdbc:mysql://localhost/lab9?serverTimezone=Europe/Moscow&useSSL=false";
            String root = "root";
            String password = "21122000Ad";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

            try(Connection connection = DriverManager.getConnection(url, root, password)){

                String login = req.getParameter("login");
                String pass = Hasher.getHash(req.getParameter("password"));
                out.println("Connect success..");

                User user = new User();
                user.setLogin(login);
                user.setPassword(pass);

                user = checkUser(user, getUsers(connection));

                if(user != null){
                    successLogin(out, user);
                    Cookies.setCookie(resp, user);
                    LOGGER.info("Login success ...");
                    req.getRequestDispatcher("home").forward(req, resp);
                }else{
                    failedLogin(req, resp,out);
                }

            }

        }catch (Exception exc){
            failedLogin(req, resp, out);
        }finally {
            out.close();
        }
    }

    private void successLogin(PrintWriter out, User user){

        Date date = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("E yyyy.MM.dd ' and time' hh:mm:ss a zzz");
        String dateString = "Date: " + formatForDateNow.format(date);

        out.println("Hello " + user.getName() + "<br/>Your role: " + user.getRole() + "<br/>" + dateString);
    }

    private void failedLogin(HttpServletRequest req, HttpServletResponse resp, PrintWriter out) throws ServletException, IOException {
       LOGGER.info("Login failed ...");
        req.setAttribute("error","Not valid login or/and password!");
        req.getRequestDispatcher("Views/Login.jsp").forward(req,resp);
    }

    private User checkUser(User user, List<User> users){
        for (User i: users){
            if(user.getLogin().equals(i.getLogin()) && user.getPassword().equals(i.getPassword())){
                return  i;
            }
        }
        return null;
    }

    private List<User> getUsers(Connection connection) {
        UserService userService = new UserService();
        List<User> users = userService.findAll(connection);
        return users;
    }


}
