package com.company;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

public class Sort {
    public static HashMap<String, Integer> sort_r(HashMap<String, Integer> words){

       HashMap<String, Integer> sortedMap = words.entrySet().stream()
                .sorted(Comparator.comparingInt(e -> -e.getValue()))
                .collect(Collectors.toMap(
                        HashMap.Entry::getKey,
                        HashMap.Entry::getValue,
                        (a, b) -> { throw new AssertionError(); },
                        LinkedHashMap::new));
        return sortedMap;
    }
}
