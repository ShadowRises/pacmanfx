/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PacMan.View.Pane;

import PacMan.View.Components.MenuButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author Paul
 */
public class EndScreen extends StackPane{
    
    public MenuButton replayButton;
    public MenuButton exitButton;
    private ImageView banniere;
    
    public EndScreen(boolean victory) {
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        this.setMinWidth(300);
        this.setMinHeight(200);
        if (victory) {
            banniere = new ImageView(new Image(System.class.getResourceAsStream("/icons/banniere.png")));
        } else {
            banniere = new ImageView(new Image(System.class.getResourceAsStream("/icons/gameover.png")));
        }

        StackPane.setAlignment(banniere, Pos.TOP_CENTER);

        this.getChildren().add(banniere);

        replayButton = new MenuButton("Replay");
        replayButton.setTextAlignment(TextAlignment.CENTER);

        StackPane.setAlignment(replayButton, Pos.CENTER);

        this.getChildren().add(replayButton);

        exitButton = new MenuButton("Exit");
        exitButton.setTextAlignment(TextAlignment.CENTER);

        StackPane.setAlignment(exitButton, Pos.BOTTOM_CENTER);

        this.getChildren().add(exitButton);
    }
}
