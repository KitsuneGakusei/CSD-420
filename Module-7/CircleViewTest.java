/*
  Author: Crystal Long
  Date: 2025-09-16
  Assignment: Module 7 Programming Assignment (Test)
  Purpose: Sanity checks for the JavaFX scene graph and CSS usage without external test libs.
*/

import javafx.embed.swing.JFXPanel; // initializes JavaFX runtime in headless context
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.Node;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CircleViewTest {
    private static void assertTrue(boolean condition, String msg) {
        if (!condition) throw new AssertionError(msg);
    }
    private static void assertEquals(int expected, int actual, String msg) {
        if (expected != actual) throw new AssertionError(msg + " (expected " + expected + ", got " + actual + ")");
    }

    public static void main(String[] args) {
        new JFXPanel(); // boot JavaFX

        GridPane root = CircleView.createRoot();
        Scene scene = new Scene(root);

        URL cssUrl = CircleView.class.getResource("mystyle.css");
        if (cssUrl != null) {
            scene.getStylesheets().add(cssUrl.toExternalForm());
        }

        List<Circle> circles = new ArrayList<>();
        for (Node n : root.getChildren()) {
            if (n instanceof StackPane) {
                StackPane sp = (StackPane) n;
                for (Node child : sp.getChildren()) {
                    if (child instanceof Circle) {
                        circles.add((Circle) child);
                    }
                }
            }
        }

        assertEquals(4, circles.size(), "There should be exactly four circles");

        boolean hasPlain = false, hasRed = false, hasGreen = false;
        for (Circle c : circles) {
            if (c.getStyleClass().contains("plaincircle")) hasPlain = true;
            if ("redcircle".equals(c.getId())) hasRed = true;
            if ("greencircle".equals(c.getId())) hasGreen = true;
        }
        assertTrue(hasPlain, "At least one circle should have class 'plaincircle'");
        assertTrue(hasRed, "One circle should have id 'redcircle'");
        assertTrue(hasGreen, "One circle should have id 'greencircle'");

        boolean cssAttached = false;
        for (String s : scene.getStylesheets()) {
            if (s.endsWith("mystyle.css")) { cssAttached = true; break; }
        }
        assertTrue(cssAttached, "The stylesheet 'mystyle.css' should be attached to the Scene");

        System.out.println("All tests passed ✅");
    }
}
