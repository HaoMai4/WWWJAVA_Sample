package iuh.fit.se.servletsample;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "myGenericServletDemo", urlPatterns = {"/generic-servlet"})
public class MyGenericServletDemo extends GenericServlet {
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet MyGenericServletDemo</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Servlet MyGenericServletDemo</h1>");
        out.println("<h3 style='color: blue'>Hello World</h3>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
}
