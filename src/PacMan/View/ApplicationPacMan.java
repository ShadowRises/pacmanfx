package PacMan.View;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ApplicationPacMan extends Application {
    private Menu menu;
    private Plateau plateau;

    @Override
    public void start(Stage primaryStage) throws Exception {
        menu = new Menu();
        plateau = new Plateau();

        menu.playButton.setOnMouseClicked((click) -> {
            Scene scene = new Scene(plateau);
            primaryStage.setScene(scene);
        });

        Scene scene = new Scene(menu);

        primaryStage.setTitle("PacMan FX");
        primaryStage.setMinWidth(200);
        primaryStage.setMinHeight(200);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(System.class.getResourceAsStream("/icons/logo.png")));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
