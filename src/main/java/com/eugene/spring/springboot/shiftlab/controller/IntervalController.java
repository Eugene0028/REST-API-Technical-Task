package com.eugene.spring.springboot.shiftlab.controller;
import com.eugene.spring.springboot.shiftlab.instruments.MergeHelper;
import com.eugene.spring.springboot.shiftlab.model.DigitInterval;
import com.eugene.spring.springboot.shiftlab.model.LetterInterval;
import com.eugene.spring.springboot.shiftlab.repository.DigitIntervalRepository;
import com.eugene.spring.springboot.shiftlab.repository.LetterIntervalRepository;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * This class represents the controller for handling the requests related to intervals.
 * It provides endpoints for retrieving and manipulating intervals of letters and digits.
 */
@RestController
@RequestMapping("/api/v1/intervals")
public class IntervalController {

    @Autowired
    private DigitIntervalRepository digitIntervalRepository;

    @Autowired
    private LetterIntervalRepository letterIntervalRepository;

    /**
     * Retrieves a list of all intervals of letters.
     *
     * @return A list of LetterInterval objects representing the intervals of letters.
     */
    @GetMapping("/letters")
    public List<LetterInterval> getAllIntervalsOfLetters() {
        return letterIntervalRepository.findAll();
    }

    /**
     * Retrieves a list of all intervals of digits.
     *
     * @return A list of DigitInterval objects representing the intervals of digits.
     */
    @GetMapping("/digits")
    public List<DigitInterval> getAllIntervalsOfDigits() {
        return digitIntervalRepository.findAll();
    }

    /**
     * Retrieves the minimum interval for a given kind of intervals.
     *
     * @param kind The kind of intervals, either "digits" or "letters".
     * @return The minimum interval for the specified kind, or a response indicating if it was not found or if the kind parameter is invalid.
     */
    @GetMapping("/min")
    @Synchronized
    public ResponseEntity<Object> getMinInterval(@RequestParam("kind") String kind) {
        return switch (kind) {
            case "digits" -> {
                DigitInterval minDigitInterval = digitIntervalRepository.findMinInterval();
                yield minDigitInterval != null ? ResponseEntity.ok(minDigitInterval) : ResponseEntity.notFound().build();
            }
            case "letters" -> {
                LetterInterval minLetterInterval = letterIntervalRepository.findMinInterval();
                yield minLetterInterval != null ? ResponseEntity.ok(minLetterInterval) : ResponseEntity.notFound().build();
            }
            default -> ResponseEntity.badRequest().body("Invalid kind parameter value");
        };
    }

    /**
     * Merges the given intervals based on the specified kind of intervals.
     *
     * @param intervals The intervals to be merged.
     * @param kind The kind of intervals, either "digits" or "letters".
     * @return A response indicating the merged intervals if successful, or a response indicating an error if the kind parameter or intervals are invalid.
     */

    @PostMapping(value = "/merge")
    @Synchronized
    public ResponseEntity<Object> mergeIntervals(@RequestBody List<LetterInterval> intervals, @RequestParam("kind") String kind) throws Exception
    {
        MergeHelper mergeHelper = new MergeHelper(intervals);
        return switch (kind)
        {
            case "digits" ->
            {
                try {
                    yield mergeHelper.isValidForDigits()  ?
                            ResponseEntity.ok(digitIntervalRepository.saveAll(mergeHelper.getDigitList(mergeHelper.mergeIntervals()))) : ResponseEntity.badRequest().body("Invalid kind parameter value");
                }
                catch (Exception e) {
                    yield ResponseEntity.badRequest().body("Invalid kind parameter value");
                }
            }
            case "letters" -> mergeHelper.isValidForString() ?
                    ResponseEntity.ok(letterIntervalRepository.saveAll(mergeHelper.mergeIntervals())) : ResponseEntity.badRequest().body("Invalid type parameter value");
            default -> ResponseEntity.badRequest().body("Invalid kind parameter value");
        };
    }
}