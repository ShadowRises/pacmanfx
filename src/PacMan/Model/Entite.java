package PacMan.Model;

public abstract class Entite implements Runnable {

    protected Direction currDirection;

    protected abstract void realiserAction();

    // TODO
    @Override
    public void run() {

    }
}
