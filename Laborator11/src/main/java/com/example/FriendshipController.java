package com.example;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;


@RestController
@RequestMapping(path="/friendship")
public class FriendshipController {

    private Friendship friendship= new Friendship();
    private Person person = new Person();

    @PostMapping("/")
    public ResponseEntity<Friendship> createFriendship(@RequestBody Friendship friendship)
    {
        if(friendship.getFriendship(friendship).isPresent())
            return ResponseEntity.badRequest().build();
        if(person.findUserByUserName(friendship.getUser1()).isEmpty() || userRepository.findUserByUserName(friendship.getUser2()).isEmpty())
            return ResponseEntity.notFound().build();

        Friendship createdFriendship = friendship.createFriendship(friendship);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{user1}/{user2}").buildAndExpand(friendship.getUser1(), friendship.getUser2()).toUri();

        return ResponseEntity.created(uri).body(createdFriendship);
    }

    @GetMapping("/all")
    public  ResponseEntity<List<Friendship>> getAllFriendships()
    {
        List<Friendship> foundFriendships = friendship.getAllFriendships();
        if(foundFriendships.size() == 0 || foundFriendships == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(foundFriendships);
    }

    @GetMapping("/{username1}/{username2}")
    public ResponseEntity<Friendship> getFriendship(@PathVariable String username1, @PathVariable String username2)
    {
        person.findUserByUserName(username1).orElseThrow(() -> new ResourceNotFoundException("Not found user with name "+username1));
        person.findUserByUserName(username2).orElseThrow(() -> new ResourceNotFoundException("Not found user with name  "+username2));

        User user1 = new User();
        user1.setUserName(username1);
        User user2 = new User();
        user2.setUserName(username2);

        Optional<Friendship> friendshipOptional = friendshipRepository.getFriendship(user1,user2);

        if(friendshipOptional.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(friendshipOptional.get());
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<User>> getFriendsOfUser(@PathVariable String username)
    {
        userRepository.findUserByUserName(username).orElseThrow(() -> new ResourceNotFoundException("Not found friends of user "+username)); /// Doar aici am tratat exceptia momentan dar merge :D

        User user = new User();
        user.setUserName(username);

        List<User> foundFriends = friendshipRepository.getFriendsOfUser(user);
        if(foundFriends.size() == 0 || foundFriends == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(foundFriends);
    }

    @GetMapping("/mostPopularUsers/{k}")
    public ResponseEntity<List<User>> getMostPopularUsers(@PathVariable Integer k)
    {
        if(k<=0)
            return ResponseEntity.badRequest().build();

        List<User> foundUsers = friendshipRepository.getTheMostPopularUsers(k);

        if(foundUsers.size() == 0 || foundUsers == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(foundUsers);
    }

//    @GetMapping("/leastPopularUsers/{k}")
//    public ResponseEntity<List<User>> getLeastPopularUsers(@PathVariable Integer k)
//    {
//        if(k<=0)
//            return ResponseEntity.badRequest().build();
//
//        List<User> foundUsers = friendshipRepository.getTheLeastPopularUsers(k);
//
//        if(foundUsers.size() == 0 || foundUsers == null)
//            return ResponseEntity.notFound().build();
//
//        return ResponseEntity.ok(foundUsers);
//    }
//
//    @DeleteMapping("/{username1}/{username2}")
//    public ResponseEntity<Friendship> deleteFriendship(@PathVariable String username1, @PathVariable String username2)
//    {
//        if(userRepository.findUserByUserName(username1).isEmpty() || userRepository.findUserByUserName(username2).isEmpty())
//            return ResponseEntity.badRequest().build();                                                                 /////poate o fi mai bine cu notFound
//
//        User user1 = new User();
//        user1.setUserName(username1);
//        User user2 = new User();
//        user2.setUserName(username2);
//
//        Optional<Friendship> friendshipOptional = friendshipRepository.getFriendship(user1,user2);
//
//        if(friendshipOptional.isEmpty())
//            return ResponseEntity.notFound().build();
//
//        friendshipRepository.deleteFriendship(user1,user2);
//        return ResponseEntity.noContent().build();
//    }

}