package Unit2.GPS;

public class Route {
    Location start;
    Location current;
    int size;

    /**
     * Creates a Home Base at 0, 0
     */
    public Route() {
        this.start = new Location();
        this.current = start;
        size = 1;
    }

    /**
     * Creates a home base and initalizes the next location on the route, connecting
     * it to home base
     * 
     * @param next
     */
    public Route(Location next) {
        this();
        current.next = next;
        size++;
    }

    /**
     * Traverses route until last location and adds the new location to the old
     * final location, making the new one the new final location
     * 
     * @param loc
     * @return location added successfully
     */
    public boolean addLocation(Location loc) {
        Location runner = current;

        while (runner.next != null) {
            runner = runner.next;
        }

        runner.next = loc;
        size++;

        return true;
    }

    /**
     * Traverses route until the specified location and adds the new location to the
     * location, and setting the new location's next to the old location's next
     * 
     * @param spot
     * @param loc
     * @return location added successfully
     */
    public boolean addLocation(int spot, Location loc) {
        // Ensures spot to be inserted in is not greater or less than the physical size
        // of the route and the location is not null to ensure it doesn't cut off the
        // rest of the routes
        if (spot > size || spot < 0 || loc == null) {
            return false;
        }

        // Ensures changing the start doesn't crash the program
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

    /**
     * Calculates using distance formula for each point and adding them together
     * 
     * @return total distance of the route including trip back to start
     */
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

    /**
     * Reverses the entire route after the starting location
     */
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

    /**
     * 
     * 
     * @param loc
     * @return location sucessfully removed
     */
    public boolean removeLocation(Location loc) {
        boolean found = false;

        // Starting temp node to make it so we can remove the starting location if
        // needed
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

        start = temp.next; // If start does get removed, this will make it be the next location

        size--;

        return found;
    }

    /**
     * Travereses the route until reaching spot, removing the location and setting
     * the next of the previous location to the next location of the removed
     * location
     * 
     * @param spot
     * @return location successfully removed
     */
    public boolean removeLocation(int spot) {
        // Ensures program doesn't crash because of a invalid spot
        if (spot >= size || spot < 0) {
            return false;
        }

        // Ensures if start is removed, program doesn't crash
        if (spot == 0) {
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

    /**
     * 
     * @param spot
     * @param loc
     * @return location successfully set
     */
    public boolean setLocation(int spot, Location loc) {
        // Ensures program doesn't crash because of invalid spot and doesn't cut off the
        // route because of a null location
        if (spot > size || spot < 0 || loc == null) {
            return false;
        }

        // Sets the location to the end of the route instead of the normal way so it
        // doesnt crash
        if (spot == size) {
            this.addLocation(loc);
            return true;
        }

        // Sets the location to the start of the route instead of the normal way so it
        // doesnt crash
        if (spot == 0) {
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

    /**
     * Calculates the longest portion of the route with distance formula on each point and returns the max
     * @return longest portion of the route
     */
    public double longestLeg() {
        double max = 0;
        Location runner = start;

        while (runner.next != null) {
            double distance = Math.hypot(runner.x - runner.next.x, runner.y - runner.next.y);
            max = (max < distance) ? distance : max;

            runner = runner.next;
        }

        return (max > Math.hypot(runner.x - start.x, runner.y - start.y)) ? max : Math.hypot(runner.x - start.x, runner.y - start.y);
    }

    /**
     * Swaps two locations in route
     * 
     * @param loc1
     * @param loc2
     * @return swap done successfully
     */
    public boolean swap(Location loc1, Location loc2) {
        //Nothing to be done if locations are the same
        if (loc1 == loc2) {
            return true;
        }

        //Invalid input check so program doesn't crash
        if (loc1 == null || loc2 == null) {
            return false;
        }

        //Pointers to track previous location of each found location
        Location prevLoc1 = null, frontLoc1 = null;
        Location prevLoc2 = null, frontLoc2 = null;
        Location runner = start;

        //Finds location 1 and keeps the location's previous location
        while (runner != null) {
            if (runner.name == loc1.name) {
                frontLoc1 = runner;
                break;
            }
            prevLoc1 = runner;
            runner = runner.next;
        }

        //Restarts runner
        runner = start;

        //Finds location 2 and keeps the location's previous location
        while (runner != null) {
            if (runner.name == loc2.name) {
                frontLoc2 = runner;
                break;
            }
            prevLoc2 = runner;
            runner = runner.next;
        }

        // Check if either one has not been found
        if (frontLoc1 == null || frontLoc2 == null) {
            return false;
        }

        //Special case for head, allows for switching the head of the route with another
        if (prevLoc1 != null) {
            prevLoc1.next = frontLoc2;
        } else {
            start = frontLoc2;
        }

        if (prevLoc2 != null) {
            prevLoc2.next = frontLoc1;
        } else {
            start = frontLoc1;
        }

        //Switches the two locations
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