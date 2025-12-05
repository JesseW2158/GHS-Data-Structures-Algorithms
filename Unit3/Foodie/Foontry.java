package Unit3.Foodie;

import java.util.Objects;

public class Foontry {
	String food;
	String Country;
	public Foontry(String food, String country) {
		super();
		this.food = food;
		Country = country;
	}
	@Override
	public String toString() {
		return "[food=" + food + ", Country=" + Country + "]";
	}
	@Override
	public int hashCode() {
		int code = 0;
		for(char c:Country.toCharArray())
			code+=c;
		for(char c:food.toCharArray())
			code+=c;
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
		return Objects.equals(Country, other.Country) && Objects.equals(food, other.food);
	}
	
	

}
