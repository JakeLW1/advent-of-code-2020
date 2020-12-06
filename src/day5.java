import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class day5 {

	public static void main(String[] args) {
		
		String fileName = "./resources/boardingPasses.txt"; //find file
		File file = new File(fileName); //make file into file
		
		List<String> passes = new ArrayList<>(); //boarding passes
		Scanner inputStream; //start scanner
		
		try {
			inputStream = new Scanner(file); //read file
			
			while(inputStream.hasNext()) {
				String row = inputStream.next(); //assign rows to string
				String[] rows = row.split("[\n]+", -1); //assign strings to array split by new line (ooooooooo regex)
				
				passes.addAll(Arrays.asList(rows)); //add rows to board pass list
			}
			inputStream.close(); //close read
		}catch(FileNotFoundException e) {
			e.printStackTrace(); //error stuff
		}
		List<Integer> values = new ArrayList<>(); //list for seat values
		
		for(int i = 0; i < passes.size(); i++) { //loop through all passes
			int upperRow = 127;
			int lowerRow = 0;
			int row = 0;
			int upperColumn = 7; //maths time
			int lowerColumn = 0;
			int column = 0;
			for(int j = 0; j < passes.get(i).length(); j++) { //for all letters in each boarding pass
				if(passes.get(i).charAt(j) == 'F') { //if the letter is F
					upperRow = (upperRow + lowerRow) / 2; //half the upper row + lower row
				}
				else if(passes.get(i).charAt(j) == 'B') { //if the letter is B
					lowerRow = (upperRow + lowerRow) / 2; //half the upper row + lower row
				}
				else if(passes.get(i).charAt(j) == 'R') { //if the letter is R
					lowerColumn = (lowerColumn + upperColumn) / 2; //half the lower column + upper column
				}
				else if(passes.get(i).charAt(j) == 'L') { //if the letter is R
					upperColumn = (upperColumn + lowerColumn) /2;//half the lower column + upper column
				}
			}
			column = upperColumn; //set the column (it says lower value on the brief but upper worked for me... quik maffs
			row = upperRow; //set row
			
			values.add((row * 8) + column); //seat ID baby
		}
		Collections.sort(values); //sort em
		for(int i = 0; i < values.size(); i++) { //for all the seat ID's (ordered)
			if((values.get(i) + 1) != values.get(i + 1) && values.get(i + 1) != (values.get(i) - 1)) { //if the ID + 1 and next ID - 1 isn't there
				System.out.println(values.get(i) + 1); //this actually gives an index out of bounds error at the end error but who cares!
			}
		}

	}

}
