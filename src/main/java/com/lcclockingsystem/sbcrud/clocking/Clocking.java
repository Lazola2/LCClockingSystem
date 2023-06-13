package com.lcclockingsystem.sbcrud.clocking;

import com.lcclockingsystem.sbcrud.users.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name="clocking")
@NoArgsConstructor
public class Clocking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cid;
    private LocalTime clockIn;
    private LocalTime clockOut;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name="userId", referencedColumnName = "id")
    private User user;
}
