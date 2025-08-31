/*
 * Crystal Long
 * Module 3 Programming Assignment
 * Date: 08/27/2025
 * Purpose: Create an ArrayList with 50 random integers from 1–20,
 *          then return a new ArrayList with duplicates removed.
 */

import java.util.ArrayList;
import java.util.Random;

public class Module3Program {

    public static void main(String[] args) {
        // Create and fill the original ArrayList with 50 random integers
        ArrayList<Integer> originalList = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < 50; i++) {
            originalList.add(rand.nextInt(20) + 1); // values from 1 to 20
        }

        // Display the original list
        System.out.println("Original ArrayList (50 random numbers, may have duplicates):");
        System.out.println(originalList);

        // Remove duplicates and store in a new ArrayList
        ArrayList<Integer> noDuplicatesList = removeDuplicates(originalList);

        // Display the duplicate-free list
        System.out.println("\nArrayList with duplicates removed:");
        System.out.println(noDuplicatesList);
    }

    // Static generic method to remove duplicates
    public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {
        ArrayList<E> resultList = new ArrayList<>();
        for (E element : list) {
            if (!resultList.contains(element)) {
                resultList.add(element);
            }
        }
        return resultList;
    }
}
