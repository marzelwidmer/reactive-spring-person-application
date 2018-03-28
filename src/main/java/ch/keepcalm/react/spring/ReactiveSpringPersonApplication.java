package ch.keepcalm.react.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;

@SpringBootApplication
@EnableReactiveMongoRepositories
@EnableWebFluxSecurity
public class ReactiveSpringPersonApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveSpringPersonApplication.class, args);
	}
}
