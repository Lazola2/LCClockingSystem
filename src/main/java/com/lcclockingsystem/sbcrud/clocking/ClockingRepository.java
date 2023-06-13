package com.lcclockingsystem.sbcrud.clocking;

import org.springframework.data.jpa.repository.JpaRepository;
public interface ClockingRepository  extends JpaRepository<ClockingDetail, Integer> {
}
