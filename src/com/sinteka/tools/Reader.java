package com.sinteka.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reader {

    private final Scanner scanner = new Scanner(System.in);

    public List<String> toEnterNumberAndThenStrings() {
        int capacity = toEnterNumber();
        scanner.nextLine();
        return getListOfStrings(capacity);
    }

    private int toEnterNumber() {
        System.out.println("Enter how many strings to read:");

        while (true) {
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            }

            System.out.println("It's not a number");
            scanner.nextLine();
        }
    }

    private List<String> getListOfStrings(int capacity) {
        List<String> listOfStrings = new ArrayList<>(capacity);

        System.out.format("Next I will read " +
                "%s strings, enter them: \n", capacity);

        int count = 0;
        while (true) {
            count++;
            listOfStrings.add(scanner.nextLine());

            if (count == capacity) {
                return listOfStrings;
            }
        }
    }

    public void close() {
        scanner.close();
    }
}
