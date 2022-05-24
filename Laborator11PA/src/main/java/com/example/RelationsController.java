package com.example;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path="/relationship")
public class RelationsController {
    public RelationsController(){
        Relations f1 = new Relations(1,"Giovanni", "Juanita");
        relationshipRepo.createRelationship(f1);

        Relations f2 = new Relations(2,"Juan", "Petunia");
        relationshipRepo.createRelationship(f2);

        Relations f3 = new Relations(3,"Juanita", "Giuseppe");
        relationshipRepo.createRelationship(f3);

        Relations f4 = new Relations(4,"Luigi", "Juanita");
        relationshipRepo.createRelationship(f4);
    }
    private RelationsRepository relationshipRepo = new RelationsRepository();
    private PersonController personController = new PersonController();

    @PostMapping("/")
    public ResponseEntity<String> createRelationship(@RequestBody Relations relationship)
    {
        if(relationshipRepo.isPresent(relationship))
            return ResponseEntity.badRequest().build();
        if(personController.findPersonByName(relationship.getPerson1())==null || personController.findPersonByName(relationship.getPerson2())==null)
            return ResponseEntity.notFound().build();

        relationshipRepo.createRelationship(relationship);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{user1}/{user2}").buildAndExpand(relationship.getPerson1(), relationship.getPerson2()).toUri();

        return new ResponseEntity<>("Realation created successfully", HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public  ResponseEntity<List<Relations>> getAllRelations()
    {
        List<Relations> foundRelations = relationshipRepo.getAllRelations();
        if(foundRelations.size() == 0 || foundRelations== null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(foundRelations);
    }

    @GetMapping("/{username1}/{username2}")
    public ResponseEntity<Relations> getRelationship(@PathVariable String username1, @PathVariable String username2)
    {
        Relations relationshipOp = relationshipRepo.getRelationship(username1,username2);

        if(relationshipOp==null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(relationshipOp);
    }

    @GetMapping("/mostPopularUsers/{k}")
    public ResponseEntity<List<String>> getMostPopularUsers(@PathVariable Integer k)
    {
        if(k<=0)
            return ResponseEntity.badRequest().build();

        List<String> foundUsers = relationshipRepo.getTheMostPopularUsers(k);

        if(foundUsers.size() == 0 || foundUsers == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(foundUsers);
    }

    @DeleteMapping("/{username1}/{username2}")
    public ResponseEntity<Relations> deleteRelationship(@PathVariable String username1, @PathVariable String username2)
    {
        if(personController.findPersonByName(username1)==null || personController.findPersonByName(username2)==null)
            return ResponseEntity.badRequest().build();

        Relations relationshipOp = relationshipRepo.getRelationship(username1,username2);

        if(relationshipOp==null)
            return ResponseEntity.notFound().build();

        relationshipRepo.deleteRelationship(username1,username2);
        return ResponseEntity.noContent().build();
    }
}