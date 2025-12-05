package Unit3.Foodie;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class Hashcodes {

	public static void main(String[] args) throws FileNotFoundException {		
		Object object = new Object();
		System.out.println(object.hashCode());
		Object object2 = new Object();
		System.out.println(object2.hashCode());
		String str = "Code";
		System.out.println(str.hashCode());
		System.out.println('C'+0);
		System.out.println('o'+0);
		System.out.println('d'+0);
		System.out.println('e'+0);
		
		System.out.println("C".hashCode());
		System.out.println("Calorie counts");
		Map<String, Integer> calories = new HashMap<String, Integer>(10);
		calories.put("Apple", 95);
		calories.put("Banana", 105);
		System.out.println("adding");
		System.out.println((calories.put("Cucumber", 45)));
		System.out.println((calories.put("Cucumber", 55)));
		
		System.out.println(calories.get("Apple"));
		for(String key:calories.keySet())
			System.out.println(key + " : "+calories.get(key));
		
		
		//Key is food and country
		//value arraylist of dishes
		//Entree   <food&country,dishes>
		
		Scanner foods = new Scanner(new File("FoodList.txt"));
		Menu Bertuccis = new Menu();
		foods.nextLine();
		for(int i = 0; i<100;i++) {
			String line = foods.nextLine();
			String[] columns = line.split(",");
			Foontry key = new Foontry(columns[0],columns[1]);
			HashSet<String> dishes = new HashSet<String>();
			for(int j = 2; j<6;j++)
				dishes.add(columns[j]);
			Cuisine value = new Cuisine(dishes);
			Bertuccis.add(new Entree(key,value));
		}
		System.out.println(Bertuccis);
		System.out.println(Bertuccis.size);
		System.out.println(Bertuccis.eat(new Foontry("Egg","Greece")));
		System.out.println(Bertuccis);
		Bertuccis.get(new Foontry("Wheat","Mexico")).dishes.remove("Marquesote Cake");
		System.out.println(Bertuccis);
		
		for(Object ent:Bertuccis) {
			System.out.println(ent);
		}
		
		foods.close();
	}

}
