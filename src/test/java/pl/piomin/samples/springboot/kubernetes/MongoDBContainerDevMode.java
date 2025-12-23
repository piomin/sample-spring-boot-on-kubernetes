package pl.piomin.samples.springboot.kubernetes;

import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.MongoDBContainer;

@TestConfiguration
public class MongoDBContainerDevMode {

    @Bean
    @ServiceConnection
    @RestartScope
    MongoDBContainer mongoDBContainer() {
        return new MongoDBContainer("mongo:8.2");
    }

}
