package PacMan.View;

import PacMan.Model.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Plateau extends BorderPane {

    private static final int COTE_CARRE = 30;
    private static final int RAD_PACGOMME = 3;
    private static final int RAD_SUPERPACGOMME = 7;

    public Plateau () {

        // Model initialization
        Jeu jeu = new Jeu();

        GridPane gPane = new GridPane();

        gPane.setGridLinesVisible(true);
        gPane.setAlignment(Pos.CENTER);
        
        for (int i = 0; i < Jeu.LONGUEUR; i++){
            for (int j = 0; j < Jeu.LARGEUR; j++){

                StackPane stackPane = new StackPane();
                Rectangle rectangle = new Rectangle(COTE_CARRE,COTE_CARRE);

                if (jeu.plateau[i][j] instanceof Mur){

                    rectangle.setFill(Color.DARKBLUE);
                    stackPane.getChildren().addAll(rectangle);

                } else if (jeu.plateau[i][j] instanceof Couloir){

                    Couloir couloir = (Couloir) jeu.plateau[i][j];
                    rectangle.setFill(Color.BLACK);

                    if (couloir.superPacGomme) {

                        Circle circle = new Circle(0, 0, RAD_SUPERPACGOMME, Color.WHITE);
                        stackPane.getChildren().addAll(rectangle, circle);

                    } else if (couloir.pacGomme) {

                        Circle circle = new Circle(0, 0, RAD_PACGOMME, Color.WHITE);
                        stackPane.getChildren().addAll(rectangle, circle);

                    } else
                        stackPane.getChildren().addAll(rectangle);

                    if (jeu.tabEntite[i][j] instanceof Pacman){

                        Circle circle = new Circle(0, 0, 10, Color.YELLOW);
                        stackPane.getChildren().add(circle);

                    } else  if (jeu.tabEntite[i][j] instanceof Fantome){

                        Circle circle = new Circle(0, 0, 10, Color.MEDIUMPURPLE);
                        stackPane.getChildren().add(circle);

                    }

                } else {

                    rectangle.setFill(Color.BLACK);
                    stackPane.getChildren().addAll(rectangle);
                }

                gPane.add(stackPane, i, j);
            }
        }

        this.setCenter(gPane);
    }
}