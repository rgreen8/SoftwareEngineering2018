package edu.nd.se2018.homework.homework3;

public class Horse {
	public class horse {}
		int horseNumber;
		double startSpeed;
		double distanceCovered;
		RaceStrategy strategy;
		String name;
		
		public Horse(String nameIn, int horseNumberIn, int startSpeedIn, String strategyIn){
			horseNumber = horseNumberIn;
			if(startSpeedIn > 0) { //check for valid input
				startSpeed = startSpeedIn;
			} else { startSpeed = 15;}
			distanceCovered = 0;
			name = nameIn;
			//set strat using switch
			setStrategy(strategyIn);
		}
		public String getName() {
			return this.name;
		}
		public void setStrategy(String newStrat) {
			//switch statement to change strategy
			switch(newStrat) {
			case "slowStart":
				strategy = new slowStart();
				break;
			case "fastStart":
				strategy = new fastStart();
				break;
			case "steadyState":
				strategy = new steadyState();
				break;
			default:
				strategy = new steadyState();
				break;
			}
		} //function to allow use of the interface saved in RaceStrategy.java
		public double getStep(double startSpeed, double distanceCovered) {
			return strategy.getSpeed(startSpeed, distanceCovered);
		}
}

