package PacMan.Model;

import PacMan.Model.Parser.Parser;

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


    // TODO
    private void initialiser() throws IOException {

        Parser parser = new Parser();
        this.plateau = parser.createPlateau();

        //for(int i = 0; i < this.LONGUEUR; i++) {
        //    for(int j = 0; j < this.LARGEUR; j++) {
        //
        //        if(i == 0 || i == 19 || j == 0 || j == 19) {
        //            this.plateau[i][j] = new Mur();
        //
        //        } else {
        //            this.plateau[i][j] = new Couloir(true, false);
        //        }
        //    }
        //}
    }

    // TODO
    public boolean finPartie() {

        return false;
    }

    // TODO
    public void deplacer(Entite entite, Direction direction) {
        entite.currDirection = direction;
    }

}
