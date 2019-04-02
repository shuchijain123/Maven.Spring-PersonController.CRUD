package io.zipcoder.crudapp.controllers;



import io.zipcoder.crudapp.models.Person;
import io.zipcoder.crudapp.repositories.PersonRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping
public class PersonController {


    private PersonRepository repository;

    public PersonController(PersonRepository repository) {
        this.repository = repository;
    }




    List<Person> myList = new ArrayList<>();


    @PostMapping("/people/")
    public ResponseEntity<Person> createPerson( @RequestBody Person P){


        return new ResponseEntity<Person>(repository.save(P), HttpStatus.CREATED);


    }

  @GetMapping("/people/{id}")
    public ResponseEntity<Person> getPerson( @PathVariable Long id){

      return new ResponseEntity<Person>(repository.findById(id).get(), HttpStatus.OK);

    }


    @GetMapping("/people/")
    public ResponseEntity<Iterable<Person>> getPersonList(){

        return new ResponseEntity<Iterable<Person>>(repository.findAll(), HttpStatus.OK);

    }


    @DeleteMapping("/people/{id}")
    public ResponseEntity<Boolean> deletePerson(@PathVariable Long id) {


         return new ResponseEntity<Boolean>(delete(id), HttpStatus.OK);


        }

    @PutMapping("/people/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person p) {


        Person person = repository.findById(id).get();
        person.setFirstName(p.getFirstName());
        person.setLastName(p.getLastName());

        return new ResponseEntity<Person>(repository.save(person),HttpStatus.CREATED);


    }





    public Boolean delete(Long id) {
        repository.deleteById(id);
        return true;
    }

}



