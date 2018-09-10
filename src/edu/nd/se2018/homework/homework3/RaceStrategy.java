package edu.nd.se2018.homework.hwk2;


public interface RaceStrategy {
    double getSpeed(int startSpeed, int distanceCovered);
}

class slowStart implements RaceStrategy {
    @Override
    public double getSpeed(int startSpeed, int distanceCovered) {
    	double effort = 0.75;
    	int speed = 0;
    	if(distanceCovered > 600) { //if in first part of race, slower speed
    		
    	} else if (distanceCovered > 900) { //hitting max speed if over limit
    		
    	} else {
    		
    	}
        return speed;
    }

}
class fastStart implements RaceStrategy {
    @Override
    public double getSpeed(int startSpeed, int distanceCovered) {
    	double effort = 0.75;
    	int speed = 0;
    	if(distanceCovered > 200) { //if in first part of race, faster speed
    		
    	} else { //return slower speed if second part
    		
    	}
        return speed;
    }

}
class steadyState implements RaceStrategy {
    @Override
    public double getSpeed(int startSpeed, int distanceCovered) {
    	double effort = 0.8;
    	
        return startSpeed*effort;
    }

}