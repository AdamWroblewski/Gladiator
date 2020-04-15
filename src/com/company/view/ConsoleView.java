package com.company.view;

import java.util.Scanner;

/**
 * Basic Console View implementation
 */
public class ConsoleView implements Viewable {
    private static Scanner scanner = new Scanner(System.in);

    @Override
    public void display(String text) {
        System.out.println(text);
    }

    @Override
    public int getNumber(int min, int max) {
        int number = 0;
        while (true) { // FIXME don't use while (true) -> there should be no way that this loop goes forever
            String input = scanner.next();

            // Try parsing the string input into a number
            try {
                number = Integer.parseInt(input);
            } catch (NumberFormatException ex) {
                continue;
            }
            // TODO should be a separate method returning true/false
            // Check whether the number is within given range
            if (number >= min || number <= max)
                // Everything good
                return number;
        }
    }
}
