/*
  Author: Crystal Long
  Date: 2025-09-16
  Assignment: Module 7 Programming Assignment
  Purpose: Display four circles styled via external CSS (one class + two IDs).
*/

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class CircleView extends Application {
    private static final double CIRCLE_RADIUS = 50;
    private static final String CSS_FILE = "mystyle.css";

    /** Builds the UI root so tests can reuse it without starting the full app. */
    public static GridPane createRoot() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(16));
        grid.setHgap(24);
        grid.setVgap(24);

        Circle white1 = new Circle(CIRCLE_RADIUS);
        white1.getStyleClass().add("plaincircle");

        Circle white2 = new Circle(CIRCLE_RADIUS);
        white2.getStyleClass().add("plaincircle");

        Circle red = new Circle(CIRCLE_RADIUS);
        red.setId("redcircle");

        Circle green = new Circle(CIRCLE_RADIUS);
        green.setId("greencircle");

        StackPane c1 = wrapCentered(white1);
        StackPane c2 = wrapCentered(red);
        StackPane c3 = wrapCentered(green);
        StackPane c4 = wrapCentered(white2);

        grid.add(c1, 0, 0);
        grid.add(c2, 1, 0);
        grid.add(c3, 0, 1);
        grid.add(c4, 1, 1);

        return grid;
    }

    private static StackPane wrapCentered(Circle circle) {
        StackPane pane = new StackPane(circle);
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(12));
        return pane;
    }

    @Override
    public void start(Stage stage) {
        GridPane root = createRoot();
        Scene scene = new Scene(root);
        if (getClass().getResource(CSS_FILE) != null) {
            scene.getStylesheets().add(getClass().getResource(CSS_FILE).toExternalForm());
        }
        stage.setTitle("Module 7 - Styled Circles");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
