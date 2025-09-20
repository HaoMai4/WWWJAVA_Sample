package iuh.fit.se;

import iuh.fit.se.dto.Person;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;

import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "restClientJsonServlet", urlPatterns = {"/rest-client-json-servlet"})
public class RestClientJsonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        Client client = ClientBuilder.newClient();
        WebTarget wt = client.target("http://localhost:8080/RestAPISample/api/persons");

        List<Person> personList = wt
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<Person>>() {});

        PrintWriter out = resp.getWriter();

        for(Person person:personList) {
            out.println(person);
        }
        out.flush();
        out.close();
    }
}

