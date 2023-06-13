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
    public ClockingDetail add(ClockingDetail record) throws IllegalAccessException {
        return clockingRepository.save(record);
    }

    @Override
    public List<ClockingDetail> getClockingRecordsById(Integer id) {
        List<ClockingDetail> allClockingRecords = clockingRepository.findAll();
        List<ClockingDetail> recordsById = new ArrayList<>();

        for (ClockingDetail record : allClockingRecords) {
            if (record.getUserId() == id) {
                recordsById.add(record);
            }
        }
        return recordsById;
    }

    @Override
    public ClockingDetail update(ClockingDetail record) {
        return clockingRepository.save(record);
    }

    @Override
    public void delete(Integer id) {
        clockingRepository.deleteById(id);
    }
}
