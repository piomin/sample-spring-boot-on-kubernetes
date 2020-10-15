package pl.piomin.samples.springboot.kubernetes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.piomin.samples.springboot.kubernetes.domain.Gender;
import pl.piomin.samples.springboot.kubernetes.domain.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonControllerTest {

	@LocalServerPort
	int port;
	@Autowired
	TestRestTemplate restTemplate;

	@Test
	void testAdd() {
		Person person = new Person(null, "Test", "Test", 100, Gender.FEMALE);
		Person personAdded = restTemplate.postForObject("/persons", person, Person.class);
		Assertions.assertNotNull(personAdded);
		Assertions.assertNotNull(personAdded.getId());
		Assertions.assertEquals(person.getLastName(), personAdded.getLastName());
	}

}
