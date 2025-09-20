package iuh.fit.se.servletsample;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet(name = "studentRegistrationServlet", urlPatterns = {"/student-registration"})
public class StudentRegistrationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Set the response message's MINE type
        resp.setContentType("text/html; charset=UTF8");

        PrintWriter out = resp.getWriter();

        // Write the response message, in an HTML page
        out.println("<!DOCTYPE html>");
        out.println("<html><head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>User Servlet</title></head>");
        out.println("<body><h2>You have enter</h2>");

        Enumeration<String> paramNames = req.getParameterNames();
        if (!paramNames.hasMoreElements()) {
            out.println("No parameters in the request.");
        } else {
            out.println("Parameters in the request:");
            while (paramNames.hasMoreElements()) {
                String name = paramNames.nextElement();
                String[] values = req.getParameterValues(name);
                out.print(name + " = ");
                if (values.length == 1) {
                    out.println(values[0] + "<br >");
                } else {
                    out.println(String.join(", ", values) + "<br >");
                }
            }
        }
        out.println("</body>");

        out.close();
    }
}
