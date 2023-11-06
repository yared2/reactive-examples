package guru.springframework.reactiveexamples;

import guru.springframework.reactiveexamples.domain.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jt on 2/27/21.
 */
public class PersonRepositoryImpl implements PersonRepository {

    List<Person> personss = new ArrayList();
    Person michael = new Person(1, "Michael", "Weston");

    Person fiona = new Person(2, "Fiona", "Glenanne");
    Person sam = new Person(3, "Sam", "Axe");
    Person jesse = new Person(3, "Jesse", "Porter");

    @Override
    public Mono<Person> getById(Integer id) {
        return Mono.just(michael);
    }

    @Override
    public Mono<Person> getByIdGiven(Integer id) {
       Person person=Person.builder().build();
        personss.add(michael);
        personss.add(fiona);
        personss.add(sam);
        personss.add(jesse);

        for (Person x : personss) {
           boolean available = x.getId() == id;
           if (available){
               person=x;
               return Mono.just(person);
           }

        }

        return Mono.just(person);
    }


    @Override
    public Flux<Person> findAll() {
        return Flux.just(michael,fiona,sam,jesse);
    }

    @Override
    public Mono<Person> findAllbyId(Integer id) {
        Mono<Person> filter = findAll().filter(person -> person.getId() == id).next();
        return (filter!=null)?filter: Mono.just(Person.builder().build());
    }
}
