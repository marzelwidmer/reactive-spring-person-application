package ch.keepcalm.react.spring.repository;

import ch.keepcalm.react.spring.domain.Person;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface PersonRespository  extends ReactiveMongoRepository<Person, String> {
    Flux<Person> findByName(String name);
}