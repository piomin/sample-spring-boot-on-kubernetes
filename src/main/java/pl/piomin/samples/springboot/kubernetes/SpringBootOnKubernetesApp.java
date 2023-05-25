package pl.piomin.samples.springboot.kubernetes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import pl.piomin.samples.springboot.kubernetes.domain.Gender;
import pl.piomin.samples.springboot.kubernetes.domain.Person;
import pl.piomin.samples.springboot.kubernetes.repository.PersonRepository;

@SpringBootApplication
@EnableMongoRepositories
public class SpringBootOnKubernetesApp implements ApplicationListener<ApplicationReadyEvent> {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootOnKubernetesApp.class, args);
    }

    @Autowired
    PersonRepository repository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        if (repository.count() == 0) {
            repository.save(new Person(null, "XXX", "FFF", 20, Gender.MALE));
            repository.save(new Person(null, "AAA", "EEE", 30, Gender.MALE));
            repository.save(new Person(null, "ZZZ", "DDD", 40, Gender.FEMALE));
            repository.save(new Person(null, "BBB", "CCC", 50, Gender.MALE));
            repository.save(new Person(null, "YYY", "JJJ", 60, Gender.FEMALE));
        }
    }
}
