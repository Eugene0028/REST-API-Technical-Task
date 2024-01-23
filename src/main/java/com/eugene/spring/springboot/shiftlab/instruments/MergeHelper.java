package com.eugene.spring.springboot.shiftlab.instruments;

import com.eugene.spring.springboot.shiftlab.model.DigitInterval;
import com.eugene.spring.springboot.shiftlab.model.LetterInterval;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
public class MergeHelper
{
    private final List<LetterInterval> intervalList;
    public MergeHelper(List<LetterInterval> intervalList) {
        this.intervalList = intervalList;
    }
    public List<DigitInterval> getDigitList(List<LetterInterval> LetterList) throws Exception {
        List<DigitInterval> digitList = new ArrayList<>();
        for(var i : LetterList)
        {
            try {
                digitList.add(new DigitInterval(Long.parseLong(i.getStart()), Long.parseLong(i.getEnd())));
            }
            catch (Exception e) {
                throw new Exception("Invalid type");
            }
        }
        return digitList;
    }
    public List<LetterInterval> mergeIntervals()
    {
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

    public boolean isValidForString() {
        String regexDigit = "\\d";
        String regexLower = "\\p{Lower}";
        for (var i : intervalList)
        {
            String start = i.getStart();
            String end = i.getEnd();
            if ( (start.matches(regexDigit) && end.matches(regexDigit) )  || (!start.matches(regexLower)  || !end.matches(regexLower)) || (start.compareTo(end) > 0)) return false;
        }
        return true;
    }
    public boolean isValidForDigits(){
        return intervalList.stream().allMatch(i -> i.getStart().compareTo(i.getEnd()) < 0);
    }

}
