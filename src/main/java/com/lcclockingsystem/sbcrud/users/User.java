/**
 * @author: Lazola Makubalo
 * */

package com.lcclockingsystem.sbcrud.users;

import com.lcclockingsystem.sbcrud.clocking.ClockingRecord;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name="user")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String firstname;
    private String lastname;

    @Column(unique = true)
    private String email;
    private String role = "user";
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ClockingRecord> clockingRecords;
}
