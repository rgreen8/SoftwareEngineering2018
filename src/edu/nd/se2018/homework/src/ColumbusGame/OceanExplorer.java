package ColumbusGame;

import java.util.LinkedList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class OceanExplorer extends Application {
	int width = 25;
	int height = 25;
	int scale = 20;
	public int[][] oceanGrid = new int[25][25];
	LinkedList<PirateShip> pirates = new LinkedList<PirateShip>();
	ObservableList<Node> root = FXCollections.observableArrayList();
	int pirateNum = 10;
	Ship columbus;
	//@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane myPane = new AnchorPane();
			Scene scene = new Scene(myPane,width*scale,height*scale);
			Stage oceanStage = new Stage();
			oceanStage.setScene(scene);
			oceanStage.setTitle("Hunt for Margaritaville");
			//draw map
			OceanMap map = new OceanMap();
			map.drawMap(root,scale,oceanGrid);
			myPane.getChildren().addAll(root);
			//make Ship
			columbus = new Ship(oceanGrid);
			columbus.getImageView().setX(columbus.getShipLocation().x);
			columbus.getImageView().setY(columbus.getShipLocation().y);
			myPane.getChildren().add(columbus.getImageView());
			//draw pirate
			//make pirates
			for(int it = 0; it < pirateNum; it++){
				pirates.add(new PirateShip(oceanGrid));
			}
			for(PirateShip pirate: pirates) {
				pirate.getImageView().setX(pirate.getLocation().x);
				pirate.getImageView().setY(pirate.getLocation().y);
				myPane.getChildren().add(pirate.getImageView());
				columbus.addObserver(pirate);
				pirate.sendRes(oceanGrid);
			}
			oceanStage.show();
			//start sailing, updating scene and presenting
			startSailing(scene);
			//update scene
		} catch(Exception e) {
			e.printStackTrace();
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
			columbus.getImageView().setX(columbus.getShipLocation().x);
			columbus.getImageView().setY(columbus.getShipLocation().y);
			for(PirateShip pirate: pirates) {
				pirate.getImageView().setX(pirate.getLocation().x);
				pirate.getImageView().setY(pirate.getLocation().y);
			}
		}
		});
	}
	
	public static void main(String[] oceanStage) {
		launch(oceanStage);
	}
}
