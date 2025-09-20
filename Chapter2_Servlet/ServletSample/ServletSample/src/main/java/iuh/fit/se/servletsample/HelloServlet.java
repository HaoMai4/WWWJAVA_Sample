package iuh.fit.se.servletsample;

import java.io.*;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet",
        urlPatterns = {"/hello", "/hello-servlet"},
        initParams = {
            @WebInitParam(name = "username", value = "nguyenvana"),
                @WebInitParam(name = "email", value = "nguyenvana@yahoo.com")
        }
)
public class HelloServlet extends HttpServlet {
    private String message;

    private String username;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        message = "Hello World!";
        username = config.getInitParameter("username");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("<h1> App name: " + this.getServletContext().getInitParameter("appName") + "</h1>");
        out.println("<h1> Username: " + username + "</h1>");
        out.println("<h1> Email: " + this.getServletConfig().getInitParameter("email") + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}