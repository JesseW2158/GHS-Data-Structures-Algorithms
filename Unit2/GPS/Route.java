package Unit2.GPS;

public class Route {
    Location start;
    Location current;

    public Route() {
        this.start = new Location();
        this.current = start;
    }

    public Route(Location next) {
        this();
        current.next = next;
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
        if(spot == 0) {
            loc.next = start;
            start = loc;

            return true;
        }

        Location runner = current;

        for(int i = 0; i < spot - 1; i++) {
            runner = runner.next;
        }

        Location temp = runner.next;
        runner.next = loc;
        loc.next = temp;

        return true;
    }

    public double routeDistance() {
        Location runner = start;
        double distance = 0;

        while(runner.next != null) {
            distance += Math.hypot(runner.x - runner.next.x, runner.y - runner.next.y);
            runner = runner.next;
        }

        distance +=  Math.hypot(runner.x - start.x, runner.y - start.y);

        return distance;
    }

    @Override
    public String toString() {
        return "" + start;
    }
}
