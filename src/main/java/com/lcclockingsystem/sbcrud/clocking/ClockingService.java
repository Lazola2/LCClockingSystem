package com.lcclockingsystem.sbcrud.clocking;

import java.util.List;

public interface ClockingService {
    // add a user
    ClockingDetail add(ClockingDetail record) throws IllegalAccessException;

    // get all clocking records
    List<ClockingDetail> getClockingRecordsById(Integer userId);

    // update a clocking record
    ClockingDetail update(ClockingDetail record);

    // delete a clocking record
    void delete(Integer id);
}
