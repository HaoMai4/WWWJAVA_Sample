package iuh.fit.se.servletsample;

import com.fasterxml.jackson.databind.ObjectMapper;
import iuh.fit.se.servletsample.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "userJsonServlet", urlPatterns = {"/user", "/user-json"})
public class UserJsonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        User user = new User("1", "Nguyen Van 001");
        ObjectMapper mapper = new ObjectMapper();
        String userJson = mapper.writeValueAsString(user);

        PrintWriter out = resp.getWriter();
        out.println(userJson);
        out.flush();
        out.close();
    }
}
