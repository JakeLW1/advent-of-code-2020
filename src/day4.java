import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//import some stuff

public class day4 {

	public static void main(String[] args) throws IOException { //file read exception handling
		
		BufferedReader in = new BufferedReader(new FileReader("input.txt")); //read that file
		
		String[] validComps = {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"}; //valid passport components
		List<String> passports = new ArrayList<String>(); //blank list for everyones passports
        String str =""; //blankety blank
        while(true) //keep doing this!
        {
            String tmp = in.readLine(); //read a line in and store temporarily
            if(tmp==null){
                break; //if temp is blank break
            }
            else if(tmp.isEmpty()){
                if(!str.isEmpty()){
                	passports.add(str); //if temp is empty but string isnt then add to passport (accounting for blank line)
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
        	passports.add(str); //if str is full add it!
        }
        
        in.close(); //in is now closed
        
        int fullMatch = 0; //full match
        for(int i = 0; i < passports.size(); i++) { //loop through all passports
        	String[] components = passports.get(i).split("[\r?\n|\n| ]+", -1); //split passports into components
        	int match = 0; //individual match counter
        	
        	for(int j = 0; j < components.length; j++) { //for each component
        		for(int k = 0; k < validComps.length; k++) { //for each valid component (trimming fat)
        			if(components[j].substring(0, 3).equalsIgnoreCase(validComps[k])) {//if the component equals a valid one
        				String component = components[j].substring(0, 3);//component is now first 3 letters (or just validComp)
        				String value = components[j].substring(4);//the value is everything after the :
        				switch(component) { //switch time baybee!
        					case "byr": //birth year
        						int byrValue = Integer.parseInt(value); //make string an int
        						if(byrValue >= 1920 && byrValue <= 2002) { //2002!!!!!!!
        							match++;
        						}
        						break;
        					case "iyr": //issue year
        						int iyrValue = Integer.parseInt(value);
        						if(iyrValue >= 2010 && iyrValue <= 2020) { //compare int value
        							match++;
        						}
        						break;
        					case "eyr":
        						int eyrValue = Integer.parseInt(value);
        						if(eyrValue >= 2020 && eyrValue <= 2030) { //same again
        							match++;
        						}
        						break;
        					case "hgt": //oh boy here we go
        						if(value.contains("in") || value.contains("cm")) { //if it has a unit of measurement
        							String unit = value.substring(value.length() -2); //get that unit
        							int hgtValue = Integer.parseInt(value.substring(0, value.length()-2)); //get the numba
	        						if(unit.equalsIgnoreCase("cm")) {
	        							if(hgtValue >= 150 && hgtValue <= 193) { //validate in cm
	        								match++;
	        							}
	        						}
	    							else if(unit.equalsIgnoreCase("in")) {
	    								if(hgtValue >= 59 && hgtValue <= 76) { //validate in inches
	            							match++;
	            						}
	        						}
        							break;
        						}
        						else {
	        						break;
        						}
        					case "hcl": //hair colour... (i wish i knew more about regex)
        						if(value.substring(0,1).equals("#")) { //if the hash is there
        							if(value.length() == 7) { //if it's 7 digits long
        								int letterMatch = 0;
        								char[] values = value.toCharArray(); //get the values into a char array
        								for(int l = 1; l < values.length; l++) {
        									char letter = '\0'; //blank leter
        									int number = 10; //invalid number
        									if(Character.isLetter(values[l])) { //if its a letter make it a char
        										letter = values[l];
        									}
        									if(Character.isDigit(values[l])) { //or a number make it an int
        										number = Character.getNumericValue(values[l]);
        									}
        									if(letter >= 'a' && letter <= 'f') { //validation for the nation
        										letterMatch++;
        										letter = '\0';
        									}
        									else if(number >= 0 && number <= 9) {
        										letterMatch++;
        										number = 10;
        									}
        									if(letterMatch == 6) { //if all 6 match up then bingo!
        										match++;
        										letterMatch = 0;
        									}
        								}
        							}
        							else
        								break;
        						}
        						else
        							break;
        					case "ecl":
        						String[] eyeColour = {"amb", "blu", "brn", "gry", "grn", "hzl", "oth"};
        						for(int m = 0; m < eyeColour.length; m++) {
        							if(value.equalsIgnoreCase(eyeColour[m])) { //eye colour validation ezpz
        								match++;
        								break;
        							}
        						}
        						break;
        					case "pid":
        						if(value.length() == 9) { //if the passport id is 9 numbers long
        							match++;
        						}
        						else
        							break;
        						break;
        						
        				}
        			}
        		}
    			if(match == 7) {
        			fullMatch++; //we have ourselves a full match! pog
    				break;
    			}
        	}
        }
        System.out.println(fullMatch); //get them in that console
	}

}
