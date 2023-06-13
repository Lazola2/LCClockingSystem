package com.lcclockingsystem.sbcrud.clocking;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@AllArgsConstructor

// before you push this to production, specify the exact origins
@CrossOrigin(origins = "*")
@RequestMapping("/clocking")
public class ClockingController {
    private ClockingService clockingService;
    private ClockingRepository clockingRepository;

    // get all clocking records matching a user id
    @GetMapping("/all/{user_id}")
    public ResponseEntity<List<ClockingRecord>> getClockingRecords(@PathVariable("user_id") Integer user_id){
        return new ResponseEntity<>(clockingService.getClockingRecordsById(user_id), OK);
    }

    // add a clocking record
    @PostMapping("/add")
    public ResponseEntity<ClockingRecord> add(@RequestBody ClockingRecord record) throws IllegalAccessException {
        return new ResponseEntity<>(clockingService.add(record), CREATED);
    }



}
