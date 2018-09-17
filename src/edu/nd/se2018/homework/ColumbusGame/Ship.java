package  ColumbusGame;

import java.awt.Point;
import java.util.Observable;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Ship extends Observable{
	Point currentLocation;
	int scale = 20;
	Image shipImage;
	ImageView shipImageView;
	
	public Ship(int[][] oceanGrid){
		Random rand1 = new Random();
		//use random between 3 and 23 to keep it interesting
		int  n = rand1.nextInt(20) + 3;
		int  n2 = rand1.nextInt(20) + 3;
		int startx = n*scale;
		int starty = n2*scale; 
		currentLocation = new Point(startx, starty);
		oceanGrid[n][n2] = 2;
		shipImage = new Image("file:/Users/RyanGreen/git/SoftwareEngineering2018/src/images/ColumbusShip.png",scale,scale,true,true);	
		shipImageView = new ImageView(shipImage);
	}
	public Point getShipLocation(){ 
		return currentLocation;
	}
	public ImageView getImageView() {
		return shipImageView;
	}
	//movement functions check bounds and island status of future move before it is allowed to update location
	public void goEast(int[][] oceanGrid) {
		if(currentLocation.x < 24*scale && okToMove(currentLocation.x + scale, currentLocation.y, oceanGrid)) {
			currentLocation.x = currentLocation.x + scale;
			setChanged();		// The observable object has moved.  To include recent changes you *MUST* have this line
			notifyObservers();
		}
	}
	public void goWest(int[][] oceanGrid) {
		if(currentLocation.x >= scale && okToMove(currentLocation.x - scale, currentLocation.y, oceanGrid)) {
			currentLocation.x = currentLocation.x - scale;
			setChanged();		// The observable object has moved.  To include recent changes you *MUST* have this line
			notifyObservers();
		}
	}
	public void goNorth(int[][] oceanGrid) {
		if(currentLocation.y >= scale && okToMove(currentLocation.x, currentLocation.y - scale, oceanGrid)) {
		currentLocation.y = currentLocation.y - scale;
		setChanged();		// The observable object has moved.  To include recent changes you *MUST* have this line
		notifyObservers();
		}
	}
	public void goSouth(int[][] oceanGrid) {
		if(currentLocation.y < 24*scale && okToMove(currentLocation.x, currentLocation.y + scale, oceanGrid)) {
		currentLocation.y = currentLocation.y + scale;
		setChanged();		// The observable object has moved.  To include recent changes you *MUST* have this line
		notifyObservers();
		}
	}
	public boolean okToMove(int x, int y, int[][] oceanGrid) {
		//check in location is "water", if not return not safe to move
		if(oceanGrid[x/scale][y/scale] != 0) {
			return false;
		}else {
			return true;
		}
	}
}
