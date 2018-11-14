package PacMan.Model;

public enum Direction {

    HAUT ("haut"),
    BAS ("bas"),
    GAUCHE ("gauche"),
    DROITE ("droite");

    private String name;

    Direction(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
