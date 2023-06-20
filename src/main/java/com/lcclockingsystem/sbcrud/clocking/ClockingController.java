/**
 * @author: Lazola Makubalo
 * */

package com.lcclockingsystem.sbcrud.clocking;

import com.lcclockingsystem.sbcrud.users.User;
import com.lcclockingsystem.sbcrud.users.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@AllArgsConstructor

// NB: before you push this to production, specify the exact origins
@CrossOrigin(origins = "*")
@RequestMapping("/clocking")
public class ClockingController {
    private ClockingService clockingService;
    private ClockingRepository clockingRepository;
    private UserRepository userRepository;

    // get all clocking records matching a user id
    @GetMapping("/all/user/{userId}")
    public ResponseEntity<List<ClockingRecord>> getClockingRecords(@PathVariable("userId") Integer userId){
        return new ResponseEntity<>(clockingService.getClockingRecordsById(userId), OK);
    }

    @PutMapping("/user/{userId}/clockout")
    public ResponseEntity<Boolean> clockOut(@PathVariable("userId") Integer userId){
        return new ResponseEntity<>(clockingService.clockOut(userId), OK);
    }

    @PostMapping("/add")
    // add a clocking record and set the user of the clocking record
    ClockingRecord add(@RequestBody ClockingRecord clockingRecordRequest) throws Exception {
        clockingRecordRequest.setClockOut(null);
        // Find the user from the repository based on the provided user_id
        User user = userRepository.findById(clockingRecordRequest.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        // Set the user on the clocking record
        clockingRecordRequest.setUser(user);

        // Save the clocking record
        return  clockingRepository.save(clockingRecordRequest);
    }

    // update a clocking record
    @PutMapping("/update")
    public ResponseEntity<ClockingRecord> update(@RequestBody ClockingRecord clockingRecord){
        return new ResponseEntity<>(clockingService.update(clockingRecord), CREATED);
    }


}
