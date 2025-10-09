package Unit2.GPS;

public class Tester {
    public static void main(String[] args) {
        Route route = new Route(new Location("Chick Fil A", 4, 4));

        route.addLocation(new Location("McDonalds", 16, 514));
        route.addLocation(new Location("Ed Sheeran Concert", 3, 1));

        System.out.println(route); //Home Base: (0.0, 0.0) -> Chick Fil A: (4.0, 4.0) -> McDonalds: (16.0, 514.0) -> Ed Sheeran Concert: (3.0, 1.0) -> null

        //Distance between Home Base & Chick Fil A: 5.656854249
        //Distance between Chick Fil A & McDonalds: 510.1411569
        //Distance between McDonalds & Ed Sheeran Concert: 513.1646909
        //Distance between Ed Sheeran Concert & Home Base: 3.16227766

        System.out.println(route.longestLeg()); // should return 513.16
        System.out.println(route.routeDistance()); //should return 1032.12498
    }
}
