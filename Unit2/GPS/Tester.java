package Unit2.GPS;

public class Tester {
    public static void main(String[] args) {
        Route route = new Route(new Location("Chick Fil A", 4, 4));

        route.addLocation(new Location("McDonalds", 16, 514));
        route.addLocation(new Location("Ed Sheeran Concert", 3, 1));

        System.out.println(route); //Home Base: (0.0, 0.0) -> Chick Fil A: (4.0, 4.0) -> McDonalds: (16.0, 514.0) -> Ed Sheeran Concert: (3.0, 1.0) -> null

        route.reverseRoute();

        System.out.println(route);
    }
}
