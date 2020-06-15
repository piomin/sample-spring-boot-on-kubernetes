package pl.piomin.samples.springboot.kubernetes.domain;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "person")
@Getter
@Setter
public class Person {

	@Id
	private String id;
	private String firstName;
	private String lastName;
	private int age;
	private Gender gender;

}
