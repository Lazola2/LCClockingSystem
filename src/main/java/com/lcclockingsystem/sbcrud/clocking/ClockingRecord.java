/**
 * @author: Lazola Makubalo
 * */

package com.lcclockingsystem.sbcrud.clocking;

import com.lcclockingsystem.sbcrud.users.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
    private Integer clockingId;

    // clock in time should be generated as current time
    private LocalTime clockIn = LocalTime.now();

    // clock out time should not be generated as current time
    private LocalTime clockOut;
    private LocalDate date = LocalDate.now();

    private Integer userId;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "userId")
    private User user;
}
