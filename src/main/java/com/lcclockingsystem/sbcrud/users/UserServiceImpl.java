package com.lcclockingsystem.sbcrud.users;

import com.lcclockingsystem.sbcrud.clocking.ClockingRecord;
import com.lcclockingsystem.sbcrud.clocking.ClockingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    private ClockingRepository clockingRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ClockingRepository clockingRepository){
        this.userRepository = userRepository;
        this.clockingRepository = clockingRepository;
    }

    @Override
    public User add(User user) throws IllegalAccessException {
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null){
            throw new IllegalAccessException("User not found!");
        }
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public Optional<User> getById(Integer id) throws Exception {
        return userRepository.findById(id);
    }

    public void saveClockingRecord(ClockingRecord clockingRecord){
        clockingRepository.save(clockingRecord);
    }
}
