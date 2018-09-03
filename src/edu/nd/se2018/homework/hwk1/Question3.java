package edu.nd.se2018.homework.hwk1;
import java.util.*;
public class Question3 {
	
	public Question3(){}	
	
    public int getMirrorCount(int[] numbers){
    	if(numbers.length == 0) {
    		return 0;
    	}
    	int maxSize = 1;
    	int midPoint = 0;
    	int size = 0;
    	List<Integer> l1 = new ArrayList<Integer>();
    	for(int it = 0; it < numbers.length; it++) {
    		//check to see if top of list, matches next int
    		//if so pop off and see if number before, matches 
    		if(l1.size() != 0 && (numbers[it] == l1.get(l1.size()-1))) {
    			size = size + 2;
    			l1.remove(l1.size()-1);
    		}
    		else {
    			//check second item in list, to see if the size is odd and the middle number will have no match
    			if(l1.size() > 2 && (numbers[it] == l1.get(l1.size() - 2))) {
    				midPoint = 1; //if so mark, to add 3 to the total later
    				//pop off 2 items from list, in order to allow the normal algorithm to work
    				l1.remove(l1.size()-1);
    				l1.remove(l1.size()-1);
    			}
    			else { //if list is empty or no match is found
    				l1.add(numbers[it]);
    			}
    		if(size > maxSize) { //compare and update maxSize
    				maxSize = size;
    				size = 0;
    		}
    		}
    	}
    	if(size > maxSize) { //final comparison
			maxSize = size;
			size = 0;
		}
    	if(midPoint == 1) { //adjust for odd sized reflection
    		maxSize = maxSize + 3;
    	}
    	//do a final check for non-touching reflection, if maxSize is still 1
    	//iterate through loop, look for repeat numbers   	
    	//if found, check if the adjacent numbers are equal, continue to look until not equal
    	int size2 = 1;
    	if(maxSize <= 1) {
    		for(int it = 0; it < numbers.length; it++) {
    			size2 = 1;
    			for(int k = it + 1; k < numbers.length; k++) {
        			if(numbers[it] == numbers[k]) {
        				//check if it+1 is == to k -1 
        				while(numbers[it+size2]==numbers[k-size2]) {
        					size2++;
        				}
        			}
        		}
    			if(size2 > maxSize) { //compare new size with old maxSize
    				maxSize = size2;
    			}
    		}
    	}	
    	//return final 
    	return maxSize;
	}
}
