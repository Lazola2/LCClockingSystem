/**
 * @author: Lazola Makubalo
 * */

package com.lcclockingsystem.sbcrud.clocking;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClockingService {
    // add a clocking record
    ClockingRecord add(ClockingRecord record) throws IllegalAccessException;

    // get all clocking records
    List<ClockingRecord> getClockingRecordsById(Integer userId);

    // update a clocking record
    ClockingRecord update(ClockingRecord record);

    // clock-out a user
    boolean clockOut(Integer id);
}
