package com.lcclockingsystem.sbcrud.clocking;

import com.lcclockingsystem.sbcrud.users.User;
import com.lcclockingsystem.sbcrud.users.UserRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Data
@Entity
@Table(name = "clocking_records")
public class ClockingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clocking_id;

    // clock in time should be generated as current time
    private LocalTime clockIn = LocalTime.now();

    // clock out time should not be generated as current time
    private LocalTime clockOut;
    private LocalDate date = LocalDate.now();

    private Integer u_id;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;
}
