package iuh.fit.se;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import iuh.fit.se.dto.Person;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@WebServlet(name = "restClientUsingHttpClientJsonServlet", urlPatterns = {"/rest-httpclient-json-servlet"})
public class RestClientUsingHttpClientJsonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();

        List<Person> personList = null;
        try {
            personList = getPersons();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (Person person : personList) {
            writer.println(person);
        }
        writer.flush();
        writer.close();
    }

    private List<Person> getPersons() throws IOException, URISyntaxException, InterruptedException {
        String url = "http://localhost:8080/RestAPISample/api/persons";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest
                .newBuilder(new URI(url))
                .GET()
                .build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        String jsonListString = response.body();

        ObjectMapper objectMapper = new ObjectMapper();

        List<Person> personList = objectMapper
                .readValue(jsonListString,
                        new TypeReference<List<Person>>() {
        });

        return personList;
    }
}
