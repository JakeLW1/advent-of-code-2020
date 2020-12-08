import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
//importicus

public class day8 {

	public static void main(String[] args) {
		
		String fileName = "./resources/gameLoop.txt"; //find file
		File file = new File(fileName); //make file into file
		
		List<String> loops = new ArrayList<>(); //empty string list
		Scanner inputStream; //start scanner
		
		try {
			inputStream = new Scanner(file); //read file
			
			while(inputStream.hasNext()) {
				
				String line = inputStream.nextLine(); //assign line to string
				String[] lines = line.split("\n"); //assign lines to array split by new line
				
				loops.addAll(Arrays.asList(lines)); //shove array into list
			}
			
			inputStream.close(); //close read
		}catch(FileNotFoundException e) {
			e.printStackTrace(); //error stuff
		}

		boolean[] hasConverted = new boolean[loops.size()]; //track if a conversion has been attempted
		
		for(int i = 0; i < loops.size(); i++) { //for all the loops
			List<String> copy = new ArrayList<>(); //copycat (aka me thanks again Joe)
			copy.addAll(loops); //populate it
			boolean[] hasExecuted = new boolean[loops.size()]; //track if a check has been made on this
			
			
			if(copy.get(i).contains("jmp")) { //if the instruction is jump
				if(hasConverted[i] == false) { //and there hasn't been a conversion
					copy.set(i, "nop" + copy.get(i).substring(3, copy.get(i).length())); //make it a nop
					hasConverted[i] = true; //and set converted to true
				}
			}
			else if(copy.get(i).substring(0, 3).contains("nop")) { //same here but flipped around
				if(hasConverted[i] == false) {
					copy.set(i, "jmp" + copy.get(i).substring(3, copy.get(i).length()));
					hasConverted[i] = true;
				}
			}
			
			if(hasConverted[i] == true) { //if its converted
				
				int acc = 0; //accumulator count
				
				for(int j = 0; j < copy.size(); j++) { //for the whole copied list
				
					String instruction = copy.get(j).substring(0, 3); //set instruction
					String operator = copy.get(j).substring(4,5); //set operator (pea brain forgot that you can store negative values in int's but work with me here)
					int value = Integer.valueOf(copy.get(j).substring(5, copy.get(j).length())); //value as int
					
					if(hasExecuted[j] == false) { //if the copied version hasn't been tried
						hasExecuted[j] = true; //set it to true
						if(instruction.contains("jmp")) { //if its jmp
							if(value != 0) //and not 0
								if(operator.contains("-")) { //if minus
									j -= (value + 1); //minus from the for loop's index
								}
								else //if plus
									j += (value - 1); //add it to index
							else {
								continue; //if it's 0 just go to next
							}
						}
						else if(instruction.contains("acc")) { //for accumulator
							if(operator.contains("-"))
								acc -= value; //minus value if negative
							else if(operator.contains("+"))
								acc += value; //or add if positive
						}
						else {
							continue; //nop means just carry on
						}
					}
					else {
						break; //skip if its been executed before
					}
					if(j == loops.size() - 1) { //if the index is the very end
						System.out.println(acc); //print the accumulator and we have our answer!
						break;
					}
				}
			}
		}
	}
}
