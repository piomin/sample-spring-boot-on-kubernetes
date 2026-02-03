package pl.piomin.samples.springboot.kubernetes;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.web.servlet.client.RestTestClient;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.piomin.samples.springboot.kubernetes.domain.Gender;
import pl.piomin.samples.springboot.kubernetes.domain.Person;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureRestTestClient
public class PersonControllerTest {

    private static String id;

    @Container
    @ServiceConnection
    static MongoDBContainer mongodb = new MongoDBContainer("mongo:8.2");

    @Autowired
    RestTestClient restTestClient;

    @Test
    @Order(1)
    void add() {
        Person person = new Person(null, "Test", "Test", 100, Gender.FEMALE);
        Person personAdded = restTestClient.post().uri("/persons")
                .body(person)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Person.class)
                .returnResult().getResponseBody();
        assertNotNull(personAdded);
        assertNotNull(personAdded.getId());
        assertEquals(person.getLastName(), personAdded.getLastName());
        id = personAdded.getId();
    }

    @Test
    @Order(2)
    void findById() {
        Person person = restTestClient.get().uri("/persons/{id}", id)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Person.class)
                .returnResult().getResponseBody();
        assertNotNull(person);
        assertNotNull(person.getId());
        assertEquals(id, person.getId());
    }

    @Test
    @Order(3)
    void findAll() {
        restTestClient.get().uri("/persons")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.length()").isEqualTo(6);
    }

}
