package PacMan.Model;

import PacMan.Model.IA.AStar;
import PacMan.Model.IA.Node;

import java.util.ArrayList;
import java.util.Random;

public class Fantome extends Entite {

    public static final long WAIT_TIME = 450;
    public static final long FEAR_WAIT_TIME = 750;

    public boolean isFear;
    public boolean isDead;
    public String color;

    public Fantome(int posX, int posY, Jeu jeu, String color) {
        super(posX, posY, jeu);
        this.color = color;
        this.waitTime = this.WAIT_TIME;

        this.isFear = false;
        this.isDead = false;
    }

    @Override
    protected void realiserAction() throws InterruptedException {

        if(this.isDead) {

            for(int i = 0; i < Jeu.LONGUEUR; i++) {
                for(int j = 0; j < Jeu.LARGEUR; j++) {

                    if(this.jeu.plateau[i][j] instanceof Couloir) {
                        Couloir c = (Couloir) this.jeu.plateau[i][j];

                        if(c.respawn) {
                            this.posX = i;
                            this.posY = j;
                        }
                    }

                }
            }

            Thread.sleep(1000);
            this.isDead = false;
            this.waitTime = this.WAIT_TIME;
        }

        int nextX = this.posX;
        int nextY = this.posY;

        if(!this.isFear) {
            AStar aStar = new AStar(this.jeu.plateau, new Node(this.posX, this.posY), new Node(this.jeu.getPacman().posX, this.jeu.getPacman().posY));
            ArrayList<Node> path = aStar.performAStar();

            this.currDirection = path.get(path.size() - 1).getDirection();

        } else {

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

                    this.posX = nextX;
                    this.posY = nextY;
                }

            }

        }
    }

    @Override
    protected void decreaseTimeSuperRemaining() {}

}
