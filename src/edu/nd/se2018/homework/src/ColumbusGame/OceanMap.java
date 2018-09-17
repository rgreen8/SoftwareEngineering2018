package ColumbusGame;

import java.util.Random;

import javafx.collections.ObservableList;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class OceanMap {
	final int dimensions = 25;
	
	public void drawMap(ObservableList<Node> root, int scale, int[][] oceanGrid) {
		isIsland(oceanGrid);
		for (int x = 0; x < 25; x++){
			for (int y = 0; y < 25; y++){
				Rectangle rect = new Rectangle(x*scale,y*scale, scale, scale);
				rect.setStroke(Color.BLACK);
				if(oceanGrid[x][y] == 1) {
					rect.setFill(Color.GREEN);
				} else {
					rect.setFill(Color.PALETURQUOISE);
				}
				root.add(rect);
			}
		}
	}
	public void isIsland(int[][] oceanGrid) {
		Random rand1 = new Random();
		for(int it = 0; it < 10; it++) {
			//use random between 3 and 23 to keep it interesting
			int randx = rand1.nextInt(20) + 3;
			int randy = rand1.nextInt(20) + 3;
			if(oceanGrid[randx][randy] == 0) {
				oceanGrid[randx][randy] = 1;
			} else { //if the ship is already there
				int counter = 0;
				while(oceanGrid[randx + counter][randy + counter] != 0) { //iterate to find possible island location
					counter++;
				}
				oceanGrid[randx + counter][randy + counter] = 1;
			}
				
		}
	}
}
