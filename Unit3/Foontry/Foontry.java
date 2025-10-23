package Unit3.Foontry;

import java.util.Objects;

public class Foontry {
    String food;
    String country;

    public Foontry(String food, String country) {
        this.food = food;
        this.country = country;
    }

    @Override
    public int hashCode() {
        int code = 0;

        for(char c : country.toCharArray()) {
            code += c;
        }

        for(char c: food.toCharArray()) {
            code += c;
        }

        return code;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Foontry other = (Foontry) obj;
        if (food == null) {
            if (other.food != null)
                return false;
        } else if (!food.equals(other.food))
            return false;
        if (country == null) {
            if (other.country != null)
                return false;
        } else if (!country.equals(other.country))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "[food=" + food + ", country=" + country + "]";
    }
}