package PacMan.Model;

public enum Direction {

    UP ("UP"),
    DOWN ("DOWN"),
    LEFT ("LEFT"),
    RIGHT ("RIGHT"),
    NOT_A_DIRECTION ("");

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
