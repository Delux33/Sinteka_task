package com.sinteka.tools;

import java.util.*;

public class StringComparison {
    private static final Map<Integer, List<String>> firstList = new HashMap<>();
    private static final Map<Integer, List<String>> secondList = new HashMap<>();
    private static final Set<Integer> indexFirstList = new HashSet<>();
    private static final Set<Integer> indexSecondList = new HashSet<>();

    public static List<String> compareForSimilarity(List<String> firstListOfStrings, List<String> secondListOfStrings) {

        fillListSplitByWords(firstListOfStrings, firstList);
        fillListSplitByWords(secondListOfStrings, secondList);

        return getListAfterComparison(firstListOfStrings, secondListOfStrings);
    }

    private static void fillListSplitByWords(List<String> ListOfStrings, Map<Integer, List<String>> listWithWords) {

        for (int i = 0; i < ListOfStrings.size(); i++) {
            listWithWords.put(i, getValueByWords(ListOfStrings, i));
        }
    }

    private static List<String> getValueByWords(List<String> ListOfStrings, int i) {
        return Arrays.asList(ListOfStrings.get(i).split(" "));
    }

    private static List<String> getListAfterComparison(List<String> firstListOfStrings, List<String> secondListOfStrings) {
        List<String> result = new ArrayList<>();

        for (Map.Entry<Integer, List<String>> firstListOfWords : firstList.entrySet()) {
            for (Map.Entry<Integer, List<String>> secondListOfWords : secondList.entrySet()) {

                for (String wordFromFirstList : firstListOfWords.getValue()) {
                    for (String wordFromSecondList : secondListOfWords.getValue()) {

                        if (wordFromFirstList.equals(wordFromSecondList)) {

                            if (indexFirstList.contains(firstListOfWords.getKey())) {
                                break;
                            }

                            String stringWithCoincidence = getStringWithCoincidence(firstListOfStrings, secondListOfStrings,
                                    firstListOfWords, secondListOfWords);

                            result.add(stringWithCoincidence);

                            indexFirstList.add(firstListOfWords.getKey());
                            indexSecondList.add(secondListOfWords.getKey());
                        }
                    }
                }
            }
            addNotCoincidencesFromFirstList(firstListOfStrings, result, firstListOfWords);
        }
        removeCoincidenceStringsFromSecondList();
        addNotCoincidencesFromSecondList(secondListOfStrings, result);
        return result;
    }

    private static String getStringWithCoincidence(List<String> firstListOfStrings,
                                                   List<String> secondListOfStrings,
                                                   Map.Entry<Integer, List<String>> firstListOfWords,
                                                   Map.Entry<Integer, List<String>> secondListOfWords) {

        String result = firstListOfStrings.get(firstListOfWords.getKey()) + " : "
                + secondListOfStrings.get(secondListOfWords.getKey());
        return result;
    }

    private static void addNotCoincidencesFromFirstList(List<String> firstListOfStrings,
                                                        List<String> result,
                                                        Map.Entry<Integer, List<String>> firstListOfWords) {

        if (!indexFirstList.contains(firstListOfWords.getKey())) {
            String stringWithoutCoincidence = getSingleString(firstListOfStrings, firstListOfWords);

            result.add(stringWithoutCoincidence);

            indexFirstList.add(firstListOfWords.getKey());
        }
    }

    private static String getSingleString(List<String> ListOfStrings, Map.Entry<Integer, List<String>> listOfWords) {
        return ListOfStrings.get(listOfWords.getKey()) + " : ?";
    }

    private static void removeCoincidenceStringsFromSecondList() {
        for (int key : indexSecondList) {
            secondList.remove(key);
        }
    }

    private static void addNotCoincidencesFromSecondList(List<String> secondListOfStrings, List<String> result) {
        for (Map.Entry<Integer, List<String>> listOfWords : secondList.entrySet()) {
            String stringWithoutCoincidence = getSingleString(secondListOfStrings, listOfWords);
            result.add(stringWithoutCoincidence);
        }
    }
}
