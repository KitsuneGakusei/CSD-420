/**
 * Crystal Long
 * September 13, 2025
 * Module 6_2
 *
 * Purpose: Implement two generic bubble sort methods—one using Comparable (natural order)
 *          and one using a provided Comparator—to sort arrays. Includes test code.
 *
 * How to run:
 *   java Long_Mod6_2.java
 *   java Long_Mod6_2
 *
 * Sources (acknowledgments):
 * - Module 6 Programming Assignment handout (method signatures and requirements).
 * - Reviewed the provided Bubble_Sort.java sample for reference only.
 *   Implementation here.
 */

import java.util.Arrays;
import java.util.Comparator;

public class Long_Mod6_2 {

    public static <E extends Comparable<E>> void bubbleSort(E[] list) {
        if (list == null || list.length < 2) return;
        boolean swapped;
        for (int pass = 0; pass < list.length - 1; pass++) {
            swapped = false;
            for (int i = 0; i < list.length - 1 - pass; i++) {
                if (list[i].compareTo(list[i + 1]) > 0) {
                    E temp = list[i];
                    list[i] = list[i + 1];
                    list[i + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    public static <E> void bubbleSort(E[] list, Comparator<? super E> comparator) {
        if (list == null || list.length < 2) return;
        if (comparator == null) throw new IllegalArgumentException("Comparator must not be null.");
        boolean swapped;
        for (int pass = 0; pass < list.length - 1; pass++) {
            swapped = false;
            for (int i = 0; i < list.length - 1 - pass; i++) {
                if (comparator.compare(list[i], list[i + 1]) > 0) {
                    E temp = list[i];
                    list[i] = list[i + 1];
                    list[i + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    public static <E extends Comparable<E>> boolean isSortedAscending(E[] list) {
        if (list == null || list.length < 2) return true;
        for (int i = 0; i < list.length - 1; i++) {
            if (list[i].compareTo(list[i + 1]) > 0) return false;
        }
        return true;
    }

    public static <E> boolean isSortedAscending(E[] list, Comparator<? super E> comparator) {
        if (list == null || list.length < 2) return true;
        for (int i = 0; i < list.length - 1; i++) {
            if (comparator.compare(list[i], list[i + 1]) > 0) return false;
        }
        return true;
    }

    static class Person {
        private final String name;
        private final int age;
        Person(String name, int age) { this.name = name; this.age = age; }
        public String getName() { return name; }
        public int getAge() { return age; }
        @Override public String toString() { return name + " (" + age + ")"; }
    }

    public static void main(String[] args) {
        Integer[] numbers = { 5, 3, 9, 1, 4, 8, 2, 7, 6, 0 };
        System.out.println("Integers Comparable before: " + Arrays.toString(numbers));
        bubbleSort(numbers);
        System.out.println("Integers Comparable after : " + Arrays.toString(numbers));
        System.out.println("Sorted? " + isSortedAscending(numbers));

        Integer[] numbers2 = { 5, 3, 9, 1, 4, 8, 2, 7, 6, 0 };
        Comparator<Integer> reverseInt = (a, b) -> Integer.compare(b, a);
        bubbleSort(numbers2, reverseInt);
        System.out.println("Integers Comparator reverse: " + Arrays.toString(numbers2));
        System.out.println("Sorted? " + isSortedAscending(numbers2, reverseInt));

        String[] words = { "pear", "apple", "banana", "kiwi", "boysenberry", "raspberry" };
        bubbleSort(words);
        System.out.println("Strings Comparable A-Z: " + Arrays.toString(words));
        System.out.println("Sorted? " + isSortedAscending(words));

        String[] words2 = { "pear", "apple", "banana", "kiwi", "boysenberry", "raspberry", "plum" };
        Comparator<String> byLengthThenAlpha = (a, b) -> {
            int len = Integer.compare(a.length(), b.length());
            return (len != 0) ? len : a.compareTo(b);
        };
        bubbleSort(words2, byLengthThenAlpha);
        System.out.println("Strings Comparator length/alpha: " + Arrays.toString(words2));
        System.out.println("Sorted? " + isSortedAscending(words2, byLengthThenAlpha));

        Person[] people = {
            new Person("Ralph", 34), new Person("Emma", 30),
            new Person("Miley", 30), new Person("Zoey", 19), new Person("Tammy", 42)
        };
        Comparator<Person> byAgeThenName = Comparator.comparingInt(Person::getAge).thenComparing(Person::getName);
        bubbleSort(people, byAgeThenName);
        System.out.println("People Comparator age/name: " + Arrays.toString(people));
        System.out.println("Sorted? " + isSortedAscending(people, byAgeThenName));

        assert isSortedAscending(numbers);
        assert isSortedAscending(words);
        assert isSortedAscending(numbers2, reverseInt);
        assert isSortedAscending(words2, byLengthThenAlpha);
        assert isSortedAscending(people, byAgeThenName);

        System.out.println("All basic assertions passed.");
    }
}
