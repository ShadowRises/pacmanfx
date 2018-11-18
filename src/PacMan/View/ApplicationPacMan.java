package PacMan.View;

import PacMan.View.Pane.Menu;
import PacMan.View.Pane.Plateau;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

public class ApplicationPacMan extends Application {
    private Menu menu;
    private Plateau plateau;

    @Override
    public void start(Stage primaryStage) throws Exception {
        AudioClip test2 = new AudioClip(System.class.getResource("/sound/pacman_beginning.wav").toExternalForm());
        test2.play();

        menu = new Menu();
        plateau = new Plateau();

        menu.playButton.setOnMouseClicked((click) -> {
            Scene scene = new Scene(plateau);
            primaryStage.setScene(scene);
        });

        menu.exitButton.setOnMouseClicked((click) -> {
            System.exit(0);
        });

        Scene scene = new Scene(menu);

        primaryStage.setTitle("PacMan FX");
        primaryStage.setMinWidth(500);
        primaryStage.setMinHeight(500);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(System.class.getResourceAsStream("/icons/logo.png")));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
