package ch.keepcalm.react.spring;

import ch.keepcalm.react.spring.domain.Person;
import ch.keepcalm.react.spring.repository.PersonRespository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RequestPredicates.method;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@SpringBootApplication
@EnableReactiveMongoRepositories
//@EnableWebFluxSecurity
public class ReactiveSpringPersonApplication {
    @Bean
    RouterFunction<?> routes(PersonRespository personRespository) {
        return nest(path("/person"),
                route(RequestPredicates.GET("/{id}"),
                        request -> ok().body(personRespository.findById(request.pathVariable("id")), Person.class))
                        .andRoute(method(HttpMethod.POST),
                                request -> {
                                    personRespository.insert(request.bodyToMono(Person.class)).subscribe();
                                    return ok().build();
                                })
        );
    }



//    @Bean
//    UserDetailsRepository userDetailsRepository() {
//        UserDetails tom = withUsername("tom").password("password").roles("USER").build();
//        UserDetails harry = withUsername("harry").password("password").roles("USER", "ADMIN").build();
//        return new MapUserDetailsRepository(tom, harry);
//    }


	public static void main(String[] args) {
		SpringApplication.run(ReactiveSpringPersonApplication.class, args);
	}
}
