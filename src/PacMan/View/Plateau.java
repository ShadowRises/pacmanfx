package PacMan.View;

import PacMan.Model.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Plateau extends Application {


    @Override
    public void start(Stage primaryStage) {
        
        Jeu jeu = new Jeu();
        
        BorderPane bPane = new BorderPane();
        GridPane gPane = new GridPane();
        gPane.setGridLinesVisible(true);
        gPane.setAlignment(Pos.CENTER);
        
        for (int i = 0; i < Jeu.LONGUEUR; i++){
            for (int j = 0; j < Jeu.LARGEUR; j++){
                StackPane stackPane = new StackPane();
                Rectangle rectangle = new Rectangle(30,30);
                if (jeu.plateau[i][j] instanceof Mur){
                    rectangle.setFill(Color.DARKBLUE);
                    stackPane.getChildren().addAll(rectangle);
                } else if (jeu.plateau[i][j] instanceof Couloir){
                    Couloir couloir = (Couloir) jeu.plateau[i][j];
                    rectangle.setFill(Color.BLACK);
                    if (couloir.superPacGomme){
                        Circle circle = new Circle(0, 0, 7, Color.WHITE);
                        stackPane.getChildren().addAll(rectangle, circle);
                    } else if (couloir.pacGomme){
                        Circle circle = new Circle(0, 0, 2, Color.WHITE);
                        stackPane.getChildren().addAll(rectangle, circle);
                    }
                } else {
                    rectangle.setFill(Color.MIDNIGHTBLUE);
                    stackPane.getChildren().addAll(rectangle);
                }
                gPane.add(stackPane, i, j);
            }
        }
        
        bPane.setCenter(gPane);

        Scene scene = new Scene(bPane, Color.BURLYWOOD);

        primaryStage.setTitle("PacMan FX");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}