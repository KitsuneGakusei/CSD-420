// Name: Crystal Long
// Assignment:Module 1 Programming – Random Cards (JavaFX)
// Date: 8-17-25

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomCardViewer extends Application {

    private static final int NUM_CARDS = 52;      // total card images
    private static final int NUM_TO_SHOW = 4;     // show four at a time

    // Will be detected at runtime so it "just works" from VS Code or terminal
    private static Path CARDS_BASE;

    private final ImageView[] slots = new ImageView[NUM_TO_SHOW];

    static {
        // Try the most likely locations first
        Path cwd = Paths.get(System.getProperty("user.dir"));
        Path[] candidates = new Path[] {
                cwd.resolve(Paths.get("Module-1", "Cards")),
                cwd.resolve("Cards"),
                // If launched from Module-1, this finds ../Module-1/Cards again (safe)
                cwd.getParent() != null ? cwd.getParent().resolve(Paths.get("Module-1", "Cards")) : null
        };
        for (Path p : candidates) {
            if (p != null && Files.exists(p)) {
                CARDS_BASE = p;
                break;
            }
        }
        if (CARDS_BASE == null) {
            // Fallback to the expected assignment layout
            CARDS_BASE = Paths.get("Module-1", "Cards");
        }
        System.out.println("Using cards directory: " + CARDS_BASE.toAbsolutePath());
    }

    @Override
    public void start(Stage stage) {
        HBox cardsRow = new HBox(16);
        cardsRow.setPadding(new Insets(16));
        cardsRow.setAlignment(Pos.CENTER);

        for (int i = 0; i < NUM_TO_SHOW; i++) {
            ImageView iv = new ImageView();
            iv.setFitWidth(160);
            iv.setPreserveRatio(true);
            iv.setSmooth(true);
            slots[i] = iv;
            cardsRow.getChildren().add(iv);
        }

        Button refreshBtn = new Button("Refresh");
        refreshBtn.setOnAction(e -> showRandomCards()); // lambda expression

        BorderPane root = new BorderPane();
        root.setCenter(cardsRow);
        BorderPane.setAlignment(refreshBtn, Pos.CENTER);
        BorderPane.setMargin(refreshBtn, new Insets(0, 0, 16, 0));
        root.setBottom(refreshBtn);

        showRandomCards(); // initial deal

        Scene scene = new Scene(root, 900, 420);
        stage.setTitle("Random Cards (JavaFX)");
        stage.setScene(scene);
        stage.show();
    }

    /** Selects 4 unique random card numbers (1..52) and updates the image views. */
    private void showRandomCards() {
        List<Integer> deck = IntStream.rangeClosed(1, NUM_CARDS).boxed().collect(Collectors.toList());
        Collections.shuffle(deck);
        List<Integer> hand = deck.subList(0, NUM_TO_SHOW);

        for (int i = 0; i < NUM_TO_SHOW; i++) {
            int cardNum = hand.get(i);
            slots[i].setImage(loadCard(cardNum));
        }
    }

    /** Loads a card image by number from Module-1/Cards/{n}.png */
private Image loadCard(int n) {
    String baseDir = "C:/Users/K1tt3/csd/CSD-420/Module-1/Cards/";
    return new Image("file:" + baseDir + n + ".png");
}


    public static void main(String[] args) {
        launch(args);
    }
}