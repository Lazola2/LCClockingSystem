/**
 * @author: Lazola Makubalo
 * */

package com.lcclockingsystem.sbcrud.clocking;

import java.util.List;

public interface ClockingService {
    // add a user
    ClockingRecord add(ClockingRecord record) throws IllegalAccessException;

    // get all clocking records
    List<ClockingRecord> getClockingRecordsById(Integer userId);

    // update a clocking record
    ClockingRecord update(ClockingRecord record);

    // delete a clocking record
    void delete(Integer id);
}
