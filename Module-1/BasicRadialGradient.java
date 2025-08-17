import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * Crystal Long
 * Module 1.1 Discussion Board
 * Date: 10/15/2023
 * 
 * Topic:Basic Gradient
 *BasicRadialGradient.java
 * Purpose: Demonstrates a JavaFX RadialGradient by filling a Circle
 * with a smooth color transition from center (BLUE) to edge (GREEN).
 *
 * How it works:
 * - A RadialGradient is constructed with:
 *     focusAngle, focusDistance: where the "hot spot" of the gradient is.
 *     centerX, centerY: the center of the gradient (0..1 because proportional = true).
 *     radius: the gradient radius (0..1 when proportional = true).
 *     proportional: when true, the coordinates are relative to the painted shape.
 *     cycleMethod: how the gradient repeats (NO_CYCLE in this example).
 *     stops: color stops defining the colors along the gradient (offsets 0..1).
 * - The gradient is applied to a Circle as its fill.
 * - A Scene is created and shown on a Stage.
 */
public class BasicRadialGradient extends Application {

    @Override
    public void start(Stage stage) {
        // Define color stops: 0.0 at center (BLUE), 1.0 at edge (GREEN)
        Stop[] stops = new Stop[] {
            new Stop(0, Color.BLUE),
            new Stop(1, Color.GREEN)
        };

        // RadialGradient parameters:
        // focusAngle=0, focusDistance=0 (focus at the center)
        // centerX=0.5, centerY=0.5 (center of the shape, since proportional=true)
        // radius=0.5 (half of the shape’s bounds)
        // proportional=true -> coordinates are relative to the shape (0..1)
        // cycle method NO_CYCLE -> no repetition beyond the radius
        RadialGradient gradient = new RadialGradient(
                0, 0,
                0.5, 0.5,
                0.5,
                true,
                CycleMethod.NO_CYCLE,
                stops
        );

        // Circle positioned at (150,150) with radius 100
        Circle circle = new Circle(150, 150, 100);
        circle.setFill(gradient);

        Pane root = new Pane(circle);
        Scene scene = new Scene(root, 300, 300);

        stage.setScene(scene);
        stage.setTitle("Basic RadialGradient");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
