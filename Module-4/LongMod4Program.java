/*
 * Crystal Long
 * Module 4 Programming Assignment
 * Date: 08/29/2025
 * Purpose: Compare LinkedList traversal time via iterator (for-each) vs get(index)
 * Discussion:
 * - On LinkedList, iterator traversal is O(n) and should be much faster.
 * - get(index) is O(n) per access on LinkedList, so a full pass is O(n^2) and
 *   becomes dramatically slower at larger sizes (e.g., 500,000).
 */

import java.util.LinkedList;
import java.util.List;

public class LinkedListTraversalTest {
    public static void main(String[] args) {
        testTraversalTime(50_000);
        testTraversalTime(500_000);
    }

    private static void testTraversalTime(int size) {
        List<Integer> list = new LinkedList<>();

        // Fill LinkedList with integers 0..size-1
        for (int i = 0; i < size; i++) {
            list.add(i);
        }

        System.out.println("Testing with " + size + " integers:");

        // --- Iterator (for-each) traversal with a simple correctness test (sum) ---
        long start = System.nanoTime();
        long iterSum = 0L;
        for (int num : list) {
            iterSum += num;
        }
        long end = System.nanoTime();
        double iterMs = (end - start) / 1_000_000.0;
        System.out.println("Iterator (for-each) time: " + iterMs + " ms");

        // --- get(index) traversal with the same correctness test (sum) ---
        start = System.nanoTime();
        long getSum = 0L;
        for (int i = 0; i < list.size(); i++) {
            getSum += list.get(i);
        }
        end = System.nanoTime();
        double getMs = (end - start) / 1_000_000.0;
        System.out.println("get(index) loop time: " + getMs + " ms");

        // --- Minimal test to ensure correctness ---
        long expected = ((long) size * (size - 1)) / 2; // sum 0..(size-1)
        if (iterSum == expected && getSum == expected) {
            System.out.println("TEST PASS: sums match expected " + expected);
        } else {
            System.out.println("TEST FAIL: expected=" + expected +
                               ", iterSum=" + iterSum + ", getSum=" + getSum);
        }

        System.out.println("Difference (get - iter): " + (getMs - iterMs) + " ms\n");
    }
}
