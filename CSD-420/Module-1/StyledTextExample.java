/**
 * Crystal Long
 * Module 1.1 Assignment Topic 2
 * Date: 08/15/2025
 *
 * Purpose:
 * Demonstrates how to use the JavaFX Text class with specific font styling,
 * fill color, stroke (outline) color, and multi-line formatting.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StyledTextExample extends Application {

    @Override
    public void start(Stage stage) {
        // Define the multi-line string (using \n for line breaks).
        // Removed bullet dots as requested.
        String content = 
                "The text itself – the text that we want to be displayed\n" +
                "The font – the font style such as sans script, the size, weight, and more stylized options\n" +
                "The fill color and the stroke color – the inside coloring of the text and the outline of the text";

        // Create a Text object with the content string
        Text text = new Text(content);

        // Set the font:
        // - SansSerif (generic sans style font)
        // - Normal weight (regular)
        // - Italicized posture
        // - Size 24
        text.setFont(Font.font("SansSerif", FontWeight.NORMAL, FontPosture.ITALIC, 24));

        // Underline the entire text
        text.setUnderline(true);

        // Set the inside (fill) color to blue
        text.setFill(Color.BLUE);

        // Set the outline (stroke) color to a complementary green
        text.setStroke(Color.GREEN);

        // Make the stroke wide enough to be visible
        text.setStrokeWidth(1.2);

        // Place the text inside a layout container
        StackPane root = new StackPane(text);

        // Create a scene with the container
        Scene scene = new Scene(root, 800, 300);

        // Set up the stage (window) with the scene
        stage.setScene(scene);
        stage.setTitle("Styled JavaFX Text Example");
        stage.show();
    }

    public static void main(String[] args) {
        // Launch the JavaFX application
        launch(args);
    }
}
