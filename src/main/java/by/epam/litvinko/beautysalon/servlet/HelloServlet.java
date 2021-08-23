package by.epam.litvinko.beautysalon.servlet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(name = "helloServlet", urlPatterns = "/controller")
public class HelloServlet extends HttpServlet {

    public void init(){
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String n = request.getParameter("num");
        int number = Integer.parseInt(n) * 7;
        request.setAttribute("res", number);
        request.getRequestDispatcher("index.jsp").forward(request, response);


    }
}
