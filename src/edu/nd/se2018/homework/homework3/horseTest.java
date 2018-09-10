package edu.nd.se2018.homework.homework3;


import org.junit.Test;

public class horseTest {
	@Test
	public void test() {
		//test standard race
		Race race1 = new Race();
		race1.enrollHorse("Sam",0,22,"fastStart");
		race1.enrollHorse("Molly",1,24,"slowStart");
		race1.enrollHorse("Joe",2,25,"fastStart");
		race1.enrollHorse("Blizzard",3,25,"slowStart");
		race1.enrollHorse("Flicker",4,25,"steadyState");
		race1.runRace();
		System.out.println("------------------");
		System.out.println("");
		
		//testing with 0 speeds
		Race race2 = new Race();
		race2.enrollHorse("Sam",0,0,"fastStart");
		race2.enrollHorse("Molly",1,24,"slowStart");
		race2.enrollHorse("Joe",2,25,"fastStart");
		race2.runRace();
		System.out.println("------------------");
		System.out.println("");
		
		//test putting to many horses in
		Race race3 = new Race();
		race3.enrollHorse("Dave",0,22,"fastStart");
		race3.enrollHorse("Sean",1,24,"slowStart");
		race3.enrollHorse("Buck",2,25,"fastStart");
		race3.enrollHorse("Connor",3,25,"slowStart");
		race3.enrollHorse("Kevin",4,25,"steadyState");
		race3.enrollHorse("Griff",5,25,"steadyState");
		race3.runRace();
		System.out.println("------------------");
		System.out.println("");

		
		//testing with a tie
		Race race4 = new Race();
		race4.enrollHorse("Ryan",0,20,"steadyState");
		race4.enrollHorse("Connor",1,20,"steadyState");
		race4.enrollHorse("Sean",3,10,"steadyState");
		race4.enrollHorse("Mike",4,25,"steadyState");
		race4.runRace();
	}
}