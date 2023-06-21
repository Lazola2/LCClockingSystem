/**
 * @author: Lazola Makubalo
 * */

package com.lcclockingsystem.sbcrud.clocking;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ClockingServiceImpl implements ClockingService, TimeFormatter {
    private ClockingRepository clockingRepository;

    @Override
    // add a clocking record
    public ClockingRecord add(ClockingRecord record) {
        return clockingRepository.save(record);
    }

    @Override
    public List<ClockingRecord> getClockingRecordsById(Integer id) {
        List<ClockingRecord> allClockingRecords = clockingRepository.findAll();
        List<ClockingRecord> recordsById = new ArrayList<>();

        for (ClockingRecord record : allClockingRecords) {
            if (Objects.equals(record.getUserId(), id)) {
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
    public LocalTime formatTime(LocalTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = time.format(formatter);
        return LocalTime.parse(formattedTime, formatter);
    }

    @Override
    public boolean clockOut(Integer id) {
        try {
            // getting all the records matching the id
            List<ClockingRecord> matchingRecords = clockingRepository.findAll().stream()
                    .filter(record -> record.getUserId() == id)
                    .toList();

            // getting the last record from the matching records
            ClockingRecord lastRecord = matchingRecords.get(matchingRecords.size()-1);

            // updating the clock out time
            lastRecord.setClockOut( formatTime(LocalTime.now(ZoneId.of("Africa/Johannesburg"))));
            clockingRepository.save(lastRecord);

            // return true if update
            return true;
        } catch (Exception exception){
            // return false if not updated
            return  false;
        }
    }
}
