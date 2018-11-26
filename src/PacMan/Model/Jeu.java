package PacMan.Model;

import PacMan.Model.Parser.Parser;
import javafx.application.Platform;

import java.io.IOException;
import java.util.Observable;

public class Jeu extends Observable {

    public static final int LONGUEUR = 21;
    public static final int LARGEUR = 21;

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

        for(int i = 0; i < Jeu.LONGUEUR; i++) {
            for(Entite e : this.tabEntite[i]) {
                if(e instanceof Pacman)
                    new Thread(e).start();
            }
        }
    }

    // TODO
    public boolean finPartie() {

        return false;
    }

    // TODO
    public void deplacer(Direction direction) {

        for(int i = 0; i < this.LONGUEUR; i++) {
            for(int j = 0; j < this.LARGEUR; j++) {
                if(this.tabEntite[i][j] instanceof Pacman) {
                    if(!direction.equals(Direction.NOT_A_DIRECTION)) {
                        this.tabEntite[i][j].currDirection = direction;
                    }
                    break;
                }
            }
        }
    }

    public void update() {
        this.setChanged();
        this.notifyObservers();
    }

}
