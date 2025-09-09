package Stacks.Bears;

public class Bear {
    private String name;
    private double weight;
    private int species;

    /**
     * Constructor
     * @param name
     * @param weight
     * @param species
     */
    public Bear(String name, double weight, int species) {
        this.name = name;
        this.weight = weight;
        this.species = species;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getSpecies() {
        return species;
    }

    public void setSpecies(int species) {
        this.species = species;
    }

    @Override
    public String toString() {
        return "Bear [name=" + name + ", weight=" + weight + ", species=" + species + "]";
    }
}
