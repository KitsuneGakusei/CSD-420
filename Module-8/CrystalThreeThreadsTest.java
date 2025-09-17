/*
 * Author: Crystal Long
 * Date: 2025-09-16
 * Assignment: Module 7 — ThreeThreads (Tests)
 *
 * Purpose: Test the helper methods in CrystalThreeThreads to ensure
 *          correct string lengths and allowed character sets.
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CrystalThreeThreadsTest {

    @Test
    void lettersLengthAndAlphabet() {
        String s = CrystalThreeThreads.randomFromSet(CrystalThreeThreads.lettersSet(), 500);
        assertEquals(500, s.length());
        assertTrue(CrystalThreeThreads.containsOnly(s, CrystalThreeThreads.lettersSet()));
    }

    @Test
    void digitsLengthAndSet() {
        String s = CrystalThreeThreads.randomFromSet(CrystalThreeThreads.digitsSet(), 500);
        assertEquals(500, s.length());
        assertTrue(CrystalThreeThreads.containsOnly(s, CrystalThreeThreads.digitsSet()));
    }

    @Test
    void symbolsLengthAndSet() {
        String s = CrystalThreeThreads.randomFromSet(CrystalThreeThreads.symbolsSet(), 500);
        assertEquals(500, s.length());
        assertTrue(CrystalThreeThreads.containsOnly(s, CrystalThreeThreads.symbolsSet()));
    }

    @Test
    void zeroAndNegative() {
        assertEquals(0, CrystalThreeThreads.randomFromSet(CrystalThreeThreads.lettersSet(), 0).length());
        assertThrows(IllegalArgumentException.class,
                () -> CrystalThreeThreads.randomFromSet(CrystalThreeThreads.digitsSet(), -1));
    }
}
