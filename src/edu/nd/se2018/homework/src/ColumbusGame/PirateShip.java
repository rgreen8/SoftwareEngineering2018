package ColumbusGame;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class PirateShip implements Observer{
	Point columbusPosition;
	Point piratePosition;
	int scale = 20;
	int[][] oceanMap = new int[25][25];
	Image pirateImage;
	ImageView pirateImageView;
	public PirateShip(int[][] oceanGrid){
		Random rand1 = new Random();
		//use random between 3 and 23 to keep it interesting
		int  n = rand1.nextInt(20) + 3;
		int  n2 = rand1.nextInt(20) + 3;
		int startx = n*scale;
		int starty = n2*scale;
		piratePosition = new Point(startx, starty);
		oceanGrid[n][n2] = 3;
		pirateImage = new Image("file:/Users/RyanGreen/git/SoftwareEngineering2018/src/images/pirateship.gif",scale,scale,true,true);
		pirateImageView = new ImageView(pirateImage);
	}
	@Override
	public void update(Observable s, Object arg) {
		if (s instanceof Ship){
			columbusPosition = ((Ship)s).getShipLocation();
			movePirate();			
		}
	}
	public ImageView getImageView() {
		return pirateImageView;
	}
	public void movePirate(){
		Random rand1 = new Random();
		int r = rand1.nextInt(10)+1;
		if(r != 1) { //pirates will randomly not move in order to make it more fair for Columbus
			if (piratePosition.x - columbusPosition.x < 0 && okToMove(piratePosition.x + scale, piratePosition.y))
				piratePosition.x = piratePosition.x + scale;
			else if(okToMove(piratePosition.x - scale, piratePosition.y))
				piratePosition.x = piratePosition.x - scale;
			
			if (piratePosition.y - columbusPosition.y < 0 && okToMove(piratePosition.x , piratePosition.y + scale))
				piratePosition.y = piratePosition.y + scale;
			else if (okToMove(piratePosition.x , piratePosition.y - scale))
				piratePosition.y = piratePosition.y - scale;
		}
		
	}
	public Point getLocation() {
		return piratePosition;
	}
	public boolean okToMove(int x, int y) {
		if(oceanMap[x/scale][y/scale] != 0) {
			return false;
		}else {
			return true;
		}
	}
	public void sendRes(int[][] map){
		oceanMap = map;
	}
}
