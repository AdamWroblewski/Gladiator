package com.company.view;

/**
 * Interface used for basic View functionality (displaying text)
 */
public interface Viewable {
    /**
     * Displays given text into the View
     *
     * @param text
     */
    void display(String text);

    /**
     *
     * @param min
     * @param max
     * @return
     */
    int getNumber(int min, int max);
}
