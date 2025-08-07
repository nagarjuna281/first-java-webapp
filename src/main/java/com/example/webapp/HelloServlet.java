package com.example.webapp;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
                         throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html><html><head>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1'>");
            out.println("<link href='https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.2/css/bootstrap.min.css' rel='stylesheet' />");
            out.println("<title>Hello</title></head><body class='p-5'>");
            out.printf("<h1 class='display-6'>Hello, Maven + Tomcat + Nexus! 😎</h1>");
            out.printf("<p class='lead'>Current server time: %s</p>", LocalDateTime.now());
            out.println("<a class='btn btn-primary' href='/responsive-demo'>Back Home</a>");
            out.println("</body></html>");
        }
    }
}
