package com.lcclockingsystem.sbcrud.clocking;

import com.lcclockingsystem.sbcrud.users.User;
import com.lcclockingsystem.sbcrud.users.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
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
    private UserRepository userRepository;

    // get all clocking records matching a user id
    @GetMapping("/all/{user_id}")
    public ResponseEntity<List<ClockingRecord>> getClockingRecords(@PathVariable("user_id") Integer user_id){
        return new ResponseEntity<>(clockingService.getClockingRecordsById(user_id), OK);
    }

    // add a clocking record
//    @PostMapping("/add")
//    public ResponseEntity<ClockingRecord> add(@RequestBody ClockingRecord record) throws IllegalAccessException {
//        return new ResponseEntity<>(clockingService.add(record), CREATED);
//    }

    @PostMapping("/add")
    ClockingRecord add(
            @RequestBody ClockingRecord clockingRecordRequest
    ) throws Exception {

        ClockingRecord clockingRecord = clockingRecordRequest;
        clockingRecord.setClockOut(null);


        // Find the user from the repository based on the provided user_id
        User user = userRepository.findById(clockingRecordRequest.getU_id())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        // Set the user on the clocking record
        clockingRecordRequest.setUser(user);

        // Save the clocking record
        return  clockingRepository.save(clockingRecord);


    }



}
