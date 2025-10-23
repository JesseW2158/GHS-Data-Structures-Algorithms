package Unit3.Foontry;

import java.util.HashSet;

public class Cuisine {
    HashSet<String> dishes;

    public Cuisine(HashSet<String> dishes) {
        super();
        this.dishes = dishes;
    }

    @Override
    public String toString() {
        return "[dishes=" + dishes + "]";
    }
}
