package application.model.vehicles;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import application.model.infrastructure.gate.CrossingGate;
import application.view.CarImageSelector;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

/**
 * Represents Car object
 * @author jane
 *
 */
public class Car extends Observable implements IVehicle, Observer{
	private ImageView ivCar;
	private double currentX = 0;
	private double currentY = 0;
	private double originalY = 0;
	private boolean gateDown = false;
	private double leadCarY = -1;  // Current Y position of car directly infront of this one
	private double speed = 0.5;
	private boolean allowTurns = false; //to determine factory it is from
	private boolean isTurning = false; //when car is in the process of turning right
	static boolean stopTraffic = false; //when car is in the process of turning right
	Random ran = new Random();
	/**
	 * Constructor
	 * @param x initial x coordinate of car
	 * @param y initial y coordinate of car
	 */
	public Car(int x, int y, boolean allowTurns){
		this.currentX = x;
		this.currentY = y;
		this.allowTurns = (allowTurns && shouldTurn());
		originalY = y;
		ivCar = new ImageView(CarImageSelector.getImage());
		ivCar.setX(getVehicleX());
		ivCar.setY(getVehicleY());
		
	}
		
	@Override
	public Node getImageView() {
		return ivCar;
	}
	
	public boolean gateIsClosed(){
		return gateDown;
	}
	
	public double getVehicleX(){
		return currentX;
	}
	public double getVehicleY(){
		return currentY;
	}
	
	public void move(){
		boolean canMove = true; 
		// First case.  Car is at the front of the stopping line.
		if (gateDown && getVehicleY() < 430 && getVehicleY()> 390) {
			canMove = false;
		}
		// Second case. Car is too close too other car.
		if (leadCarY != -1  &&  getDistanceToLeadCar() < 50) {
			canMove = false;
		}
		if(currentY > 692 && currentY < 694 && allowTurns) { //when in specific zone, and car has been flagged to turn
			canMove = turnLeft();
			//there a car past the train tracks?
			//if yes wait until it is open
			//if no finish it out
			} 
		if(canMove) {
			currentY+=speed;
			ivCar.setY(currentY);
		}
		setChanged();
		notifyObservers();
	}
	private boolean turnLeft() {
		if(currentX > 395) {
			currentX -= 1;
			ivCar.setX(currentX);
			isTurning = true;
			if(currentX < 400) {
				stopTraffic = true;
			}
			return false;
		}
		return true;
		
	}

	public boolean shouldTurn() { //chooses cars to take the alternate path
		int randNum = ran.nextInt(4);
		if(randNum == 1) {
			return true;
		}
		return false;
	}
	
	public void setSpeed(double speed){
		this.speed = speed;
	}
	
	public void setGateDownFlag(boolean gateDown){
		this.gateDown = gateDown;
	}
	
	public boolean offScreen(){
		if (currentY > 1020)
			return true;
		else
			return false;			
	}
		
	public void reset(){
		currentY = originalY;
		stopTraffic = false;
	}
	
	public double getDistanceToLeadCar(){
		return Math.abs(leadCarY-getVehicleY());
	}
	
	public void removeLeadCar(){
		leadCarY = -1;
	}
	public boolean isTurning() {
		return isTurning;
	}

	@Override
	public void update(Observable o, Object arg1) {
		if (o instanceof Car){
			leadCarY = ((Car)o).getVehicleY();
			if (leadCarY > 1020 || ((Car)o).isTurning()) {//want the car to keep moving if it is off screen, or if the one ahead of it turned
				leadCarY = -1;
			}
		}
			
		if (o instanceof CrossingGate){
			CrossingGate gate = (CrossingGate)o;
			if(gate.getTrafficCommand()=="STOP")			
				gateDown = true;
			else
				gateDown = false;
					
		}
		
	}	
}
