package Unit2.GPS;

public class Location {
    double x;
    double y;
    String name;
    Location next;

    public Location(String name, double x, double y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public Location() {
        this("Home Base", 0, 0);
    }

    @Override
    public String toString() {
        return "" + name + ": (" + x + ", " + y + ") -> " + next;
    }
}
