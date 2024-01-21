package com.eugene.spring.springboot.shiftlab.repository;


import com.eugene.spring.springboot.shiftlab.model.DigitInterval;

import com.eugene.spring.springboot.shiftlab.model.LetterInterval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Repository;

@Repository
public interface DigitIntervalRepository extends JpaRepository<DigitInterval, Long>
{
    @Query(value = "SELECT * FROM DIGITINTERVALS WHERE _start= (SELECT MIN(_start) FROM DIGITINTERVALS) AND _end = (SELECT MIN(_end ) FROM DIGITINTERVALS) LIMIT 1;", nativeQuery = true)
    DigitInterval findMinInterval();

}
