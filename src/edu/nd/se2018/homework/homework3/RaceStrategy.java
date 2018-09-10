package edu.nd.se2018.homework.homework3;


public interface RaceStrategy { //set standard for all implementations
    double getSpeed(double startSpeed, double distanceCovered);
}

class slowStart implements RaceStrategy {
    @Override
    public double getSpeed(double startSpeed, double distanceCovered) {
    	double stepSize = 0;
    	if(distanceCovered > 900) { //see if at max speed
    		stepSize = startSpeed/0.75;
    	} else if (distanceCovered > 600) { //check if you are speeding up
    		stepSize = (startSpeed/0.75)*0.9;
    	} else {
    		stepSize = startSpeed;
    	}
        return stepSize;
    }

}
class fastStart implements RaceStrategy {
    @Override
    public double getSpeed(double startSpeed, double distanceCovered) {
    	double stepSize = 0;
    	if(distanceCovered > 200) { // pump the breaks after 200
    		stepSize = startSpeed*0.75;
    	} else { //go fast if you are still starting
    		stepSize = startSpeed;
    	}
        return stepSize;
    }

}
class steadyState implements RaceStrategy {
    @Override
    public double getSpeed(double startSpeed, double distanceCovered) {
        return startSpeed;
    }

}