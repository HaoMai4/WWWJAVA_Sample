package iuh.fit.se;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "restClientServlet", urlPatterns = {"/rest-client-servlet"})
public class RestClientServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        Client client = ClientBuilder.newClient();

        // WebTarget wt1 = client.target("localhost:8080/RestAPISample/api/numbers/sum/{a}/{b}");
        WebTarget wt1 = client.target("http://localhost:8080/RestAPISample/api/numbers/sum")
                .path("1")
                .path("2");

        Response response1 = wt1.request().accept(MediaType.TEXT_PLAIN).get();
        String s1 = response1.readEntity(String.class);
        out.println("Sum: 1 + 2 = " + s1 + "\n");

        // WebTarget wt2 = client.target("localhost:8080/RestAPISample/api/numbers/multiply?a=1&b=2");
        WebTarget wt2 = client.target("http://localhost:8080/RestAPISample/api/numbers/multiply")
                .queryParam("a", 1)
                .queryParam("b", 2);

        Response response2 = wt2.request().accept(MediaType.TEXT_PLAIN).get();
        String s2 = response2.readEntity(String.class);
        out.println("Multiply: 1 x 2 = " + s2);
        out.flush();
        out.close();
    }

}
