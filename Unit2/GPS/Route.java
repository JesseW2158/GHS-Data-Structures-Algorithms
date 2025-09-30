package Unit2.GPS;

public class Route {
    Location start;
    Location current;

    public Route(Location start) {
        this.start = start;
        this.current = this.start;
    }

    public boolean addLocation(Location loc) {
        Location runner = current;

        while(runner.next != null) {
            runner = runner.next;
        }

        runner.next = loc;

        return true;
    }

    public boolean addLocation(int spot, Location loc) {
        Location runner = current;

        for(int i = 0; i < spot; i++) {
            runner = runner.next;
        }

        Location temp = runner.next;
        runner.next = loc;
        loc.next = temp;

        return true;
    }

    @Override
    public String toString() {
        return "" + start;
    }
}
