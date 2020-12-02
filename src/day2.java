import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class passwordChecker {

	public static void main(String[] args) {
		
		String fileName = "passwords.csv"; //find file
		File file = new File(fileName); //make file into file
		
		List<String> passwordComponents = new ArrayList<>(); //empty string list
		Scanner inputStream; //start scanner
		
		try {
			inputStream = new Scanner(file); //read file
			
			while(inputStream.hasNext()) {
				String stuff = inputStream.next(); //assign number to string
				String[] components = stuff.split(",|\\-|\\:"); //assign strings to array split by dash, space and colon
				
				passwordComponents.addAll(Arrays.asList(components)); //shove array into list
			}
			
			inputStream.close(); //close read
		}catch(FileNotFoundException e) {
			e.printStackTrace(); //error stuff
		}
		int correctCount = 0;
		
		for(int i = 0; i < passwordComponents.size(); i++) {
			if(i % 4 == 0) {
				int charCount = 0;
				int min = Integer.valueOf(passwordComponents.get(i));
				int max = Integer.valueOf(passwordComponents.get(i + 1));
				String Sletter = passwordComponents.get(i + 2);
				char letter = Sletter.charAt(0);
				String password = passwordComponents.get(i + 3);
					try {
						if(password.charAt(min - 1) == letter) {
							charCount++;
						}
						if(password.charAt(max - 1) == letter) {
							charCount++;
						}
				}catch(StringIndexOutOfBoundsException e) {
					e.printStackTrace();
				}
					
				if(charCount == 1) {
					correctCount++;
				}
			}
		}
		System.out.println(correctCount);
		
	}

}
