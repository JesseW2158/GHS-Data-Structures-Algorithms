package Unit2.GPS;

public class Location {
    double x;
    double y;
    String name;
    Location next;

    public Location(double x, double y, String name, Location next) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.next = next;
    }

    public Location(double x, double y, String name) {
        this(x, y, name, null);
    }

    @Override
    public String toString() {
        return "" + name + ": (" + x + ", " + y + ") -> " + next;
    }
}
