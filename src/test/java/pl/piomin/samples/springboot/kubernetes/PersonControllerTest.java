package pl.piomin.samples.springboot.kubernetes;

import org.junit.jupiter.api.*;
import pl.piomin.samples.springboot.kubernetes.domain.Gender;
import pl.piomin.samples.springboot.kubernetes.domain.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonControllerTest {

	private static String id;

	@LocalServerPort
	int port;
	@Autowired
	TestRestTemplate restTemplate;

	@Test
	@Order(1)
	void add() {
		Person person = new Person(null, "Test", "Test", 100, Gender.FEMALE);
		Person personAdded = restTemplate.postForObject("/persons", person, Person.class);
		assertNotNull(personAdded);
		assertNotNull(personAdded.getId());
		assertEquals(person.getLastName(), personAdded.getLastName());
		id = personAdded.getId();
	}

	@Test
	@Order(2)
	void findById() {
		Person person = restTemplate.getForObject("/persons/{id}", Person.class, id);
		assertNotNull(person);
		assertNotNull(person.getId());
		assertEquals(id, person.getId());
	}

	@Test
	@Order(2)
	void findAll() {
		Person[] persons = restTemplate.getForObject("/persons", Person[].class);
		assertEquals(6, persons.length);
	}

}
