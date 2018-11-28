package PacMan.View.Pane;

import PacMan.Model.*;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.Observable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class Plateau extends BorderPane {

    private static final int COTE_CARRE = 30;
    private static final int RAD_PACGOMME = 3;
    private static final int RAD_SUPERPACGOMME = 7;

    private Jeu jeu;
    private GridPane gPane;
    private StackPane sPane;

    public Plateau () {

        // Model initialization
        this.jeu = new Jeu();

        this.gPane = new GridPane();
        
        this.sPane = new StackPane();

        gPane.setAlignment(Pos.CENTER);
        sPane.setAlignment(Pos.CENTER_LEFT);
        
        jeu.start();
        
        this.draw();
        
        this.setCenter(gPane);
        this.setTop(sPane);
    }

    public void draw() {
        gPane.getChildren().clear();
        System.out.println("DRAW !!!!!!!");
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
                        ImageView imgPacman = null;
                                
                        switch (jeu.getPacman().getDirection()){
                            case UP:
                                imgPacman = new ImageView(new Image(System.class.getResourceAsStream("/icons/Pacman_top.png")));
                                break;
                                
                            case DOWN:
                                imgPacman = new ImageView(new Image(System.class.getResourceAsStream("/icons/Pacman_bottom.png")));
                                break;
                                
                            case LEFT:
                                imgPacman = new ImageView(new Image(System.class.getResourceAsStream("/icons/Pacman_left.png")));
                                break;
                                
                            case RIGHT:
                                imgPacman = new ImageView(new Image(System.class.getResourceAsStream("/icons/Pacman_right.png")));
                                break;
                                
                            case NOT_A_DIRECTION:
                                imgPacman = new ImageView(new Image(System.class.getResourceAsStream("/icons/Pacman_right.png")));
                                break;
                        }
                        
                        stackPane.getChildren().add(imgPacman);

                    } else  if (jeu.tabEntite[i][j] instanceof Fantome){
                        ImageView imgFantome = null;
                        switch (jeu.tabEntite[i][j].getDirection()){
                            case UP:
                                imgFantome = new ImageView(new Image(System.class.getResourceAsStream("/icons/Fantome_top.png")));
                                break;
                                
                            case DOWN:
                                imgFantome = new ImageView(new Image(System.class.getResourceAsStream("/icons/Fantome_bottom.png")));
                                break;
                                
                            case LEFT:
                                imgFantome = new ImageView(new Image(System.class.getResourceAsStream("/icons/Fantome_left.png")));
                                break;
                                
                            case RIGHT:
                                imgFantome = new ImageView(new Image(System.class.getResourceAsStream("/icons/Fantome_right.png")));
                                break;
                                
                            case NOT_A_DIRECTION:
                                imgFantome = new ImageView(new Image(System.class.getResourceAsStream("/icons/Fantome_right.png")));
                                break;
                        }
                        stackPane.getChildren().add(imgFantome);

                    }

                } else {

                    rectangle.setFill(Color.BLACK);
                    stackPane.getChildren().addAll(rectangle);
                }

                gPane.add(stackPane, i, j);
            }
        }
        
        sPane.getChildren().clear();
        
        Text score = new Text("Score : " + jeu.score);
        
        sPane.getChildren().add(score);
    }

    public Jeu getJeu() { return this.jeu; }
}