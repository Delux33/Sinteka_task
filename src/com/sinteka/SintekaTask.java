package com.sinteka;

import com.sinteka.tools.StringComparison;
import com.sinteka.tools.Reader;

import java.util.List;

public class SintekaTask {
    public static void main(String[] args) {
        Reader reader = new Reader();

        List<String> firstListOfStrings = reader.toEnterNumberAndThenStrings();
        List<String> secondListOfStrings = reader.toEnterNumberAndThenStrings();

        reader.close();

        List<String> result = StringComparison.compareForSimilarity(firstListOfStrings, secondListOfStrings);

        for (String output : result) {
            System.out.println(output);
        }
    }
}