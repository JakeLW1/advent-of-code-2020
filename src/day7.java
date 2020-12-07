import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class day7 {

	public static void main(String[] args) {
		
		String fileName = "bags.txt"; //find file
		File file = new File(fileName); //make file into file
		
		List<String> bags = new ArrayList<>(); //empty string list
		Scanner inputStream; //start scanner
		
		try {
			inputStream = new Scanner(file); //read file
			
			while(inputStream.hasNext()) {
				
				String bag = inputStream.nextLine(); //assign number to string
				String[] components = bag.split("\\n"); //assign strings to array split by new line
				
				bags.addAll(Arrays.asList(components)); //shove array into list
			}
			
			inputStream.close(); //close read
		}catch(FileNotFoundException e) {
			e.printStackTrace(); //error stuff
		}
		
		List<String> holder = new ArrayList<>();
		holder.add("1 shiny gold");
		int count = 0;
		HashMap<String, Integer> quantities = new HashMap<>();
		quantities.put("1 shiny gold", 1);
		
		for(int c = 0; c < 6; c++) {
			for(int i = 0; i < bags.size(); i++) {
				String[] split = bags.get(i).split(" bags contain ");
				
				String goldHolder = split[0];
				for(int j = 0; j < holder.size(); j++) {
					
					if(holder.get(j).contains(goldHolder)) {
						String[] contains = split[1].split(", ");
						for(int k = 0; k < contains.length; k++) {
							if(split[1].equals("no other bags.")) {
								break;
							}
							else {
								quantities.put(contains[k].substring(2, contains[k].length() - 4), Integer.valueOf(contains[k].substring(0,1)));
								holder.add(contains[k].substring(0, contains[k].length() - 4));
							}
							
						}
					}
				}
			}
		}
		
		Set<String> set = new LinkedHashSet<>(holder);
		holder.clear();
		holder.addAll(set);
		
		for(int i = 0; i < bags.size(); i++) {
			String[] split = bags.get(i).split(" bags contain ");
			

			String[] contents = split[1].split(", ");
			for(int j = 0; j < holder.size(); j++) {
				String goldHolder = split[0];
				if(holder.get(j).contains(goldHolder)) {
					System.out.println(holder.get(j) + " layer " + j);
					
				} //I GIVE UP
			}
		}
	}
}
