package com.eugene.spring.springboot.shiftlab.controller;

import com.eugene.spring.springboot.shiftlab.instruments.MergeHelperForDigits;
import com.eugene.spring.springboot.shiftlab.instruments.MergeHelperForStrings;
import com.eugene.spring.springboot.shiftlab.model.DigitInterval;
import com.eugene.spring.springboot.shiftlab.model.LetterInterval;
import com.eugene.spring.springboot.shiftlab.repository.DigitIntervalRepository;
import com.eugene.spring.springboot.shiftlab.repository.LetterIntervalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/intervals")
public class IntervalController
{
    private DigitIntervalRepository digitIntervalRepository;
    private LetterIntervalRepository letterIntervalRepository;


    @Autowired
    public void setDigitIntervalRepository(DigitIntervalRepository digitIntervalRepository) {
        this.digitIntervalRepository = digitIntervalRepository;
    }
    @Autowired
    public void setLetterIntervalRepository(LetterIntervalRepository letterIntervalRepository) {
        this.letterIntervalRepository = letterIntervalRepository;
    }

    @PostMapping(value = "/merge")
    public ResponseEntity<?> mergeIntervals(@RequestBody List<LetterInterval> intervals, @RequestParam("kind") String kind)
    {


        if (kind.equals("digits"))
        {
            MergeHelperForDigits mergeHelper;
            try
            {
                mergeHelper = new MergeHelperForDigits(intervals);
            }
            catch (Exception e){return ResponseEntity.badRequest().body("Invalid kind parameter value\n\n\n"+info);}

            if ( !mergeHelper.isAlph() && mergeHelper.isValid())
            {
                digitIntervalRepository.saveAll(mergeHelper.mergeIntervals());
                return ResponseEntity.ok("Merging intervals for digits");
            } else {
                return ResponseEntity.badRequest().body("Invalid kind parameter value\n\n\n"+info);
            }
        }



        else if (kind.equals("letters"))
        {
            MergeHelperForStrings mergeHelper = new MergeHelperForStrings(intervals);
            if (!mergeHelper.isNumeric() && mergeHelper.isAlph() && mergeHelper.isValid()) {
                letterIntervalRepository.saveAll(mergeHelper.mergeIntervals());
                return ResponseEntity.ok("Merging intervals for letters");
            } else {
                return ResponseEntity.badRequest().body("Invalid type parameter value\n\n\n"+info);
            }
        }
        else {
            return ResponseEntity.badRequest().body("Invalid kind parameter value\n\n\n"+info);
        }
    }


    @GetMapping("/min")
    public ResponseEntity<?> getMinInterval(@RequestParam("kind") String kind) {
        if (kind.equals("digits")) {
            DigitInterval minInterval = digitIntervalRepository.findMinInterval();
            if (minInterval != null) {
                return ResponseEntity.ok(minInterval);
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        else if (kind.equals("letters")) {
            LetterInterval minInterval = letterIntervalRepository.findMinInterval();
            if (minInterval != null) {
                return ResponseEntity.ok(minInterval);
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        else {
            return ResponseEntity.badRequest().body("Invalid kind parameter value\n\n\n"+info);
        }
    }


    @GetMapping("/letters")
    public List<LetterInterval> getAllIntervalsOfLetters()
    {
        return letterIntervalRepository.findAll();
    }

    @GetMapping("/digits")
    public List<DigitInterval> getAllIntervalsOfDigits()
    {
        return digitIntervalRepository.findAll();
    }

    private final String info = """
            If you see this, that means you have a problem. This is an example of how you must write a request.
            If you want to ADD a list of Letter Intervals, you must write this: POST http://localhost:8080/api/v1/intervals/merge?kind=letters
            [
                {
                    "start" : "a",
                    "end" : "b"
                },
                {
                    "start" : "a",
                    "end" : "z"
                }
            ]
            
            If you want to ADD a list of Digit Intervals, you must write this: POST http://localhost:8080/api/v1/intervals/merge?kind=digits
            [
                {
                    "start" : 1,
                    "end" : 2
                },
                {
                    "start" : 1,
                    "end" : 100
                }
            ]
            
            If you want to get the MINIMUM of a Letter Interval, you must write this: GET http://localhost:8080/api/v1/intervals/min?kind=letters
            If you want to get the MINIMUM of a Digit Interval, you must write this: GET http://localhost:8080/api/v1/intervals/min?kind=digits
            
            If you want to get ALL the list of Letter Intervals, you must write this: GET http://localhost:8080/api/v1/intervals/letters
            If you want to get ALL the list of Digit Intervals, you must write this: GET http://localhost:8080/api/v1/intervals/digits
            """;
}