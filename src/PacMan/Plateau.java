package PacMan;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Plateau extends Application {


    @Override
    public void start(Stage primaryStage) {

        BorderPane bPane = new BorderPane();

        Scene scene = new Scene(bPane, Color.BURLYWOOD);
        primaryStage.setTitle("PacManFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}