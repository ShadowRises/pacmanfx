package PacMan.View;

import PacMan.Model.Direction;
import PacMan.View.Pane.Menu;
import PacMan.View.Pane.Plateau;
import PacMan.Model.Jeu;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import java.util.Observable;
import java.util.Observer;

public class ApplicationPacMan extends Application {
    private Menu menu;
    private Plateau plateau;

    @Override
    public void start(Stage primaryStage) throws Exception {
        AudioClip test2 = new AudioClip(System.class.getResource("/sound/pacman_beginning.wav").toExternalForm());
        test2.play();

        menu = new Menu();

        menu.playButton.setOnMouseClicked((click) -> {
            plateau = new Plateau();
            Scene scene = new Scene(plateau);
            Jeu jeu = plateau.getJeu();

            primaryStage.setScene(scene);

            scene.setOnKeyPressed(key -> {
                Direction direction;
                switch(key.getCode()) {
                    case UP:
                        direction = Direction.UP;
                        break;

                    case DOWN:
                        direction = Direction.DOWN;
                        break;

                    case LEFT:
                        direction = Direction.LEFT;
                        break;

                    case RIGHT:
                        direction = Direction.RIGHT;
                        break;

                    default:
                        direction = Direction.NOT_A_DIRECTION;
                        break;
                }

                jeu.deplacer(direction);

            });

            jeu.addObserver(new Observer() {
                @Override
                public void update(Observable observable, Object o) {
                    System.out.println("UPDATE !!!!");
                    plateau.draw();

                }
            });

            /**
             * Stop all thread to properly close the game
             */
            primaryStage.setOnCloseRequest((e) -> {
                jeu.pacmanThread.stop();
            });

        });

        menu.exitButton.setOnMouseClicked((click) -> {
            Platform.exit();
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