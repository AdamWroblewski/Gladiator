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
     * //TODO javadoc, what should this method do?
     * @param min //TODO
     * @param max //TODO
     * @return //TODO
     */
    int getNumber(int min, int max);
}
