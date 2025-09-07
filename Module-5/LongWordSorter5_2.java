/*
 * Name: Crystal Long
 * Date: 2025-09-07
 * Assignment: Module 5.2 – Word Sorting Program
 *
 * Program Summary (what it does):
 *   - Reads words from "collection_of_words.txt" (referenced directly in code).
 *   - Normalizes tokens (lowercase, strip punctuation) to avoid duplicate variants.
 *   - Removes duplicates and prints the unique words:
 *       1) in ascending (A→Z) order
 *       2) then in descending (Z→A) order
 *   - Includes a lightweight self-test to verify normalization, de-duplication, and ordering.
 *
 * File Placement:
 *   - Put "collection_of_words.txt" in the same folder as this .java file (or your project root).
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

public class LongWordSorter5_2 {

    public static void main(String[] args) {
        /* SECTION: Configuration & Self-Test
         * - The file name is fixed (no command-line args required).
         * - Run a minimal self-test so the assignment includes test code that verifies core logic.
         */
        final String fileName = "collection_of_words.txt";
        runSelfTest();

        /* SECTION: Data Structure for Unique, Sorted Words
         * - TreeSet provides ascending order and uniqueness automatically.
         */
        SortedSet<String> uniqueWordsAscending = new TreeSet<>();

        /* SECTION: File Reading + Normalization + De-duplication
         * - Read tokens from the file, normalize each token to a clean word,
         *   and add it to the TreeSet if non-empty.
         */
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNext()) {
                String normalized = normalize(scanner.next());
                if (!normalized.isEmpty()) {
                    uniqueWordsAscending.add(normalized);
                }
            }

            /* SECTION: Output (Ascending)
             * - Directly iterate the TreeSet to print A→Z unique words.
             */
            System.out.println("=== Ascending Order (Unique Words) ===");
            for (String word : uniqueWordsAscending) {
                System.out.println(word);
            }

            /* SECTION: Output (Descending)
             * - Copy to a list and reverse for Z→A output.
             */
            ArrayList<String> descendingList = new ArrayList<>(uniqueWordsAscending);
            Collections.reverse(descendingList);

            System.out.println("\n=== Descending Order (Unique Words) ===");
            for (String word : descendingList) {
                System.out.println(word);
            }

        } catch (FileNotFoundException e) {
            /* SECTION: Error Handling
             * - Provide a clear message if the input file is missing and how to fix it.
             */
            System.out.println("Error: File '" + fileName + "' not found.");
            System.out.println("Place '" + fileName + "' in the same folder as this .java file or your project root.");
        }
    }

    /* SECTION: Normalization Helper
     * - Converts a raw token to a comparable word by:
     *   1) lowercasing (case-insensitive comparisons)
     *   2) removing non-letters (punctuation, numbers, symbols)
     * - Returns "" when nothing remains after cleaning.
     */
    private static String normalize(String token) {
        if (token == null) return "";
        String lower = token.toLowerCase();
        String lettersOnly = lower.replaceAll("[^a-z]", "");
        return lettersOnly;
    }

    /* SECTION: Lightweight Self-Test
     * - Verifies that:
     *   - normalize() lowercases and strips punctuation,
     *   - TreeSet de-duplicates and sorts ascending,
     *   - Reversed list yields correct descending order.
     * - Prints PASS/FAIL (simple console-based check suitable for a single-file assignment).
     */
    private static void runSelfTest() {
        System.out.println("=== Running Self-Test ===");

        String[] tokens = { "Banana,", "apple", "Apple", "banana;", "Carrot", "carrot!", "APPLE??" };
        String[] expectedAscending = { "apple", "banana", "carrot" };
        String[] expectedDescending = { "carrot", "banana", "apple" };

        SortedSet<String> set = new TreeSet<>();
        for (String t : tokens) {
            String n = normalize(t);
            if (!n.isEmpty()) set.add(n);
        }

        ArrayList<String> ascList = new ArrayList<>(set);
        ArrayList<String> descList = new ArrayList<>(set);
        Collections.reverse(descList);

        boolean ascOk = Arrays.equals(ascList.toArray(new String[0]), expectedAscending);
        boolean descOk = Arrays.equals(descList.toArray(new String[0]), expectedDescending);

        if (ascOk && descOk) {
            System.out.println("Self-Test: PASS");
        } else {
            System.out.println("Self-Test: FAIL");
            System.out.println("  Expected ASC: " + Arrays.toString(expectedAscending));
            System.out.println("  Actual   ASC: " + ascList);
            System.out.println("  Expected DESC: " + Arrays.toString(expectedDescending));
            System.out.println("  Actual   DESC: " + descList);
        }

        System.out.println("=== End Self-Test ===\n");
    }
}
