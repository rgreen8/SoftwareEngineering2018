package edu.nd.se2018.homework.hwk2;

public class Horse {
	public class horse {}
		int horseNumber;
		int startSpeed;
		int distanceCovered;
		RaceStrategy strategy;
		String name;
		
		public Horse(String nameIn, int horseNumberIn, int startSpeedIn, RaceStrategy strategyIn){
			horseNumber = horseNumberIn;
			startSpeed = startSpeedIn;
			distanceCovered = 0;
			strategy = strategyIn;
			name = nameIn;
		}
		public String getName() {
			return name;
		}
}

