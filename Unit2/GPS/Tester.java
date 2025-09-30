package Unit2.GPS;

public class Tester {
    public static void main(String[] args) {
        Route route = new Route(new Location(0, 0, "GHS"));

        for(int i = 1; i < 5; i++) {
            route.addLocation(new Location(i, i, Integer.toString(i)));
        }

        System.out.println(route);

        route.addLocation(0, null)
    }
}
