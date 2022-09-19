package com.company;

import java.util.HashMap;


public class Parser {
    private StringBuilder builder;
    private HashMap<String, Integer> words = new HashMap<>();
    private int count = 0;

    public Parser(StringBuilder builder) {
        this.builder= builder;
    }

    public HashMap<String, Integer> Getwords(){
        return words;
    }

    public int Getcount(){
        return count;
    }

    public void parce(){
        int i = 0;
        String ptr;
        while (builder.length() != 0) {
             count += 1;
             while (i < builder.length() && Character.isLetterOrDigit(builder.charAt(i))) {
                    i += 1;
            }
            ptr = builder.substring(0, i);

          /* if(words.containsKey(ptr))
               words.put(ptr, words.get(ptr) + 1);
            else
               words.put(ptr, 1);
               */
            words.merge(ptr, 1, (old, prev) -> old + prev);

            while (i < builder.length() && !Character.isLetterOrDigit(builder.charAt(i)))
                   i += 1;

            builder.delete(0, i);
            i = 0;
       }
        words = Sort.sort_r(words);
   }
}

