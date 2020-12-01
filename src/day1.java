import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
//import stuff

public class day1 {

	public static void main(String[] args) {
		
		String fileName = "./resources/expenses.csv"; //find file
		File file = new File(fileName); //make file into file
		
		List<String> expenses = new ArrayList<>(); //empty string list
		Scanner inputStream; //start scanner
		
		try {
			inputStream = new Scanner(file); //read file
			
			while(inputStream.hasNext()) {
				String expense = inputStream.next(); //assign number to string
				String[] values = expense.split(","); //assign strings to array split by a comma
				
				expenses.addAll(Arrays.asList(values)); //shove array into list
			}
			
			inputStream.close(); //close read
		}catch(FileNotFoundException e) {
			e.printStackTrace(); //error stuff
		}
		
		List<Integer> numExpenses = new ArrayList<>(); //empty integer list
		
		for(String s : expenses) numExpenses.add(Integer.valueOf(s)); //put string list into integer list
		
		for(int i = 0; i < numExpenses.size(); i++) {
			for(int j = 0; j < numExpenses.size(); j++) {
				for(int k = 0; k < numExpenses.size(); k++) {
					if(numExpenses.get(i) + numExpenses.get(j) + numExpenses.get(k) == 2020) { //if all values make 2020
						System.out.println(numExpenses.get(i) * numExpenses.get(j) * numExpenses.get(k)); //multiply them bad boys and print that baby
						break;
					}
				}
				
			}
		}
	}

}