import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class day3 {

	public static void main(String[] args) {
		
		String fileName = "./resources/"
				+ "input.txt"; //find file
		File file = new File(fileName); //make file into file
		
		List<String> map = new ArrayList<>(); //small map
		List<String> bigMap = new ArrayList<>(); //big map
		Scanner inputStream; //start scanner
		
		try {
			inputStream = new Scanner(file); //read file
			
			while(inputStream.hasNext()) {
				String row = inputStream.next(); //assign rows to string
				String[] rows = row.split("[\r?\n|\r]+", -1); //assign strings to array split by new line (ooooooooo regex)
				
				map.addAll(Arrays.asList(rows)); //add rows to map
			}
			inputStream.close(); //close read
		}catch(FileNotFoundException e) {
			e.printStackTrace(); //error stuff
		}
		
		for(int i = 0; i < map.size(); i++) { //loop through the whole map
			String row = map.get(i); //get each row and make it a string
			row = map.get(i).repeat(73); //repeat the string 73(!) times
			bigMap.add(row); //add it to BIG map
			System.out.println(row); //puke it out to console
		}
		
		System.out.println(bigMap.get(0).length()); //debug by logging lol
		
		int treeMiss1 = 0;
		int treeMiss2 = 0;
		int treeMiss3 = 0;
		int treeMiss4 = 0;
		int treeMiss5 = 0;
		int treeHit1 = 0;
		int treeHit2 = 0;
		int treeHit3 = 0;           //i'm sorry god don't look at this
		int treeHit4 = 0;
		int treeHit5 = 0;
		int checkpoint1 = 0;
		int checkpoint2 = 0;
		int checkpoint3 = 0;
		int checkpoint4 = 0;
		int checkpoint5 = 0;
		
		//repeating for-loops, stepping through each row of map and jumping by however long the challenge wants
		for(int i = 0; i < bigMap.size(); i++) {
			for(int j = checkpoint2; j < bigMap.get(i).length(); j++) {
				if(bigMap.get(i).charAt(j) == '.') {
					treeMiss2++;
					break;
				}
				if(bigMap.get(i).charAt(j) == '#') {
					treeHit2++;
					break;
				}
			}
			checkpoint2++;
		}
		for(int i = 0; i < bigMap.size(); i++) {
			for(int j = checkpoint1; j < bigMap.get(i).length(); j+=3) {
				if(bigMap.get(i).charAt(j) == '.') {
					treeMiss1++;
					break;
				}
				if(bigMap.get(i).charAt(j) == '#') {
					treeHit1++;
					break;
				}
			}
			checkpoint1+=3;
		}
		for(int i = 0; i < bigMap.size(); i++) {
			for(int j = checkpoint3; j < bigMap.get(i).length(); j+=5) {
				if(bigMap.get(i).charAt(j) == '.') {
					treeMiss3++;
					break;
				}
				if(bigMap.get(i).charAt(j) == '#') {
					treeHit3++;
					break;
				}
			}
			checkpoint3+=5;
		}
		for(int i = 0; i < bigMap.size(); i++) {
			for(int j = checkpoint4; j < bigMap.get(i).length(); j+=7) {
				if(bigMap.get(i).charAt(j) == '.') {
					treeMiss4++;
					break;
				}
				if(bigMap.get(i).charAt(j) == '#') {
					treeHit4++;
					break;
				}
			}
			checkpoint4+=7;
		}
		for(int i = 0; i < bigMap.size(); i+=2) {
			for(int j = checkpoint5; j < bigMap.get(i).length(); j++) {
				if(bigMap.get(i).charAt(j) == '.') {
					treeMiss5++;
					break;
				}
				if(bigMap.get(i).charAt(j) == '#') {
					treeHit5++;
					break;
				}
			}
			checkpoint5++;
		}
		long totalHits = 1;
		totalHits *= treeHit1;
		totalHits *= treeHit2;
		totalHits *= treeHit3;       //*multiplication*
		totalHits *= treeHit4;
		totalHits *= treeHit5;
		System.out.println("misses: " + treeMiss1*treeMiss2*treeMiss3*treeMiss4*treeMiss5 + ", hits: " + totalHits); //answer goes here
		System.out.println(treeHit1 + " " + treeHit2 + " " + treeHit3 + " " + treeHit4 + " " + treeHit5);
		System.out.println(checkpoint5); //debug by printing you know the score
	}

}
