/**
 * @author: Lazola Makubalo
 * */

package com.lcclockingsystem.sbcrud.clocking;

import org.springframework.data.jpa.repository.JpaRepository;
public interface ClockingRepository  extends JpaRepository<ClockingRecord, Integer> {
}
