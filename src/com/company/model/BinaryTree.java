package com.company.model;

/**
 * Custom implementation of Binary Tree for the Tournament Pyramid logic
 *
 * @param <T>
 */
public class BinaryTree<T> {
    /**
     * A boolean value used for navigating between left and right branches when adding new values
     */
    private boolean left = true;

    /**
     * Default BinaryTree constructor
     */
    public BinaryTree() {
    }

    /**
     * Constructor with Value
     *
     * @param value
     */
    public BinaryTree(T value) {
        Value = value;
    }

    public BinaryTree<T> LeftBranch;
    public BinaryTree<T> RightBranch;

    public T Value;

    /**
     * Adds a new value to this Tree
     *
     * @param value
     */
    public void Add(T value) {
        // Check if branches already exist
        if (LeftBranch != null && RightBranch != null) {
            // Pass the value deeper into the Tree
            if (left)
                LeftBranch.Add(value);
            else
                RightBranch.Add(value);

            left = !left;
        } else {
            if (Value == null) {
                // Add value to this Tree node
                Value = value;
            } else {
                // Create branches
                LeftBranch = new BinaryTree<T>(Value);
                RightBranch = new BinaryTree<T>(value);

                // Nullify the value, since it's been passed to the left branch
                Value = null;
            }
        }
    }

    /// <summary>
    ///
    /// </summary>
    /// <param name="enumerable"></param>
    public void AddRange(Iterable<T> iterable) {
        for (var element : iterable) Add(element);
    }
}
