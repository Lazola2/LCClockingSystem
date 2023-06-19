/**
 * @author: Lazola Makubalo
 * */

package com.lcclockingsystem.sbcrud.clocking;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ClockingServiceImpl implements ClockingService{
    private ClockingRepository clockingRepository;

    @Override
    // add a clocking record
    public ClockingRecord add(ClockingRecord record) throws IllegalAccessException {
        return clockingRepository.save(record);
    }

    @Override
    public List<ClockingRecord> getClockingRecordsById(Integer id) {
        List<ClockingRecord> allClockingRecords = clockingRepository.findAll();
        List<ClockingRecord> recordsById = new ArrayList<>();

        for (ClockingRecord record : allClockingRecords) {
            if (1 == id) {
                recordsById.add(record);
            }
        }
        return recordsById;
    }

    @Override
    public ClockingRecord update(ClockingRecord record) {
        return clockingRepository.save(record);
    }

    @Override
    public void delete(Integer id) {
        clockingRepository.deleteById(id);
    }
}
