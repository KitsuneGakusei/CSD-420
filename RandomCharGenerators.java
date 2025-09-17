/*
 * Author: Crystal Long
 * Date: 2025-09-16
 * Assignment: ThreeThreads — helper generators
 *
 * Purpose: Provide pure functions to generate arrays/strings of random letters, digits, and symbols.
 *          These are easy to unit test without UI.
 */

import java.security.SecureRandom;

public final class RandomCharGenerators {

    private static final char[] LETTERS;
    private static final char[] DIGITS = "0123456789".toCharArray();
    private static final char[] SYMBOLS = "!@#$%&*".toCharArray();
    private static final SecureRandom RNG = new SecureRandom();

    static {
        LETTERS = new char[26];
        for (int i = 0; i < 26; i++) LETTERS[i] = (char) ('a' + i);
    }

    private RandomCharGenerators() {}

    public static String randomLetters(int n) {
        return randomFromSet(LETTERS, n);
    }

    public static String randomDigits(int n) {
        return randomFromSet(DIGITS, n);
    }

    public static String randomSymbols(int n) {
        return randomFromSet(SYMBOLS, n);
    }

    private static String randomFromSet(char[] set, int n) {
        if (n < 0) throw new IllegalArgumentException("n must be non-negative");
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            sb.append(set[RNG.nextInt(set.length)]);
        }
        return sb.toString();
    }

    // Convenience for validating membership (used by tests)
    public static boolean containsOnly(String s, char[] set) {
        outer:
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            for (char allowed : set) {
                if (c == allowed) continue outer;
            }
            return false;
        }
        return true;
    }

    public static char[] lettersSet() { return LETTERS.clone(); }
    public static char[] digitsSet()  { return DIGITS.clone(); }
    public static char[] symbolsSet() { return SYMBOLS.clone(); }
}
