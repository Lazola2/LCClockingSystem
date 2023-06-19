/**
 * @author: Lazola Makubalo
 * */

package com.lcclockingsystem.sbcrud.users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
