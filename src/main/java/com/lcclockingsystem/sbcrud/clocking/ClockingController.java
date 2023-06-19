/**
 * @author: Lazola Makubalo
 * */

package com.lcclockingsystem.sbcrud.clocking;

import com.lcclockingsystem.sbcrud.users.User;
import com.lcclockingsystem.sbcrud.users.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static org.springframework.http.HttpStatus.OK;

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
    @GetMapping("/all/{user_id}")
    public ResponseEntity<List<ClockingRecord>> getClockingRecords(@PathVariable("user_id") Integer user_id){
        return new ResponseEntity<>(clockingService.getClockingRecordsById(user_id), OK);
    }

    @PostMapping("/add")
    // add a clocking record and set the user of the clocking record
    ClockingRecord add(@RequestBody ClockingRecord clockingRecordRequest) throws Exception {

        // the clock out should not be available during clock in
        clockingRecordRequest.setClockOut(null);

        // Find the user from the repository based on the provided user_id
        User user = userRepository.findById(clockingRecordRequest.getU_id())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        // Set the user on the clocking record
        clockingRecordRequest.setUser(user);

        // Save the clocking record
        return  clockingRepository.save(clockingRecordRequest);
    }

}
