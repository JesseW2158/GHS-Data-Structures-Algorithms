package Unit2.GPS;

public class Tester {
    public static void main(String[] args) {
    }

    public static void testSwap() {
        Route route = new Route();

        Location library = new Location("Library", 2, 1);
        Location park = new Location("Park", 5, 1);
        Location cafe = new Location("Cafe", 7, 4);
        Location museum = new Location("Museum", 2, 6);

        route.addLocation(library);
        route.addLocation(park);
        route.addLocation(cafe);
        route.addLocation(museum);

        System.out.println("Original Route:");
        System.out.println(route);
        // Library -> Park -> Cafe -> Museum

        System.out.println("Original Distance: " + route.routeDistance());
        // Expected: 20.55 (approx)

        route.swap(library, museum);
        System.out.println("\nAfter Swap:");
        System.out.println(route);
        // Museum -> Park -> Cafe -> Library

        double totalDistance = route.routeDistance();
        System.out.println("Calculated Distance: " + totalDistance);
        // Expected: 23.82 (approx)
    }

    public static void testLongestLegAndRouteDistance() {
        Route route = new Route(new Location("Chick Fil A", 4, 4));

        route.addLocation(new Location("McDonalds", 16, 514));
        route.addLocation(new Location("Ed Sheeran Concert", 3, 1));

        System.out.println(route); // Home Base: (0.0, 0.0) -> Chick Fil A: (4.0, 4.0) -> McDonalds: (16.0, 514.0)
                                   // -> Ed Sheeran Concert: (3.0, 1.0) -> null

        // Distance between Home Base & Chick Fil A: 5.656854249
        // Distance between Chick Fil A & McDonalds: 510.1411569
        // Distance between McDonalds & Ed Sheeran Concert: 513.1646909
        // Distance between Ed Sheeran Concert & Home Base: 3.16227766

        System.out.println(route.longestLeg()); // should return 513.16
        System.out.println(route.routeDistance()); // should return 1032.12498
    }

    public static void testRemove1() {
        Route route = new Route();

        route.addLocation(new Location("GHS", 3, 5));
        route.addLocation(new Location("Chick-fil-a", 2, -6));
        route.addLocation(new Location("Home Depot", -3, 9));
        route.addLocation(new Location("Central Rock Gym", 1, 2));

        System.out.println("Should be 42.986: " + route.routeDistance());
        route.removeLocation(0);
        System.out.println("Should be 38.525: " + route.routeDistance());
    }

    public static void testRemove2() {
        Route route = new Route();
        
        route.addLocation(new Location());
        route.addLocation(new Location());
        route.addLocation(new Location());
        route.addLocation(new Location());
        route.addLocation(new Location());
        route.addLocation(new Location());
        route.addLocation(new Location("A", 3, 2));

        route.removeLocation(new Location());
        System.out.println(route);
    }
}
