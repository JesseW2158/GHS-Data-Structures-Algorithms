package Unit2.GPS;

public class Tester {
    public static void main(String[] args) {
        Route route = new Route();
        Location gasStation = new Location("GasStation", 3, 0);
        Location centralRock = new Location("CentralRock", 3, 4);
        Location pizzaShop = new Location("PizzaShop", 6, 4);
        Location bookStore = new Location("BookStore", 6, 8);

        route.addLocation(gasStation);
        route.addLocation(centralRock);
        route.addLocation(pizzaShop);
        route.addLocation(bookStore);

        System.out.println("Original Route:");
        System.out.println(route.toString());
        System.out.println("Original Distance: " + route.routeDistance());

        route.swap(pizzaShop, bookStore);

        System.out.println("\nAfter Swap:");
        System.out.println(route.toString());
        double totalDistance = route.routeDistance();
        System.out.println("Expected Distance: 23.211");
        System.out.println("Calculated Distance: " + totalDistance);
    }
}
