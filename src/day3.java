import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class tabogganTrajectory {

	public static void main(String[] args) {
		
		String fileName = "input.txt"; //find file
		File file = new File(fileName); //make file into file
		
		List<String> map = new ArrayList<>();
		List<String> bigMap = new ArrayList<>();
		Scanner inputStream; //start scanner
		
		try {
			inputStream = new Scanner(file); //read file
			
			while(inputStream.hasNext()) {
				String row = inputStream.next(); //assign number to string
				String[] rows = row.split("[\r?\n|\r]+", -1); //assign strings to array split by new line
				
				map.addAll(Arrays.asList(rows));
			}
			inputStream.close(); //close read
		}catch(FileNotFoundException e) {
			e.printStackTrace(); //error stuff
		}
		
		for(int i = 0; i < map.size(); i++) {
			String row = map.get(i);
			row = map.get(i).repeat(57);
			bigMap.add(row);
			System.out.println(row);
		}
		
		System.out.println(bigMap.get(0).length());
		
		int treeMiss1 = 0;
		int treeMiss2 = 0;
		int treeMiss3 = 0;
		int treeMiss4 = 0;
		int treeMiss5 = 0;
		int treeHit1 = 0;
		int treeHit2 = 0;
		int treeHit3 = 0;
		int treeHit4 = 0;
		int treeHit5 = 0;
		int checkpoint1 = 0;
		int checkpoint2 = 0;
		int checkpoint3 = 0;
		int checkpoint4 = 0;
		int checkpoint5 = 0;
		
		for(int i = 1; i < bigMap.size(); i++) {
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
		for(int i = 1; i < bigMap.size(); i++) {
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
		for(int i = 1; i < bigMap.size(); i++) {
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
		for(int i = 1; i < bigMap.size(); i++) {
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
		for(int i = 1; i < bigMap.size(); i+=2) {
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
		long totalHits = treeHit1 * treeHit2 * treeHit3 * treeHit4 * treeHit5;
		System.out.println("misses: " + treeMiss1*treeMiss2*treeMiss3*treeMiss4*treeMiss5 + ", hits: " + totalHits);
		System.out.println(treeHit1 + " " + treeHit2 + " " + treeHit3 + " " + treeHit4 + " " + treeHit5);
		System.out.println(checkpoint5);
	}

}
