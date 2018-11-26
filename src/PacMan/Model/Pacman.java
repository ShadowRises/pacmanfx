package PacMan.Model;

public class Pacman extends Entite {

    public Pacman(int posX, int posY, Jeu jeu) {
        super(posX, posY, jeu);
    }

    // TODO
    @Override
    protected void realiserAction() {

        Case[][] plateau = this.jeu.plateau;
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
        System.out.println("next :");
        System.out.println(nextX + " " + nextY);
        deplacement(nextX, nextY);
    }

    private void deplacement(int nextX, int nextY) {
        synchronized (this) {

            Case[][] plateau = this.jeu.plateau;
            Entite[][] tabEntite = this.jeu.tabEntite;

            if(plateau[nextX][nextY] instanceof Couloir) {

                tabEntite[nextX][nextY] = this;
                if(nextX != this.posX && nextY != this.posY)
                    tabEntite[this.posX][this.posY] = null;
                this.posX = nextX;
                this.posY = nextY;
                /*
                for(int i = 0; i < Jeu.LONGUEUR; i++) {

                    for(int j = 0; j < Jeu.LARGEUR; j++) {
                        Entite e = tabEntite[i][j];

                        if(e instanceof Pacman) {
                            Pacman pacman = (Pacman) e;
                            tabEntite[nextX][nextY] = e;
                            e.setPosX(nextX);
                            e.setPosY(nextY);
                            tabEntite[i][j] = null;
                        }
                    }
                }
                */
            }

        }
    }

    @Override
    public String toString() {
        return "P";
    }
}
