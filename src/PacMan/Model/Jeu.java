package PacMan.Model;

import java.util.Observable;

public class Jeu extends Observable {

    public static final int LONGUEUR = 20;
    public static final int LARGEUR = 20;

    public Case[][] plateau;
    public Entite[][] tabEntite;

    public Jeu() {
        this.plateau = new Case[this.LONGUEUR][this.LARGEUR];
        this.tabEntite = new Entite[this.LONGUEUR][this.LARGEUR];

        initialiser();
    }


    // TODO
    private void initialiser() {

        for(int i = 0; i < this.LONGUEUR; i++) {
            for(int j = 0; j < this.LARGEUR; j++) {

                if(i == 0 || i == 19 || j == 1 || j == 19) {
                    this.plateau[i][j] = new Mur();

                } else {
                    this.plateau[i][j] = new Couloir(true, false);
                }
            }
        }
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
