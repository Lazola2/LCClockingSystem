package com.lcclockingsystem.sbcrud.users;

import com.lcclockingsystem.sbcrud.clocking.ClockingRecord;
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
    private Integer user_id;
    private String firstname;
    private String lastname;

    @Column(unique = true)
    private String email;
    private String role = "user";
    private String password;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "f_key", referencedColumnName = "user_id")
    private List<ClockingRecord> clockingRecords;

}


