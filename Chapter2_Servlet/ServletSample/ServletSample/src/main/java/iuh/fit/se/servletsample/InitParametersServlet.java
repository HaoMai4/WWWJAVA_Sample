package iuh.fit.se.servletsample;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

//@WebServlet(
//        name = "initParametersServlet",
//        urlPatterns = {"/init-param"},
//        initParams = {
//                @WebInitParam(name = "username", value = "u0001"),
//                @WebInitParam(name = "email", value = "u001@gmail.com")
//        }
//    )
public class InitParametersServlet extends HttpServlet {

    private String userName;
    private String appName;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        this.userName = config.getInitParameter("username");

        this.appName = config.getServletContext().getInitParameter("appName");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = this.getServletConfig().getInitParameter("email");
        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet InitParametersServlet</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Get Init Parameters</h1>");
        out.println("User Name: " + this.userName  + "<br >");
        out.println("Email: " + email + "<br >");

        out.println("<h1>Get Global Parameters</h1>");
        out.println("App Name: " + this.appName  + "<br >");

        out.println("<h1>Get path</h1>");
        out.println("Get Context path: " + req.getServletContext().getContextPath() + "<br >");
        out.println("Get Request URI: " + req.getRequestURI() + "<br >");

        out.println("<h1>Get header information</h1>");
        out.println("<table width = \"100%\" border = \"1\" align = \"center\">\n"
                + "<tr bgcolor = \"#949494\">\n"
                + "<th>Header Name</th><th>Header Value(s)</th>\n"
                + "</tr>\n"
        );

        // get header names
        Enumeration<String> headerNames = req.getHeaderNames();

        while (headerNames.hasMoreElements()) {
            String paramName = (String) headerNames.nextElement();
            out.print("<tr><td>" + paramName + "</td>\n");
            String paramValue = req.getHeader(paramName);
            out.println("<td> " + paramValue + "</td></tr>\n");
        }
        out.println("</table>");

        out.println("</body>");
        out.println("</html>");
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
