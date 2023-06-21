/**
 * @author: Lazola Makubalo
 * */

package com.lcclockingsystem.sbcrud.users;

import com.fasterxml.jackson.databind.util.BeanUtil;
import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RestController
@AllArgsConstructor

// before you push this to production, specify the origins
@CrossOrigin(origins = "*")

@RequestMapping("/users")
public class UserController {
    private UserService userService;
    private PasswordService passwordService;
    private UserRepository userRepository;

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
        String hashedPassword = passwordService.hashPassword(user.getPassword());
        user.setPassword(hashedPassword);
        return new ResponseEntity<>(userService.add(user), CREATED);
    }

    // update the user using patch to update partially
    @PatchMapping("/update/user/{userId}")
    public ResponseEntity<User> update(@PathVariable("userId") Integer userId, @RequestBody User payload){
        User existingUser = userRepository.findById(userId).orElse(null);
        if (existingUser == null) return ResponseEntity.notFound().build();

        // copy non-null properties from updatedUser to existingUser
        BeanUtils.copyProperties(payload, existingUser, getNullPropertyNames(payload));
        User savedUser = userRepository.save(existingUser);
        return ResponseEntity.ok(savedUser);
    }

    // Helper method to get null property names
    private static String[] getNullPropertyNames(Object source){
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null)
                emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    // update a user put to update all details
    @PutMapping("/update")
    public ResponseEntity<User> update(@RequestBody User user){
        return new ResponseEntity<>(userService.update(user), CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginData loginData){
        String email = loginData.getEmail();
        String password = loginData.getPassword();
        User user = userRepository.findByEmail(email);

        if (user != null && BCrypt.checkpw(password, user.getPassword()))
            return new ResponseEntity<>(user, OK);
        return ResponseEntity.status(UNAUTHORIZED).body(null);
    }

    // delete a user
    @DeleteMapping("/user/{id}")
    public void delete(@PathVariable("id") Integer id){
        userService.delete(id);
    }

    // delete all users
    @DeleteMapping("/all")
    public void deleteAll(){
        userService.deleteAll();
    }

}
