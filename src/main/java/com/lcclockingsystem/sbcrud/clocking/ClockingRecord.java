/**
 * @author: Lazola Makubalo
 * */

package com.lcclockingsystem.sbcrud.clocking;

import com.lcclockingsystem.sbcrud.users.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "clocking_records")
public class ClockingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clocking_id;

    // clock in time should be generated as current time
    private LocalTime clock_in = LocalTime.now();

    // clock out time should not be generated as current time
    private LocalTime clock_out;
    private LocalDate date = LocalDate.now();

    private Integer u_id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;
}