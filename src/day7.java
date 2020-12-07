import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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
		
		int count = 0;
		List<String> bagsToProcess = new ArrayList<>();
		List<Integer> nums = new ArrayList<>();
		
		bagsToProcess.add("shiny gold");
		nums.add(1);
		
		while(!bagsToProcess.isEmpty()) {
			String bag = bagsToProcess.get(bagsToProcess.size()-1);
			int num = nums.get(nums.size() - 1);
			bagsToProcess.remove(bagsToProcess.size() - 1);
			nums.remove(nums.size() - 1);
			
			int j = 0;
			while(!bags.get(j).startsWith(bag))
				j++;
			
			String line = bags.get(j);
			String[] contents = line.substring(line.indexOf("contain") + 8).replace(".", "").split(", ");
			
			if(!contents[0].equals("no other bags")) {
				for(String content : line.substring(line.indexOf("contain") + 8).replace(".", "").split(", ")) {
					int subCount = Integer.parseInt(content.substring(0, content.indexOf(" ")).trim());
					bagsToProcess.add(content.substring(content.indexOf(" ")).replace("bags", "").trim());
					nums.add(subCount * num);
					count += subCount * num;
				}
			}
		}
		System.out.println(count);
	}
}
