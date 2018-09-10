package edu.nd.se2018.homework.homework3;

public class Main {

	public static void main(String[] args) {
		//standard race to start
		Race race = new Race(); //Initialize race and add horses
		race.enrollHorse("Sam",0,22, "fastStart");
		race.enrollHorse("Molly",1,24,"slowStart");
		race.enrollHorse("Joe",2,25,"fastStart");
		race.runRace(); //run to see who wins
	}
}
