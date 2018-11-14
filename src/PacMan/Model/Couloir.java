package PacMan.Model;

public class Couloir extends Case {

    public boolean pacGomme;
    public boolean superPacGomme;

    public Couloir(boolean pacGomme, boolean superPacGomme) {
        this.pacGomme = pacGomme;
        this.superPacGomme = superPacGomme;
    }
}
