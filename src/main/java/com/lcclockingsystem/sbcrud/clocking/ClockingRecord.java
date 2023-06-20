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
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Data
@Entity
@Table(name = "clocking_records")
public class ClockingRecord implements TimeFormatter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clockingId;

    // method to format the time
    @Override
    public LocalTime formatTime(LocalTime time){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = time.format(formatter);
        return LocalTime.parse(formattedTime, formatter);
    }

    // method formatTime() is defined above
    private LocalTime clockIn = formatTime(LocalTime.now(
            ZoneId.of("Africa/Johannesburg")
    ));

    // clock out time should not be generated as current time
    private LocalTime clockOut;
    private LocalDate date = LocalDate.now();

    private Integer userId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id", referencedColumnName = "userId")
    private User user;


}
