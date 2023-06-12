package com.lcclockingsystem.sbcrud.users;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@AllArgsConstructor

// before you push this to production, specify the origins
@CrossOrigin(origins = "*")

@RequestMapping("/users")
public class UserController {
    private UserService userService;

    // get all users
    @GetMapping("/all")
    public ResponseEntity<List<User>> getUsers(){
        return new ResponseEntity<>(userService.getUsers(), OK);
    }

    // get a single user
    @GetMapping("/user/{id}")
    public Optional<User> getUser(@PathVariable("id") Integer id) throws Exception {
        return userService.getById(id);
    }

    // add a user
    @PostMapping("/add")
    public ResponseEntity<User> add(@RequestBody User user) throws IllegalAccessException {
        return new ResponseEntity<>(userService.add(user), CREATED);
    }

    // update a user
    @PutMapping("/update")
    public ResponseEntity<User> update(@RequestBody User user){
        return new ResponseEntity<>(userService.update(user), CREATED);
    }

    // delete a user
    @DeleteMapping("/user/{id}")
    public void delete(@PathVariable("id") Integer id){
        userService.delete(id);
    }

}
