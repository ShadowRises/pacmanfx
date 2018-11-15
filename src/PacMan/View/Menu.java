package PacMan.View;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class Menu extends AnchorPane {

    public Button playButton;

    public Menu() {
        this.setMinWidth(300);
        this.setMinHeight(200);

        playButton = new Button("Play");

        AnchorPane.setTopAnchor(playButton, 100.0);
        AnchorPane.setLeftAnchor(playButton, 100.0);

        this.getChildren().add(playButton);
    }
}
