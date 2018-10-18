package chipschallenge;


import java.awt.Point;
import java.util.Observable;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player extends Observable{
	Point currentLocation;
	int scale = 20;
	Image playerDown = new Image("file:///Users/RyanGreen/git/SoftwareEngineering2018/chip/textures/chipDown.png",scale,scale,true,true);
	Image playerUp = new Image("file:///Users/RyanGreen/git/SoftwareEngineering2018/chip/textures/chipUp.png",scale,scale,true,true);
	Image playerRight = new Image("file:///Users/RyanGreen/git/SoftwareEngineering2018/chip/textures/chipRight.png",scale,scale,true,true);
	Image playerLeft = new Image("file:///Users/RyanGreen/git/SoftwareEngineering2018/chip/textures/chipLeft.png",scale,scale,true,true);
	ImageView playerImageView = new ImageView(playerDown);
	boolean hasKey = false;
	boolean levelComplete = false;
	int startx = 12*scale;
	int starty = 13*scale;
	public Player() {
		currentLocation = new Point(startx, starty);
	}
	
	public Point getLocation() {
		return currentLocation;
	}

	public ImageView getImageView() {
		return playerImageView;
	}

	public void goRight(int[][] grid, int level) {
		if(currentLocation.x < 24*scale && okToMove(currentLocation.x + scale, currentLocation.y, grid, level)) {
			currentLocation.x = currentLocation.x + scale;
		}
		checkForKey(level);
		playerImageView.setImage(playerRight);
		setChanged();		// The observable object has moved.  To include recent changes you *MUST* have this line
		notifyObservers();
	}

	public void goLeft(int[][] grid, int level) {
		if(currentLocation.x > 0 && okToMove(currentLocation.x - scale, currentLocation.y, grid, level)) {
			currentLocation.x = currentLocation.x - scale;
		}
		checkForKey(level);
		playerImageView.setImage(playerLeft);
		setChanged();		// The observable object has moved.  To include recent changes you *MUST* have this line
		notifyObservers();
	}

	public void goUp(int[][] grid, int level) {
		if(currentLocation.y > 0 && okToMove(currentLocation.x, currentLocation.y - scale, grid, level)) {
			currentLocation.y = currentLocation.y - scale;
		}
		checkForKey(level);
		playerImageView.setImage(playerUp);
		setChanged();		// The observable object has moved.  To include recent changes you *MUST* have this line
		notifyObservers();
	}

	public void goDown(int[][] grid, int level) {
		if(currentLocation.y < 24*scale && okToMove(currentLocation.x, currentLocation.y + scale, grid, level)) {
			currentLocation.y = currentLocation.y + scale;
		}
		checkForKey(level);
		playerImageView.setImage(playerDown);
		setChanged();		// The observable object has moved.  To include recent changes you *MUST* have this line
		notifyObservers();
	}
	private boolean okToMove(int x, int y, int[][] grid, int level) {
		if(level == 1) {
			if(x/scale == 20 && y/scale == 20) {
				//iterate Level 1
				levelComplete = true;
				return true;
			} else if(hasKey && x/scale == 20 && y/scale == 20) {
					return true;
			} 
		}
		if(level == 2) {
			if(x/scale == 3  && y/scale == 15) {
				//iterate to end
				levelComplete = true;
				return true;
			} else if(hasKey && x/scale == 4 && y/scale == 15) {
					return true;
			} 
		}
		if(grid[x/scale][y/scale] == 0 || grid[x/scale][y/scale] == 4) {
			return true;
		} 
		return false;
	}
	private void checkForKey(int level){
		if(level == 1) {
			if(currentLocation.x == 13*scale && currentLocation.y == 5 *scale && !hasKey) {
				//make key true
				hasKey = true;
				//reset Image
				playerImageView.toFront();
				//change key square to regular walk square
				
			}
		} else { //level 2
			if(currentLocation.x == 22*scale && currentLocation.y == 3*scale && !hasKey) {
				//make key true
				hasKey = true;
			}
		}
	}
	
}
