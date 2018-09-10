package edu.nd.se2018.homework.hwk2;
import java.util.*;

import org.junit.Test;

public class horseTest {
	@Test
	public void test() {
		Object race = new race();
		assert(race.enrollHorse("Sam",0,22,new fastStart()));
		assert(race.enrollHorse("Molly",1,24,new slowStart()));
		assert(race.enrollHorse("Joe",2,25,new fastStart()));
		assert(race.enrollHorse("Blizzard",3,25,new slowStart()));
		assert(race.enrollHorse("Flicker",4,25,new steadyState()));
	}
}