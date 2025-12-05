package Unit3.Foodie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class HashPractice {

	public static void main(String[] args) {		
		HashMap<String, Set<String>> fruitMap = new HashMap<>();
		Set<String> fruits = new HashSet<>(Set.of("Banana","Lemon","Apple")); 
	
		fruitMap.put("Yellow",fruits);
		fruits = new HashSet<>(Set.of("Apple","Strawberry"));
		fruitMap.put("Red",fruits);
		fruits = new HashSet<>(Set.of("Apple","Kiwi","Banana"));
		fruitMap.put("Green",fruits);
		
		
		//add a Red Raspberry to the map
		fruitMap.get("Red").add("Raspberry");
		//remove brown fruits
		fruitMap.remove("Brown");
		String[] moreGreen = {"Melon","Avocado"};
		for(String green:moreGreen)
			fruitMap.get("Green").add(green);
		System.out.println(printHash(fruitMap));
		
		for(String key:fruitMap.keySet())
			if(fruitMap.get(key).contains("Apple"))
				System.out.println(key);
		ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1,2,3,4,5,6));
		/*
		for(Integer i:numbers)
			if(i%2==0)
				numbers.remove(i);*/
		/*BAD #2
		for(String key:fruitMap.keySet()) {
			if(key.contains("R"))
				fruitMap.remove(key);
		}*/
		Iterator<Integer> numIt = numbers.iterator();
		
		while(numIt.hasNext()) {
			Integer temp = numIt.next();
			if(temp%2==0)
				numIt.remove();
		}
		System.out.println(numbers);
		

	}
	public static String printHash(HashMap<String,Set<String>> map) {
		String output = "";
		for(String key:map.keySet()) {
			output += key + "->";
			for(String fruit:map.get(key))
				output +=fruit + ",";
			output+="\n";
		}
		return output;
	}

}
