package pl.piomin.samples.springboot.kubernetes.controller;

import java.util.Optional;
import java.util.Set;

import pl.piomin.samples.springboot.kubernetes.domain.Gender;
import pl.piomin.samples.springboot.kubernetes.domain.Person;
import pl.piomin.samples.springboot.kubernetes.domain.PersonV2;
import pl.piomin.samples.springboot.kubernetes.repository.PersonRepository;
import pl.piomin.samples.springboot.kubernetes.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persons")
public class PersonController {

	private PersonRepository repository;
	private PersonService service;

	PersonController(PersonRepository repository, PersonService service) {
		this.repository = repository;
		this.service = service;
	}

	@PostMapping
	public Person add(@RequestBody Person person) {
		return repository.save(person);
	}

	@PostMapping("/random")
	public Set<Person> add() {
		Person p1 = new Person();
		p1.setAge(1);
		p1.setFirstName("X");
		p1.setLastName("X");
		p1.setGender(Gender.MALE);
		Person p2 = new Person();
		p2.setAge(2);
		p2.setFirstName("Y");
		p2.setLastName("Y");
		p2.setGender(Gender.FEMALE);
		return service.doIt(p1, p2);
	}

	@PutMapping
	public Person update(@RequestBody Person person) {
		return repository.save(person);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") String id) {
		repository.deleteById(id);
	}

	@GetMapping
	public Iterable<Person> findAll() {
		return repository.findAll();
	}

	@GetMapping("/{id}")
	public Optional<Person> findById(@PathVariable("id") String id) {
		return repository.findById(id);
	}

	@GetMapping("/v2/{id}")
	public PersonV2 findByIdV2(@PathVariable("id") String id) {
		Person p = repository.findById(id).orElseThrow();
		PersonV2 personV2 = new PersonV2();
		personV2.setAge(p.getAge());
		personV2.setGender(p.getGender());
		personV2.setName(p.getFirstName() + " " + p.getLastName());
		personV2.setId(p.getId());
		return personV2;
	}

	@GetMapping("/first-name/{firstName}/last-name/{lastName}")
	public Set<Person> findByFirstNameAndLastName(@PathVariable("firstName") String firstName,
			@PathVariable("lastName") String lastName) {
		return repository.findByFirstNameAndLastName(firstName, lastName);
	}

	@GetMapping("/age-greater-than/{age}")
	public Set<Person> findByAgeGreaterThan(@PathVariable("age") int age) {
		return repository.findByAgeGreaterThan(age);
	}

	@GetMapping("/age/{age}")
	public Set<Person> findByAge(@PathVariable("age") int age) {
		return repository.findByAge(age);
	}

}
