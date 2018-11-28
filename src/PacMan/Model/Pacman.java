package PacMan.Model;

import javafx.scene.media.AudioClip;

public class Pacman extends Entite {

    public Pacman(int posX, int posY, Jeu jeu) {
        super(posX, posY, jeu);
        this.waitTime = 500;
    }

    @Override
    protected void realiserAction() {

        int nextX = this.posX;
        int nextY = this.posY;

        switch (this.currDirection) {
            case UP:
                nextY = this.posY - 1;
                break;

            case DOWN:
                nextY = this.posY + 1;
                break;

            case LEFT:
                nextX = this.posX - 1;
                break;

            case RIGHT:
                nextX = this.posX + 1;
                break;

            case NOT_A_DIRECTION:
                break;
        }

        if(nextX == -1)
            nextX += Jeu.LONGUEUR;

        if(nextY == -1)
            nextY += Jeu.LARGEUR;

        deplacement(nextX % Jeu.LONGUEUR, nextY % Jeu.LARGEUR);
    }

    @Override
    protected void deplacement(int nextX, int nextY) {

        synchronized (this) {
            AudioClip pacmanChomp = new AudioClip(System.class.getResource("/sound/pacman_chomp.wav").toExternalForm());
            pacmanChomp.play();
            
            Case[][] plateau = this.jeu.plateau;
            Entite[][] tabEntite = this.jeu.tabEntite;

            if(plateau[nextX][nextY] instanceof Couloir) {
                Couloir nextPosition = (Couloir) plateau[nextX][nextY];

                tabEntite[this.posX][this.posY] = null;

                if(tabEntite[nextX][nextY] instanceof Fantome)
                    this.isAlive = false;

                else
                    tabEntite[nextX][nextY] = this;


                if(nextPosition.pacGomme) {

                    nextPosition.pacGomme = false;
                    this.jeu.score += 10;

                } else if(nextPosition.superPacGomme) {

                    nextPosition.superPacGomme = false;
                    this.jeu.score += 50;

                }

                this.posX = nextX;
                this.posY = nextY;

            }
        }

    }

}
