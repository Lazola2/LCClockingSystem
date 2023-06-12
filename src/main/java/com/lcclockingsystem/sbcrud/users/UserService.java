package com.lcclockingsystem.sbcrud.users;

import java.util.List;
import java.util.Optional;

public interface UserService {

    // add a user
    User add(User user);

    // get all users
    List<User> getUsers();

    // update a user
    User update(User user);

    // delete a user
    void delete(Integer id);

    // delete all users
    void deleteAll();

    // get a user by id
    Optional<User> getById(Integer id) throws Exception;
}
