import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class day10 {

	public static void main(String[] args) {
		
		String fileName = "./resources/joltages.txt"; //find file
		File file = new File(fileName); //make file into file
		
		List<Integer> joltages = new ArrayList<Integer>(); //empty long list
		Scanner inputStream; //start scanner
		
		try {
			inputStream = new Scanner(file); //read file
			
			while(inputStream.hasNext()) { //while there's still stuff in the txt file
				
				try {
					int joltage = Integer.parseInt(inputStream.nextLine()); //assign line to string
					joltages.add(joltage); //add the numbers to a list
				} catch(NumberFormatException ex) {
					ex.printStackTrace(); //had errors with int being too short so needed a long
				}
			}
			
			inputStream.close(); //close read
		}catch(FileNotFoundException e) {
			e.printStackTrace(); //error stuff
		}
		
//		partOne(joltages);
		partTwo(joltages);

	}
	
	public static void partOne(List<Integer> input) {
		Collections.sort(input);
		int oneJump = 0;
		int threeJump = 0;
		for(int i = 0; i < input.size(); i++) {
			if(input.get(i) == 1) {
				oneJump++;
			}
			if(i < input.size() - 1) {
				if((input.get(i) + 1) == input.get(i + 1)) {
					oneJump++;
				}
				else if((input.get(i) + 3) == input.get(i + 1)) {
					threeJump++;
				}
			}
			else {
				threeJump++;
			}
		}
		System.out.println(oneJump * threeJump);
	}
	
	public static void partTwo(List<Integer> input) {
		
		Collections.sort(input);
		for(int i = 0; i < input.size(); i++) {
			
			List<Integer> copy = new ArrayList<>();
			copy.addAll(input);
			boolean skipped = false;
			boolean[] hasSkipped = new boolean[copy.size()];
			
			for(int j = 0; j < copy.size(); j++) {
				if(i + 2 < copy.size()) {
					if(copy.get(i + 2) == (copy.get(i) + 2)) {
						copy.remove(i + 1);
						System.out.println(copy);
						partTwo(copy);
					}
				}
				if(i + 3 < copy.size()) {
					if(copy.get(i + 3) == (copy.get(i) + 3)) {
						copy.remove(i + 1);
						copy.remove(i + 2);
						System.out.println(copy);
						partTwo(copy);
						
					}
				}
			}
		}
	}
}
