package PacMan.Model;

public abstract class Entite implements Runnable {

    protected Direction currDirection;
    protected int posX;
    protected int posY;
    protected boolean isAlive;
    protected long waitTime;
    protected Jeu jeu;


    public Entite(int posX, int posY, Jeu jeu) {
        this.currDirection = Direction.NOT_A_DIRECTION;
        this.posX = posX;
        this.posY = posY;
        this.isAlive = true;
        this.waitTime = 1000;
        this.jeu = jeu;
    }

    protected abstract void realiserAction();

    protected abstract void deplacement(int nextX, int nextY);

    @Override
    public void run() {
        while(!this.jeu.finPartie()) {
            this.realiserAction();
            this.jeu.update();

            try {

                Thread.sleep(this.waitTime);

            } catch(InterruptedException e) {
                System.err.println("Interrupt");
            }
        }

    }

    public int getPosX() { return this.posX; }

    public int getPosY() { return this.posY; }
    
    public Direction getDirection() { return this.currDirection; }
}
