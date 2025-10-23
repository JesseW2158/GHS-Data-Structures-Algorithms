package Unit3.Foontry;

public class Entree {
    Foontry key;
    Cuisine value;
    
    public Entree(Foontry key, Cuisine value) {
        super();
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "[key=" + key + ", value=" + value + "]";
    }
}
