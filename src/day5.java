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
		
		List<String> passes = new ArrayList<>(); //small map
		Scanner inputStream; //start scanner
		
		try {
			inputStream = new Scanner(file); //read file
			
			while(inputStream.hasNext()) {
				String row = inputStream.next(); //assign rows to string
				String[] rows = row.split("[\n]+", -1); //assign strings to array split by new line (ooooooooo regex)
				
				passes.addAll(Arrays.asList(rows)); //add rows to map
			}
			inputStream.close(); //close read
		}catch(FileNotFoundException e) {
			e.printStackTrace(); //error stuff
		}
		List<Integer> values = new ArrayList<>();
		
		for(int i = 0; i < passes.size(); i++) {
			int upperRow = 127;
			int lowerRow = 0;
			int row = 0;
			int upperColumn = 7;
			int lowerColumn = 0;
			int column = 0;
			for(int j = 0; j < passes.get(i).length(); j++) {
				if(passes.get(i).charAt(j) == 'F') {
					upperRow = (upperRow + lowerRow) / 2;
				}
				else if(passes.get(i).charAt(j) == 'B') {
					lowerRow = (upperRow + lowerRow) / 2;
				}
				else if(passes.get(i).charAt(j) == 'R') {
					lowerColumn = (lowerColumn + upperColumn) / 2;
				}
				else if(passes.get(i).charAt(j) == 'L') {
					upperColumn = (upperColumn + lowerColumn) /2;
				}
			}
			column = upperColumn;
			row = upperRow;
			
			values.add((row * 8) + column);
		}
		Collections.sort(values);
		for(int i = 0; i < values.size(); i++) {
			if((values.get(i) + 1) != values.get(i + 1) && values.get(i + 1) != (values.get(i) - 1)) {
				System.out.println(values.get(i) + 1);
			}
		}

	}

}
