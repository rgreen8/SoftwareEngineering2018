package edu.nd.se2018.homework.hwk1;
import java.util.*;
public class Question1 {
		
	public Question1(){}
	
	public int getSumWithoutDuplicates(int[] numbers){
		int sum = 0;
		HashSet<Integer> tracker = new HashSet<Integer>();
		for (int it=0; it<numbers.length; it++) {
			if(!tracker.contains(numbers[it])){
				tracker.add(numbers[it]);
				sum = sum + numbers[it];
			}
        }
		return sum;	
	}
}
