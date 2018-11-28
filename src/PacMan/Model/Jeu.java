package PacMan.Model;

import PacMan.Model.Parser.Parser;
import javafx.application.Platform;

import java.io.IOException;
import java.util.Observable;

public class Jeu extends Observable {

    public static final int LONGUEUR = 21;
    public static final int LARGEUR = 21;

    private Pacman pacman;
    public Thread pacmanThread;

    public Case[][] plateau;
    public Entite[][] tabEntite;

    public int score;

    public Jeu() {
        this.plateau = new Case[this.LONGUEUR][this.LARGEUR];
        this.tabEntite = new Entite[this.LONGUEUR][this.LARGEUR];

        try {

            initialiser();

        } catch (Exception e) {
            System.err.println("Erreur lors de la création du plateau !\n" + e.getMessage());
            System.exit(1);
        }
    }


    private void initialiser() throws IOException {

        Parser parser = new Parser(this);
        this.plateau = parser.createPlateau();
        this.tabEntite = parser.createEntite();

        this.score = 0;
    }

    public void start() {

        for(int i = 0; i < this.LONGUEUR; i++) {
            for(int j = 0; j < this.LARGEUR; j++) {
                if(this.tabEntite[i][j] instanceof Pacman) {

                    this.pacman = (Pacman) this.tabEntite[i][j];
                    this.pacmanThread = new Thread(this.pacman);
                    this.pacmanThread.start();

                }
            }
        }
    }

    // TODO
    public boolean finPartie() {

        if(this.pacman.isAlive) {

            for(int i = 0; i < this.LONGUEUR; i++) {
                for(int j = 0; j < this.LARGEUR; j++) {

                    if(this.plateau[i][j] instanceof Couloir) {
                        Couloir couloir = (Couloir) this.plateau[i][j];

                        if(couloir.pacGomme || couloir.superPacGomme)
                            return false;
                    }

                }
            }

            // Debug
            System.err.println("End");

        }
        // Debug
        else System.err.println("Dead");

        return true;
    }

    public void deplacer(Direction direction) {

        if(!direction.equals(Direction.NOT_A_DIRECTION)) {
            for(int i = 0; i < this.LONGUEUR; i++) {
                for(int j = 0; j < this.LARGEUR; j++) {
                    if(this.tabEntite[i][j] instanceof Pacman)
                        this.tabEntite[i][j].currDirection = direction;
                }
            }
        }
    }

    public void update() {
        Platform.runLater(() -> {
            setChanged();
            notifyObservers();
        });
    }

    public Pacman getPacman() { return this.pacman; }

}
