/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

    public final List<Person> persons = new ArrayList<>();

    public PersonController() {
        persons.add(new Person(01, "Giovanni"));
        persons.add(new Person(02, "Luigi"));
        persons.add(new Person(03, "Juan"));
        persons.add(new Person(04, "Juanita"));
        persons.add(new Person(05, "Petunia"));
        persons.add(new Person(06, "Giuseppe"));
    }
    
    public Person findById(int id) {
        for(Person person: persons) {
            if(person.getId() == id)
                return person;
        }
        
        return null;
    }

    public Person findPersonByName(String name) {
        for(Person person: persons) {
            if(person.getName().equals(name))
                return person;
        }
        return null;
    }
    
    @GetMapping
    public List<Person> getPersons() {
        return persons;
    }
    
    @GetMapping("/{id}")
    public Person getPersons(@PathVariable("id") int id) {
        return persons.stream()
        .filter(p -> p.getId() == id).findFirst().orElse(null);
    }
    
    
    @PostMapping(consumes="application/json")
    public ResponseEntity<String> createPerson(@RequestBody Person person) {
        persons.add(person);
        return new ResponseEntity<>("A new person was created", HttpStatus.CREATED);
    }
        
    @PutMapping("/{id}")
    public ResponseEntity<String> updatePerson(@PathVariable int id, @RequestParam String name) {
        Person person = findById(id);
        if (person == null) {
            return new ResponseEntity<>("The requested person is not found", HttpStatus.NOT_FOUND); //or GONE
        }
        
        person.setName(name);
    
        return new ResponseEntity<>("Person's name was updated", HttpStatus.OK);
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable int id) {
        Person person = findById(id);
        if (person == null) {
            return new ResponseEntity<>("The requested person is not found", HttpStatus.GONE);
        }
        persons.remove(person);
        return new ResponseEntity<>("The requested person was removed", HttpStatus.OK);
    }
}

