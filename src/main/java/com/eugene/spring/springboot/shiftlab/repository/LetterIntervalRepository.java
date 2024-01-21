package com.eugene.spring.springboot.shiftlab.repository;


import com.eugene.spring.springboot.shiftlab.model.DigitInterval;
import com.eugene.spring.springboot.shiftlab.model.LetterInterval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LetterIntervalRepository extends JpaRepository<LetterInterval, Integer> {
    @Query(value = "SELECT * FROM LETTERINTERVALS WHERE _start= (SELECT MIN(_start) FROM LETTERINTERVALS) AND _end = (SELECT MIN(_end ) FROM LETTERINTERVALS) LIMIT 1;", nativeQuery = true)
    LetterInterval findMinInterval();
}
