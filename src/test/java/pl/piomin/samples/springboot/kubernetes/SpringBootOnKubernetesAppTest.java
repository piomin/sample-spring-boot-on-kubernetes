package pl.piomin.samples.springboot.kubernetes;

import org.springframework.boot.SpringApplication;

public class SpringBootOnKubernetesAppTest {

    public static void main(String[] args) {
        SpringApplication.from(SpringBootOnKubernetesApp::main)
                .with(MongoDBContainerDevMode.class)
                .run(args);
    }

}
