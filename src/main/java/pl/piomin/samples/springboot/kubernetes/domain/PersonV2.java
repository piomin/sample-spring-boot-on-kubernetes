package pl.piomin.samples.springboot.kubernetes.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "person")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonV2 {

	@Id
	private String id;
	private String name;
	private int age;
	private Gender gender;

}
