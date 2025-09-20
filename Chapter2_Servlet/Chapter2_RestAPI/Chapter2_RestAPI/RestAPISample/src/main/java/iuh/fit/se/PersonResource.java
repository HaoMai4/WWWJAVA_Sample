package iuh.fit.se;

import iuh.fit.se.entities.Person;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Path("/persons")
public class PersonResource {
    // Endpoint: /api/persons/{person-id} -> Get person information (get by person-id)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{person-id}")
    public Person getPerson() {
        Person person = new Person();
        person.setName("Nguyen Van A");
        person.setAge(20);

        return person;
    }

    // Endpoint: /api/persons -> Insert
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String handlePersonRequest(Person person) {
        return person.toString();
    }

    // Endpoint: /api/persons -> Get List
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getListPerson() {

        List<Person> personList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Person person = new Person();
            person.setName("Name #" + i);
            person.setAge(ThreadLocalRandom.current().nextInt(1 , 10 ));
            personList.add(person);
        }

        return personList;
    }
}
