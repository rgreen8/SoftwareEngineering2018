package edu.nd.se2018.homework.homework3;
import java.util.*;

public class Race {
	Race(){}
	int horseCount = 0;
	private HashMap<String, Horse> Horses = new HashMap<String, Horse>(); //store all horse information
	private ArrayList<String> standings = new ArrayList<String>(); //keep track of winners as they are added to the end
	public void enrollHorse(String name, int horseNum, int startSpeed, String strat){
		if(horseCount < 5) {//max 5
			horseCount++;
			Horse newHorse = new Horse(name, horseNum, startSpeed, strat);
			Horses.put(name, newHorse);
			
		} else { 
			System.out.println("The Starting Gate is Full! We will race with the first 5");
		}
	}
	//race is 1000 units, which equals 10 miles
	//speed give the step size of each horse per iteration
	//every 5 iterations, give update on horse status (in no particular order)
	public void runRace() {
		int finishCount = 0;
		int loopCount = 0;
		while(finishCount < horseCount) {
			for(String key: Horses.keySet()) {
				//check if finished
				if(Horses.get(key).distanceCovered >= 1000) { //if finished, do nothing
					continue;
				} else { //update distance
					double advanceStep = Horses.get(key).getStep(Horses.get(key).startSpeed, Horses.get(key).distanceCovered);
					Horses.get(key).distanceCovered = Horses.get(key).distanceCovered + advanceStep;
				}
				//check if finished now, and iterate finish count if needed
				if(Horses.get(key).distanceCovered >= 1000) {
					finishCount++;
					Horses.get(key).distanceCovered = 1000;
					//add name to list of winners
					standings.add(key);
				}
			}
			//iterate loop count
			//if divisible by 5, print update
			loopCount++;
			if(loopCount%5 == 0) {
				System.out.println("Race Update");
				for(String key: Horses.keySet()) {
					System.out.printf(key + " %.2f mi %n", Horses.get(key).distanceCovered/100);
				}
				System.out.println("");
			}
		}
		//print Final Standings
		System.out.println("");
		System.out.println(standings.get(0) + " Wins the Race!!");
		System.out.println("");
		System.out.println("The Final Results Are");
		for(int it = 0; it< standings.size(); it++) {
				System.out.println((it + 1) + ": " + standings.get(it));
		}
	}
	
	
}