
package com.example;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persons")
public class PersonController {
    private final List<Person> persons = new ArrayList<>();
    public PersonController() {
        persons.add(new Person(1, "Mask"));
        persons.add(new Person(2, "Gloves"));
    }
    
    public Person findById(int id) {
        for(Person person: persons) {
            if(person.getId() == id)
                return person;
        }
        
        return null;
    }
    
    @GetMapping
    public List<Person> getPerson() {
        return persons;
    }
    
    @GetMapping("/{id}")
    public Person getPerson(@PathVariable("id") int id) {
        return persons.stream()
        .filter(p -> p.getId() == id).findFirst().orElse(null);
    }
    
    
        @PostMapping(consumes="application/json")
        public ResponseEntity<String> createPerson(@RequestBody Person person) {
            persons.add(person);
            return new ResponseEntity<>("Person created successfully", HttpStatus.CREATED);
        }
        
    @PutMapping("/{id}")
    public ResponseEntity<String> updatePerson(@PathVariable int id, @RequestParam String name) {
        Person person = findById(id);
        if (person == null) {
            return new ResponseEntity<>("Person not found", HttpStatus.NOT_FOUND); //or GONE
        }
        
        person.setName(name);
    
        return new ResponseEntity<>("Person updated successsfully", HttpStatus.OK);
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable int id) {
        Person person = findById(id);
        if (person == null) {
            return new ResponseEntity<>("Person not found", HttpStatus.GONE);
        }
        persons.remove(person);
        return new ResponseEntity<>("Person removed", HttpStatus.OK);
    }
}

