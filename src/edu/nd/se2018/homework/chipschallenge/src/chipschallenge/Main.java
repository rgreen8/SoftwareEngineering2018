package chipschallenge;
	
import chipschallenge.block.Block;
import chipschallenge.block.WalkBlock;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;



public class Main extends Application {
	private Scene scene;
	public int mapSize = 25;
	int width = 25;
	int hieght = 25;
	int scale = 20;
	int level = 1;
	Player player;
	Map gameMap;
	Spider spider;
	public int[][] gameGrid = new int[mapSize][mapSize];
	ObservableList<Node> oblist = FXCollections.observableArrayList();
	AnchorPane myPane = new AnchorPane();
	Stage myStage;
	@Override
	public void start(Stage stage) {
		try {
			//set up JavaFX
			scene = new Scene(myPane,width*scale,hieght*scale);
			stage.setTitle("Chip's Challenge");
			stage.setScene(scene);
			stage.show();
			//Decide Level
			
			//Build Map
			gameMap = new Map();
			gameMap.drawMap(oblist, gameGrid, level, scale);
			myPane.getChildren().addAll(oblist);
			//init player
			player = new Player();
			player.getImageView().setX(player.getLocation().x);
			player.getImageView().setY(player.getLocation().y);
			myPane.getChildren().add(player.getImageView());
			myStage = stage;
			stage.show();
			//start game
			startPlay(scene);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	private void startPlay(Scene scene){
		scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
		@Override
		public void handle(KeyEvent ke) {
			switch(ke.getCode()){
				case RIGHT:
					player.goRight(gameGrid, level);
					break;
				case LEFT:
					player.goLeft(gameGrid, level);
					break;
				case UP:
					player.goUp(gameGrid, level);
					break;
				case DOWN:
					player.goDown(gameGrid, level);
					break;
				case ESCAPE:
			        myStage.close();
			        break;
				default:
					break;
			} 
			//update player status
			if(player.hasKey || player.levelComplete) {
				updatesNeeded();
			}
			//update players and spider location
			player.getImageView().setX(player.getLocation().x);
			player.getImageView().setY(player.getLocation().y);
			if(level == 2) {
				//update player spider
				if(spider.caughtChip) {
					updatesNeeded();
				}
				spider.getImageView().setX(spider.getLocation().x);
				spider.getImageView().setY(spider.getLocation().y);
			}
			
			
		}
		});
	}
	public void updatesNeeded() {
		if(level == 1) {
			if(player.hasKey) { //erase key block
				gameGrid[13][5] = 0;
				Block block = new WalkBlock();
				block.getImageView().setX(13*scale);
				block.getImageView().setY(5*scale);
				myPane.getChildren().add(block.getImageView());
				player.getImageView().toFront();
			}
			if(player.levelComplete) {
				//start level 2
				level = 2;
				startLevel2();
				
			}
		} else { //level 2
			if(player.hasKey) { //erase key block
				gameGrid[22][3] = 0;
				Block block = new WalkBlock();
				block.getImageView().setX(22*scale);
				block.getImageView().setY(3*scale);
				myPane.getChildren().add(block.getImageView());
				player.getImageView().toFront();
			}
			if(player.levelComplete) {
				level = 0;
				winGame();
		
				
			}
			if(spider.caughtChip) {
				level = 0;
				gameOver();
			}
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
	
	public void startLevel2() {
		//build new map
		myPane.getChildren().clear();
		oblist.clear();
		for (int x = 0; x < mapSize; x++){
			for (int y = 0; y < mapSize; y++){
				gameGrid[x][y] = 0;
			}
		}
		gameMap.drawMap(oblist, gameGrid, level, scale);
		myPane.getChildren().addAll(oblist);
		//new player
		player.hasKey = false;
		player.levelComplete = false;
		player.getImageView().setX(player.getLocation().x);
		player.getImageView().setY(player.getLocation().y);
		myPane.getChildren().add(player.getImageView());
		//new spider
		spider = new Spider();
		spider.getImageView().setX(spider.getLocation().x);
		spider.getImageView().setY(spider.getLocation().y);
		myPane.getChildren().add(spider.getImageView());
		//allows for the spider to track
		player.addObserver(spider);  
		spider.sendRes(gameGrid);
	}
	private void winGame() {
		myPane.getChildren().clear();
		oblist.clear();
		Rectangle rect = new Rectangle(8*scale,11*scale, 200, 30);
		rect.setStroke(Color.BLACK);
		rect.setFill(Color.GREEN);
		Text text = new Text();
		text.setFill(Color.WHITE);
		text.setFont(Font.font("verdana", FontWeight.BOLD, 25));
		text.setText("Game Won");
		text.setX(9*scale); 
	    text.setY(12*scale);
	    myPane.getChildren().add(rect);
	    myPane.getChildren().add(text);
		
	}
	private void gameOver() {
		myPane.getChildren().clear();
		oblist.clear();
		Rectangle rect = new Rectangle(3*scale,11*scale, 360, 30);
		rect.setStroke(Color.BLACK);
		rect.setFill(Color.RED);
		Text text = new Text();
		text.setFill(Color.WHITE);
		text.setFont(Font.font("verdana", FontWeight.BOLD, 25));
		text.setText("The Spider Caught You");
		text.setX(4*scale); 
	    text.setY(12*scale);
	    myPane.getChildren().add(rect);
	    myPane.getChildren().add(text);
		
	}
}


