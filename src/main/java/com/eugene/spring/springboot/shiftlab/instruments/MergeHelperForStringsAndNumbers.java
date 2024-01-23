//package com.eugene.spring.springboot.shiftlab.instruments;
//
//import com.eugene.spring.springboot.shiftlab.model.Interval;
//import com.eugene.spring.springboot.shiftlab.model.LetterInterval;
//
//import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.List;
//
//public class MergeHelperForStringsAndNumbers {
//    private final List<Interval> intervalList;
//
//    public MergeHelperForStringsAndNumbers(List<LetterInterval> intervalList)
//    {
//        this.intervalList = new ArrayList<>(intervalList);
//    }
//
//
//    public List<Interval> mergeIntervals() {
//        intervalList.sort(Comparator.comparing(Interval::getStart));
//
//        List<Interval> mergedIntervals = new ArrayList<>();
//        Interval currentInterval = null;
//
//        for (Interval interval : intervalList) {
//            if (currentInterval == null || interval.getStart().compareTo(currentInterval.getEnd()) > 0) {
//                mergedIntervals.add(interval);
//                currentInterval = interval;
//            } else if (interval.getEnd().compareTo(currentInterval.getEnd()) > 0) {
//                currentInterval.setEnd(interval.getEnd());
//            }
//        }
//        return mergedIntervals;
//    }
//}
