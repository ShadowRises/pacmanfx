package PacMan.Model;

import java.util.Random;

public class Fantome extends Entite {

    public Fantome(int posX, int posY, Jeu jeu) {
        super(posX, posY, jeu);
        this.waitTime = 400;
    }

    @Override
    protected void realiserAction() {

        int nextX = this.posX;
        int nextY = this.posY;

        Random rand = new Random();
        int randDir = rand.nextInt(4);

        switch (randDir) {
            case 0:
                this.currDirection = Direction.UP;
                break;

            case 1:
                this.currDirection = Direction.DOWN;
                break;

            case 2:
                this.currDirection = Direction.LEFT;
                break;

            case 3:
                this.currDirection = Direction.RIGHT;
                break;
        }

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

        synchronized(this) {

            Case[][] plateau = this.jeu.plateau;
            Entite[][] tabEntite = this.jeu.tabEntite;

            if(plateau[nextX][nextY] instanceof Couloir) {
                if(!(tabEntite[nextX][nextY] instanceof Fantome)) {
                    tabEntite[this.posX][this.posY] = null;

                    if(tabEntite[nextX][nextY] instanceof Pacman)
                        tabEntite[nextX][nextY].isAlive = false;

                    tabEntite[nextX][nextY] = this;

                }

                this.posX = nextX;
                this.posY = nextY;

            }

        }
    }

}
