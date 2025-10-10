package Unit2.GPS;

public class Route {
    Location start;
    Location current;
    int size;

    public Route() {
        this.start = new Location();
        this.current = start;
        size = 1;
    }

    public Route(Location next) {
        this();
        current.next = next;
        size++;
    }

    public boolean addLocation(Location loc) {
        Location runner = current;

        while (runner.next != null) {
            runner = runner.next;
        }

        runner.next = loc;
        size++;

        return true;
    }

    public boolean addLocation(int spot, Location loc) {
        if (spot > size + 1 || spot < 0) {
            return false;
        }

        if (spot == 0) {
            loc.next = start;
            start = loc;

            return true;
        }

        Location runner = current;

        for (int i = 0; i < spot - 1; i++) {
            runner = runner.next;
        }

        Location temp = runner.next;
        runner.next = loc;
        loc.next = temp;

        size++;

        return true;
    }

    public double routeDistance() {
        Location runner = start;
        double distance = 0;

        while (runner.next != null) {
            distance += Math.hypot(runner.x - runner.next.x, runner.y - runner.next.y);
            runner = runner.next;
        }

        distance += Math.hypot(runner.x - start.x, runner.y - start.y);

        return distance;
    }

    public void reverseRoute() {
        if (start == null || start.next == null) {
            return;
        }

        Location front = start.next.next;
        Location back = start.next;
        back.next = null;

        while (front != null) {
            Location temp = front.next;
            front.next = back;
            back = front;
            front = temp;
        }

        start.next = back;
    }

    public boolean removeLocation(Location loc) {
        boolean found = false;

        Location temp = new Location();
        Location runner = temp;

        temp.next = start;

        while (runner.next != null) {
            if (runner.next.name == loc.name) {
                found = true;
                runner.next = runner.next.next;
            } else {
                runner = runner.next;
            }
        }

        start = temp.next;

        size--;

        return found;
    }

    public boolean removeLocation(int spot) {
        if (spot > size || spot < 0) {
            return false;
        }

        if(spot == 0) {
            start = start.next;
            return true;
        }

        Location runner = start;

        for (int i = 0; i < spot - 1; i++) {
            runner = runner.next;
        }

        runner.next = runner.next.next;

        size--;

        return true;
    }

    public boolean setLocation(int spot, Location loc) {
        if (spot > size) {
            return false;
        }

        if(spot == size) {
            this.addLocation(loc);
            return true;
        }

        if(spot == 0) {
            Location temp = start.next;
            start = loc;
            start.next = temp;

            return true;
        }

        Location runner = start;

        for (int i = 0; i < spot - 1; i++) {
            runner = runner.next;
        }

        Location temp = runner.next.next;
        runner.next = loc;
        loc.next = temp;

        return true;
    }

    public double longestLeg() {
        double max = 0;
        Location runner = start;

        while (runner.next != null) {
            double distance = Math.hypot(runner.x - runner.next.x, runner.y - runner.next.y);
            max = (max < distance) ? distance : max;

            runner = runner.next;
        }

        return max;
    }

    public boolean swap(Location loc1, Location loc2) {
        if(loc1 == loc2) {
            return true;
        }

        Location prevLoc1 = null, frontLoc1 = null;
        Location prevLoc2 = null, frontLoc2 = null;
        Location runner = start;

        while (runner != null) {
            if (runner.name == loc1.name) {
                frontLoc1 = runner;
                break;
            }
            prevLoc1 = runner;
            runner = runner.next;
        }

        runner = start; 

        // Second loop to find y
        while (runner != null) {
            if (runner.name == loc2.name) {
                frontLoc2 = runner;
                break;
            }
            prevLoc2 = runner;
            runner = runner.next;
        }

        // If either x or y is not present, nothing to do
        if (frontLoc1 == null || frontLoc2 == null) {
            return false;
        }

        // If x is not head of the linked list
        if (prevLoc1 != null) {
            prevLoc1.next = frontLoc2;
        } else {
            start = frontLoc2; 
        }

        // If y is not head of the linked list
        if (prevLoc2 != null) {
            prevLoc2.next = frontLoc1;
        } else {
            start = frontLoc1;
        }

        // Swap next pointers
        Location temp = frontLoc2.next;
        frontLoc2.next = frontLoc1.next;
        frontLoc1.next = temp;

        return true;
    }

    @Override
    public String toString() {
        return "" + start;
    }
}