package PacMan.Model;

public abstract class Entite implements Runnable {

    protected Direction currDirection;
    protected int posX;
    protected int posY;
    protected Jeu jeu;

    public Entite(int posX, int posY, Jeu jeu) {
        this.currDirection = Direction.NOT_A_DIRECTION;
        this.posX = posX;
        this.posY = posY;
        this.jeu = jeu;
    }

    protected abstract void realiserAction();

    // TODO
    @Override
    public void run() {
        while(!this.jeu.finPartie()) {
            System.out.println("RUN !!!");
            realiserAction();
            System.out.println(this.posX + " " + this.posY);
            this.jeu.update();

            try {

                Thread.sleep(500);

            } catch(InterruptedException e) {
                System.err.println("Interrupt");
            }
        }

    }

    public int getPosX() { return this.posX; }

    public int getPosY() { return this.posY; }

    public void setPosX(int posX) { this.posX = posX; }

    public void setPosY(int posY) { this.posY = posY; }

    @Override
    public String toString() {
        return " ";
    }
}
