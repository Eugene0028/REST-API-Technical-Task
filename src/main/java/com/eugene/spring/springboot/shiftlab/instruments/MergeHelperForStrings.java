package com.eugene.spring.springboot.shiftlab.instruments;

import com.eugene.spring.springboot.shiftlab.model.LetterInterval;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MergeHelperForStrings
{
    private final List<LetterInterval> intervalList;

    public MergeHelperForStrings(List<LetterInterval> intervalList) {
        this.intervalList = intervalList;
    }

    public List<LetterInterval> mergeIntervals() {
        intervalList.sort(Comparator.comparing(LetterInterval::getStart));

        List<LetterInterval> mergedIntervals = new ArrayList<>();
        LetterInterval currentInterval = null;

        for (LetterInterval interval : intervalList) {
            if (currentInterval == null || interval.getStart().compareTo(currentInterval.getEnd()) > 0) {
                mergedIntervals.add(interval);
                currentInterval = interval;
            } else if (interval.getEnd().compareTo(currentInterval.getEnd()) > 0) {
                currentInterval.setEnd(interval.getEnd());
            }
        }
        return mergedIntervals;
    }

    public boolean isNumeric() {
        String regex = "\\d";
        int cnt = 0;
        for (var i : intervalList) {
            if (i.getStart().matches(regex) && i.getEnd().matches(regex)) cnt++;
        }
        return cnt == intervalList.size();
    }

    public boolean isAlph() {
        String regex = "\\p{Lower}";
        int cnt = 0;
        for (var i : intervalList) {
            if (i.getStart().matches(regex) && i.getEnd().matches(regex)) cnt++;
        }
        return cnt == intervalList.size();
    }

    public boolean isValid() {
        return intervalList.stream().allMatch(i -> i.getStart().compareTo(i.getEnd()) < 0);
    }
}
