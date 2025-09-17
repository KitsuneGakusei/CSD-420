/*
 * Author: Crystal Long
 * Date: 2025-09-16
 * Assignment: Module 7 — ThreeThreads
 *
 * Purpose: A JavaFX app that uses three threads to append random letters, digits,
 *          and symbols to a TextArea (10,000 characters from each thread).
 *
 * Notes:
 * - Uses ThreadLocalRandom for thread-safe random generation.
 *  - Public static helper methods are provided so tests can validate behavior.
 *  - No external libraries beyond JavaFX are used.
 */

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.concurrent.ThreadLocalRandom;

public class CrystalThreeThreads extends Application {

    // ----- * constants -----
    private static final int TARGET_PER_SET = 10_000; // minimum per set
    private static final int BATCH_SIZE     = 200;    // UI-friendly append size

    // Character sets (exactly as described)
    private static final char[] LETTERS;
    private static final char[] DIGITS  = "0123456789".toCharArray();
    private static final char[] SYMBOLS = "!@#$%&*".toCharArray();

    static {
        LETTERS = new char[26];
        for (int i = 0; i < 26; i++) LETTERS[i] = (char) ('a' + i);
    }

    private TextArea output;

    // ===== Helper methods (public static so tests can call them) =====

    /** Generate a random string of length n from the provided set. */
    public static String randomFromSet(char[] set, int n) {
        if (n < 0) throw new IllegalArgumentException("n must be non-negative");
        StringBuilder sb = new StringBuilder(n);
        ThreadLocalRandom r = ThreadLocalRandom.current();
        for (int i = 0; i < n; i++) {
            sb.append(set[r.nextInt(set.length)]);
        }
        return sb.toString();
    }

    /** Verify that a string contains only characters from the provided set. */
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

    /** Expose the exact sets for tests. */
    public static char[] lettersSet() { return LETTERS.clone(); }
    public static char[] digitsSet()  { return DIGITS.clone();  }
    public static char[] symbolsSet() { return SYMBOLS.clone(); }

    // ===== JavaFX UI =====

    @Override
    public void start(Stage stage) {
        Label title = new Label("CrystalThreeThreads — 3 concurrent streams (letters, digits, symbols)");
        output = new TextArea();
        output.setEditable(false);
        output.setWrapText(true);
        output.setPrefRowCount(24);

        VBox root = new VBox(8, title, output);
        root.setPadding(new Insets(12));

        stage.setTitle("CrystalThreeThreads");
        stage.setScene(new Scene(root, 820, 520));
        stage.show();

        // Start the three threads immediately upon showing the window
        startThreads();
    }

    private void startThreads() {
        new Thread(() -> produce(LETTERS), "LettersThread").start();
        new Thread(() -> produce(DIGITS),  "DigitsThread").start();
        new Thread(() -> produce(SYMBOLS), "SymbolsThread").start();
    }

    private void produce(char[] set) {
        int produced = 0;
        while (produced < TARGET_PER_SET) {
            int remaining = TARGET_PER_SET - produced;
            int thisBatch = Math.min(BATCH_SIZE, remaining);
            String chunk = randomFromSet(set, thisBatch);

            // Append on the JavaFX Application Thread
            Platform.runLater(() -> output.appendText(chunk));

            produced += thisBatch;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
