package chipschallenge;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Spider implements Observer {
	Point chipPosition;
	Point spiderPosition;
	int scale = 20;
	int[][] gameGrid = new int[25][25]; //include OceanMap for placing and moving pirates
	int startx = 3*scale;
	int starty = 3*scale;
	Image spiderImage = new Image("file:///Users/RyanGreen/git/SoftwareEngineering2018/chip/textures/bugUp.png",scale,scale,true,true);
	ImageView spiderImageView = new ImageView(spiderImage);
	public boolean caughtChip = false;
	public Spider() {
		spiderPosition = new Point(startx, starty);
	}
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Player){
			chipPosition = ((Player)o).getLocation();
			moveSpider();
			isCaught();
		}
	}	
	private void moveSpider() { //goal to move spider too player
		Random rand1 = new Random();
		int r = rand1.nextInt(10)+1;
		if(r < 5) { //pirates will randomly not move in order to make it more fair
			if (spiderPosition.x - chipPosition.x < 0 && okToMove(spiderPosition.x + scale, spiderPosition.y))
				spiderPosition.x = spiderPosition.x + scale;
			else if(okToMove(spiderPosition.x - scale, spiderPosition.y))
				spiderPosition.x = spiderPosition.x - scale;
			
			if (spiderPosition.y - chipPosition.y < 0 && okToMove(spiderPosition.x , spiderPosition.y + scale))
				spiderPosition.y = spiderPosition.y + scale;
			else if (okToMove(spiderPosition.x , spiderPosition.y - scale))
				spiderPosition.y = spiderPosition.y - scale;
		}

	}
	public Point getLocation() {
		return spiderPosition;
	}
	public void isCaught() {
		if(chipPosition.x == spiderPosition.x && chipPosition.y == spiderPosition.y) {
			caughtChip = true;
		}
	}
	public boolean okToMove(int x, int y) {
		if(gameGrid[x/scale][y/scale] == 0 || gameGrid[x/scale][y/scale] == 4) {
			return true;
		} 
		return false;
	}
	public ImageView getImageView() {
		return spiderImageView;
	}
	public void sendRes(int[][] map){ //used to pull map into spider class
		gameGrid = map;
	}
}
