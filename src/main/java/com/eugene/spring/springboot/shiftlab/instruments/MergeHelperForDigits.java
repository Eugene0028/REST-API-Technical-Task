//package com.eugene.spring.springboot.shiftlab.instruments;
//
//import com.eugene.spring.springboot.shiftlab.model.DigitInterval;
//import com.eugene.spring.springboot.shiftlab.model.LetterInterval;
//
//
//import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.List;
//
//
//public class MergeHelperForDigits
//{
//    private final List<DigitInterval> intervalList;
//
//    public MergeHelperForDigits(List<LetterInterval> intervalList) throws Exception {
//        this.intervalList = new ArrayList<>();
//        for(LetterInterval i : intervalList)
//        {
//            try
//            {
//                Long start = Long.parseLong(i.getStart());
//                Long end = Long.parseLong(i.getEnd());
//                this.intervalList.add(new DigitInterval(null, start, end));
//            }
//            catch (Exception e){throw new Exception("Error");}
//        }
//    }
//    public List<DigitInterval> mergeIntervals()
//    {
//        intervalList.sort(Comparator.comparing(DigitInterval::getStart));
//
//        List<DigitInterval> mergedIntervals = new ArrayList<>();
//        DigitInterval currentInterval = null;
//
//        for (DigitInterval interval : intervalList)
//        {
//            if (currentInterval == null || interval.getStart().compareTo(currentInterval.getEnd()) > 0) {
//                mergedIntervals.add(interval);
//                currentInterval = interval;
//            } else if (interval.getEnd().compareTo(currentInterval.getEnd()) > 0) {
//                currentInterval.setEnd(interval.getEnd());
//            }
//        }
//        return mergedIntervals;
//    }
//    public boolean isValid(){
//        return intervalList.stream().allMatch(i -> i.getStart().compareTo(i.getEnd()) < 0);
//    }
//
//}