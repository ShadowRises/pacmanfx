package PacMan.View;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Plateau extends Application {


    @Override
    public void start(Stage primaryStage) {

        BorderPane bPane = new BorderPane();
        GridPane gPane = new GridPane();
        gPane.setGridLinesVisible(true);

        Rectangle rect = new Rectangle(200, 200);
        rect.setFill(Color.BLUEVIOLET);

        Rectangle rect2 = new Rectangle(200, 200);
        rect2.setFill(Color.CHARTREUSE);

        gPane.add(rect, 0, 0);
        gPane.add(rect2, 1, 0);

        rect2.setOnMouseClicked(event -> {
            if(rect2.getFill().equals(Color.CHARTREUSE))
                rect2.setFill(Color.RED);
            else
                rect2.setFill(Color.CHARTREUSE);
        });

        bPane.setCenter(gPane);

        Scene scene = new Scene(bPane, Color.BURLYWOOD);

        primaryStage.setTitle("PacMan FX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}