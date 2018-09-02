package edu.nd.se2018.homework.hwk1;
import java.util.*;
public class Question2 {

	public Question2(){}
	
	public String getMostFrequentWord(String input, String stopwords){
		HashMap<String, Integer> inputTrack = new HashMap<String, Integer>();
		HashSet<String> stops = new HashSet<String>();
		//put stop words in a set
		for (String word : stopwords.split(" ")) {
			stops.add(word);
		}
		//iterate through string
		for (String word : input.split(" ")) {
			if(!stops.contains(word)) { //valid word
				//check if already tracked
				//if so add one to total
				//else enter it as 1
				if(inputTrack.containsKey(word)) {
					inputTrack.put(word, inputTrack.get(word) + 1);
				}else {
					inputTrack.put(word, 1);
				}
			}
		}
		//find highest times
		String maxInput = "";
		int maxValue = 0;
		for(String word : inputTrack.keySet()){
			if(inputTrack.get(word) == maxValue) {
				maxInput = "";
				maxValue = inputTrack.get(word);
			}
			if(inputTrack.get(word) > maxValue) {
				maxInput = word;
				maxValue = inputTrack.get(word);
			}
		}
		//return highest time
		System.out.println(maxInput);
		if(maxInput == "") {
			return null;
		}
		return maxInput;
	}
}
