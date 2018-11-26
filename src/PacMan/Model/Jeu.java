package PacMan.Model;

import PacMan.Model.Parser.Parser;
import javafx.application.Platform;

import java.io.IOException;
import java.util.Observable;

public class Jeu extends Observable {

    public static final int LONGUEUR = 21;
    public static final int LARGEUR = 21;

    public Thread pacmanThread;

    public Case[][] plateau;
    public Entite[][] tabEntite;

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
    }

    public void start() {

        for(int i = 0; i < this.LONGUEUR; i++) {
            for(int j = 0; j < this.LARGEUR; j++) {
                if(this.tabEntite[i][j] instanceof Pacman) {
                    this.pacmanThread = new Thread(this.tabEntite[i][j]);
                    this.pacmanThread.start();
                }
            }
        }
    }

    // TODO
    public boolean finPartie() {

        return false;
    }

    // TODO
    public void deplacer(Direction direction) {

        if(!direction.equals(Direction.NOT_A_DIRECTION)) {
            for(int i = 0; i < this.LONGUEUR; i++) {
                for(int j = 0; j < this.LARGEUR; j++) {
                    if(this.tabEntite[i][j] instanceof Pacman)
                        this.tabEntite[i][j].currDirection = direction;
                }
            }
        }
            //this.pacman.currDirection = direction;
        /*
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("1 - " + direction);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        for(int i = 0; i < this.LONGUEUR; i++) {
            for(int j = 0; j < this.LARGEUR; j++) {
                if(this.tabEntite[i][j] instanceof Pacman) {
                    System.err.println("PACMAN !!!");
                    if(!direction.equals(Direction.NOT_A_DIRECTION)) {
                        this.tabEntite[i][j].currDirection = direction;
                    }
                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    System.out.println(this.tabEntite[i][j].currDirection);
                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    return;
                }
            }
        }
        */
    }

    public void update() {
        Platform.runLater(() -> {
            setChanged();
            notifyObservers();
        });
    }

}
