import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
//i m p o r t

public class day6 {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new FileReader("./resources/groups.txt")); //read that file
		
		List<String> groups = new ArrayList<String>(); //blank list for all the groups
        String str =""; //blankety blank
        while(true) //keep doing this!
        {
            String tmp = in.readLine(); //read a line in and store temporarily
            if(tmp==null){
                break; //if temp is blank break
            }
            else if(tmp.isEmpty()){
                if(!str.isEmpty()){
                	groups.add(str); //if temp is empty but string isnt then add to groups (accounting for blank line)
                }
                str= ""; //make str empty if temp is empty
            }
            else{
                if(str.isEmpty()){
                    str = tmp; //if str is empty make it tmp
                }
                else{
                    str += "\n" + tmp; //add new line and tmp to str
                }
            }

        }
        if(!str.isEmpty())
        {
        	groups.add(str); //if str is full add it!
        }
        
        in.close(); //in is now closed
        int count = 0; //count

        for(int i = 0; i < groups.size(); i++) { //loop through all groups
        	String group = groups.get(i); //get each group
    		String[] individuals = group.split("[\n]"); //and split them by new line to make a string array
    		List<Character> chars = new ArrayList<>(); //empty list for the characters
    		for(int j = 0; j < individuals.length; j++) { //for every individual
    			for(int k = 0; k < individuals[j].length(); k++) { //and every character that appears
    				chars.add(individuals[j].charAt(k)); //add to the char list
    			}
    		}
    		Collections.sort(chars); //sort them bad boys for validation
    		for(char c = 'a'; c <= 'z'; c++) { //for the whole alphabet
    			int timesCharOccurs = 0; //count how many times the character occurs
    			for(int j = 0; j < chars.size(); j++) { //for every character
    				if(chars.get(j) == c) { //if its a letter
    					timesCharOccurs++; //then it appears
    					if(timesCharOccurs == individuals.length) { //if the amount of times it occurs is the same as the amount of people
			    			count++; //count it up baby
    					}
    				}
    				else {
    					timesCharOccurs = 0; //reset
    				}
    			}
	    		
	    		
    		}
        }
        System.out.println(count); //print the count
	}

}
