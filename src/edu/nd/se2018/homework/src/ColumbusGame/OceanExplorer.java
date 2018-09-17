package ColumbusGame;

import java.util.LinkedList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class OceanExplorer extends Application {
	int width = 25;
	int height = 25;
	int scale = 20;
	public int[][] oceanGrid = new int[25][25];
	LinkedList<PirateShip> pirates = new LinkedList<PirateShip>();
	ObservableList<Node> root = FXCollections.observableArrayList();
	int pirateNum = 2;
	Ship columbus;
	//@Override
	public void start(Stage primaryStage) {
		try {
			//JavaFX Startup
			AnchorPane myPane = new AnchorPane();
			Scene scene = new Scene(myPane,width*scale,height*scale);
			Stage oceanStage = new Stage();
			oceanStage.setScene(scene);
			oceanStage.setTitle("Hunt for Margaritaville"); //Not the new world, but close
			//draw map
			OceanMap map = new OceanMap();
			map.drawMap(root,scale,oceanGrid); //also defines islands to be used when creating ships
			myPane.getChildren().addAll(root);
			//make Ship
			columbus = new Ship(oceanGrid);
			columbus.getImageView().setX(columbus.getShipLocation().x);
			columbus.getImageView().setY(columbus.getShipLocation().y);
			myPane.getChildren().add(columbus.getImageView());
			//make Pirates, insert into linked list for scaling 
			for(int it = 0; it < pirateNum; it++){
				pirates.add(new PirateShip(oceanGrid));
			}
			for(PirateShip pirate: pirates) {
				pirate.getImageView().setX(pirate.getLocation().x);
				pirate.getImageView().setY(pirate.getLocation().y);
				myPane.getChildren().add(pirate.getImageView());
				columbus.addObserver(pirate);  //allows pirates to track columbus location
				pirate.sendRes(oceanGrid);
			}
			oceanStage.show();
			//start sailing, updating scene and presenting
			startSailing(scene);
		} catch(Exception e) {
			e.printStackTrace(); //for debug help
		}
	}
	private void startSailing(Scene scene){
		scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
		@Override
		public void handle(KeyEvent ke) {
			switch(ke.getCode()){
				case RIGHT:
					columbus.goEast(oceanGrid);
					break;
				case LEFT:
					columbus.goWest(oceanGrid);
					break;
				case UP:
					columbus.goNorth(oceanGrid);
					break;
				case DOWN:
					columbus.goSouth(oceanGrid);
					break;
				default:
					break;
			} 
			//update columbus location, then track new move with all pirates
			columbus.getImageView().setX(columbus.getShipLocation().x);
			columbus.getImageView().setY(columbus.getShipLocation().y);
			for(PirateShip pirate: pirates) {
				pirate.getImageView().setX(pirate.getLocation().x);
				pirate.getImageView().setY(pirate.getLocation().y);
			}
		}
		});
	}
	//launch game
	public static void main(String[] oceanStage) {
		launch(oceanStage);
	}
}
