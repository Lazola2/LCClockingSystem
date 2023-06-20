package com.lcclockingsystem.sbcrud.clocking;

import java.time.LocalTime;

public interface TimeFormatter {
    LocalTime formatTime(LocalTime time);
}
