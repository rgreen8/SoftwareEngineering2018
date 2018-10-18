package chipschallenge;

import chipschallenge.block.Block;
import chipschallenge.block.BlockFactory;
import javafx.collections.ObservableList;
import javafx.scene.Node;

public class Map {
	private int mapSize = 25;
	BlockFactory factory = new BlockFactory();
	public void drawMap(ObservableList<Node> oblist, int[][] oceanGrid, int level, int scale) {
		createGrid(oceanGrid, level);
		if(level == 2) {
			buildLevelTwo(oblist, oceanGrid, scale);
		} else {
			buildLevelOne(oblist, oceanGrid, scale);
		}
	}
	
	public void buildLevelOne(ObservableList<Node> oblist, int[][] oceanGrid, int scale) {
		for (int x = 0; x < mapSize; x++){
			for (int y = 0; y < mapSize; y++){
				Block block;
				if(oceanGrid[x][y] == 1) { //impass
					block = factory.createBlock("impass");
				} 
				else if(oceanGrid[x][y] == 2) { //locked
					block = factory.createBlock("locked");
				} else if(oceanGrid[x][y] == 3) { //victory
					block = factory.createBlock("victory");	
				} else if(oceanGrid[x][y] == 4) { //key block
					block = factory.createBlock("key");	
				} else { //walking
					block = factory.createBlock("walk");
				}
				//set position
				block.getImageView().setX(x*scale);
				block.getImageView().setY(y*scale);
				//add to map
				oblist.add(block.getImageView());
			}
		}
		
	}
	public void buildLevelTwo(ObservableList<Node> oblist, int[][] oceanGrid, int scale) {
		for (int x = 0; x < mapSize; x++){
			for (int y = 0; y < mapSize; y++){
				Block block;
				if(oceanGrid[x][y] == 1) { //impass
					block = factory.createBlock("impass");
				} 
				else if(oceanGrid[x][y] == 2) { //locked
					block = factory.createBlock("locked");
				} else if(oceanGrid[x][y] == 3) { //victory
					block = factory.createBlock("victory");	
				} else if(oceanGrid[x][y] == 4) { //key
					block = factory.createBlock("key");	
				}else { //walking
					block = factory.createBlock("walk");
				}
				//set position
				block.getImageView().setX(x*scale);
				block.getImageView().setY(y*scale);
				//add to map
				oblist.add(block.getImageView());
			}
		}
	}
	private void createGrid(int[][] oceanGrid, int level) {
		if(level == 2) {
			createLevelTwo(oceanGrid, level);
		} else {
			createLevelOne(oceanGrid, level);
		}
	}

	private void createLevelOne(int[][] oceanGrid, int level) {
		for (int x = 0; x < mapSize; x++){
			for (int y = 0; y < mapSize; y++){
				//put key
				if(x == 13 && y == 5) {oceanGrid[13][5] = 4;}
				//put in locked block
				if(x == 20 && y ==20) {oceanGrid[20][20] = 2;}
				//put in victory block
				if(x == 21 && y == 20) {oceanGrid[21][20] = 3;}
				//check if wall
				isWall(level, x, y, oceanGrid);
			}
		}
	}

	private void createLevelTwo(int[][] oceanGrid, int level) {
		for (int x = 0; x < mapSize; x++){
			for (int y = 0; y < mapSize; y++){
				//put key 
				if(x == 22 && y ==3) {oceanGrid[22][3] = 4;}
				//put in locked
				if(x == 4 && y == 15) {oceanGrid[4][15] = 2;}
				//put in victory block
				if(x == 3 && y == 15) {oceanGrid[3][15] = 3;}
				//check if wall
				isWall(level, x, y, oceanGrid);
			}
		}
	}
	private boolean isWall(int level, int x, int y, int[][] oceanGrid) {
		if(level == 1) {
			if((x == 20 && y == 21) || (x == 21 && y == 21) || (x == 22 && y == 21) || (x == 20 && y == 19) || (x == 21 && y == 19) || (x == 22 && y == 19) || (x == 22 && y == 20) ) {
				oceanGrid[x][y] = 1;
			}
		} 
		if(level == 2) {
			if((x == 4 && y == 16) || (x == 3 && y == 16) || (x == 2 && y == 16) || (x == 2 && y == 15) || (x == 2 && y == 14) || (x == 3 && y == 14) || (x == 4 && y == 14)){
				oceanGrid[x][y] = 1;
			}
		}
		return false;
	}
}
