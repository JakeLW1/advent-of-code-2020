import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
//imports babay

public class day2 {

	public static void main(String[] args) {
		
		String fileName = "./resources/passwords.csv"; //find file
		File file = new File(fileName); //make file into file
		
		List<String> passwordComponents = new ArrayList<>(); //empty string list
		Scanner inputStream; //start scanner
		
		try {
			inputStream = new Scanner(file); //read file
			
			while(inputStream.hasNext()) {
				String stuff = inputStream.next(); //assign stuff to string
				String[] components = stuff.split(",|\\-|\\:"); //assign strings to array split by dash, space and colon
				
				passwordComponents.addAll(Arrays.asList(components)); //shove array into list
			}
			
			inputStream.close(); //close read
		}catch(FileNotFoundException e) {
			e.printStackTrace(); //error stuff
		}
		int correctCount = 0; //counting correct passwords
		
		for(int i = 0; i < passwordComponents.size(); i++) {
			if(i % 4 == 0) { //every 4th element (could've been done better with for loop)
				int charCount = 0; //counting how many charaters appear in the password
				int min = Integer.valueOf(passwordComponents.get(i)); //first number in array is min value
				int max = Integer.valueOf(passwordComponents.get(i + 1)); //second is max
				String Sletter = passwordComponents.get(i + 2); //third is the letter
				char letter = Sletter.charAt(0); //convert to char (dunno why)
				String password = passwordComponents.get(i + 3); //fourth element is password
					try {
						if(password.charAt(min - 1) == letter) {
							charCount++;
						} //if char at index -1 (thanks shopkeeper) is the specified letter then charcount + 1
						if(password.charAt(max - 1) == letter) {
							charCount++;
						}
				}catch(StringIndexOutOfBoundsException e) {
					e.printStackTrace();
				} //String index out of bounds error bugger off
					
				if(charCount == 1) {
					correctCount++;
				} //if ONLY 1 char is either at min or max number then correct count +1
			}
		}
		System.out.println(correctCount); //print that sucker
		
	}

}
