package application.model.vehicles;

import java.util.Observable;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents the train entity object
 * @author jane
 *
 */
public class Train extends Observable implements IVehicle{
	private double currentX = 0;
	private double currentY = 0;
	private double originalX = 0;
	private Image img;
	private ImageView imgView;
	private int trainLength = 35;
	public boolean trainrev = false;
	
	public Train(int x, int y, boolean rev){
		this.currentX = x;
		this.currentY = y;
		this.trainrev = rev;
		originalX = x;
		if(trainrev) { //choose image based on direction
			img = new Image("file:///Users/RyanGreen/git/SoftwareEngineering2018/src/images/Trainrev.PNG",120,trainLength,false,false);
			imgView = new ImageView(img);
		} else {
			img = new Image("file:///Users/RyanGreen/git/SoftwareEngineering2018/src/images/Train.PNG",120,trainLength,false,false);
			imgView = new ImageView(img);
		}
		imgView.setX(currentX);
		imgView.setY(currentY);
	}
	
	public double getVehicleX(){
		return currentX;
	}
	
	public double getVehicleY(){
		return currentY;
	}
	public boolean getReversed() {
		return trainrev;
	}
	public void move(){
		currentX-=1.25;
		imgView.setX(currentX);
		setChanged();
		notifyObservers();
	}
	public void moverev(){ //iterate x value to the right if reversed
		currentX+=1.25;
		imgView.setX(currentX);
		setChanged();
		notifyObservers();
	}
	
	public boolean offScreen(){
		if (currentX < -200)
			return true;
		else
			return false;				
	}
	public boolean offScreenrev(){ //change off screen for reversed option
		if (currentX > 1200)
			return true;
		else
			return false;				
	}
	
	public void reset(){
		currentX = originalX;
	}

	//@Override
	public Node getImageView() {
		return imgView;
	}
}