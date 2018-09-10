package edu.nd.se2018.homework.hwk2;
import java.util.*;

public class Race {
	Race(){}
	int horseCount = 0;
	private HashMap<String, Double> disTrack = new HashMap <String, Double>;
	private HashMap<String, Horse> Horses = new HashMap<String, Horse>;
	
	
	public void enrollHorse(String name, int horseNum, int startSpeed, RaceStrategy strat){
		if(horseCount < 4) {
			horseCount++;
			Horse newHorse = new Horse(name, horseNum, startSpeed, strat);
			Horses.put(newHorse.getName(), newHorse);
			
		} else {
			System.out.println("The Starting Gate is Full!");
		}
	}
	
	public void runRace() {
		for(String key : Horses.keySet()) {
			System.out.println(key.getName());
		}
	}
	
	
}