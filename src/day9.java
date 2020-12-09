import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//tropmi

public class day9 {

	public static void main(String[] args) {

		String fileName = "./resources/encoding.txt"; //find file
		File file = new File(fileName); //make file into file
		
		List<Long> number = new ArrayList<Long>(); //empty long list
		Scanner inputStream; //start scanner
		
		try {
			inputStream = new Scanner(file); //read file
			
			while(inputStream.hasNext()) { //while there's still stuff in the txt file
				
				try {
					Long num = Long.parseLong(inputStream.nextLine()); //assign line to string
					number.add(num); //add the numbers to a list
				} catch(NumberFormatException ex) {
					ex.printStackTrace(); //had errors with int being too short so needed a long
				}
			}
			
			inputStream.close(); //close read
		}catch(FileNotFoundException e) {
			e.printStackTrace(); //error stuff
		}
		long numInQuestion = 1492208709; //answer to part 1 used in part 2
		boolean done = false; //checking so it wont run when it's done
		
		for(int i = 0; i < number.size(); i++) { //index 1
    
			List<Long> nums = new ArrayList<>(); //make new list for numbers to be added up
			for(int j = 0; j < number.size(); j++) { //index 2
      
				if(done == false) { //checkaroo
        
					if(i + j < 1000) { //indexing error prevention squad
          
						nums.add(number.get(i + j)); //add in appropriate numbers to the list
						long sum = 0; //count
						for(int k = 0; k < nums.size(); k++) { //loop through adding array
            
							sum = sum + nums.get(k); //sum all the elements
							if(numInQuestion == sum) { //if the sum of the adding array == part 1
              
								System.out.println(number.get(i) + nums.get(j)); //print it
								done = true; //set done to true
								break; //done
							}
							
						}
					}
					else {
						break; //done
					}
				}
				else {
					break; //done
				}
				
			}
		}
	}
}
