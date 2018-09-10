package edu.nd.se2018.homework.hwk2;
import java.util.*;

public static void Main(String[] args) {
	
		race.enrollHorse("Sam",0,22,new fastStart());
		race.enrollHorse("Molly",1,24,new slowStart());
		race.enrollHorse("Joe",2,25,new fastStart());
		race.runRace();
	}
}