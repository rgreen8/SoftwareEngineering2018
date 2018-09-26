package application;

import java.util.ArrayList;
import java.util.Collection;

import application.model.infrastructure.MapBuilder;
import application.model.infrastructure.RailwayTracks;
import application.model.infrastructure.Road;
import application.model.infrastructure.gate.CrossingGate;
import application.model.vehicles.Car;
import application.model.vehicles.Train;
import application.view.MapDisplay;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Simulation extends Application{
	
	private Pane root;
	private Scene scene;
	private MapBuilder mapBuilder;
	private MapDisplay mapDisplay;
	
	@Override  
	public void start(Stage stage) throws Exception {
		
		root = new Pane();
		
		// Build infrastructure
		mapBuilder = new MapBuilder();
		mapDisplay = new MapDisplay(root, mapBuilder.getRoads(), mapBuilder.getTracks(),mapBuilder.getAllGates());					
		mapDisplay.drawTracks();		
		mapDisplay.drawRoad();
		mapDisplay.drawGate();
		
		scene = new Scene(root,1200,1000);
		stage.setTitle("Railways");
		stage.setScene(scene);
		stage.show();
				
		// Train
		RailwayTracks track = mapBuilder.getTrack("Royal");
		//create trains
		Train train = new Train(track.getEndX()+100,track.getEndY()-25, false);
		Train train2 = new Train(track.getEndX()-1400,track.getEndY()+25, true);
		root.getChildren().add(train.getImageView());
		root.getChildren().add(train2.getImageView());
		for(CrossingGate gate: mapBuilder.getAllGates()) { //make all gates observers of trains
			train.addObserver(gate);
			train2.addObserver(gate);
		}
		// Sets up a repetitive loop i.e., in handle that runs the actual simulation
		new AnimationTimer(){

			@Override
			public void handle(long now) {
				//start annimations
				createCar();
				train.move();
				train2.moverev();
				
				for(CrossingGate gate: mapBuilder.getAllGates())
					gate.operateGate();
				
				if (train.offScreen())
					train.reset();
				if (train2.offScreenrev())
					train2.reset();
						
				clearCars();				
			}
		}.start();
	}
	
	// Clears cars as they leave the simulation
	private void clearCars(){
		Collection<Road> roads = mapBuilder.getRoads();
		for(Road road: roads){			
			if (road.getCarFactory()!= null){
				ArrayList<Car> junkCars = road.getCarFactory().removeOffScreenCars();	
				mapDisplay.removeCarImages(junkCars);
			}
		}
	}
	
	private void createCar(){
		Collection<Road> roads = mapBuilder.getRoads();
		for(Road road: roads){
			if (road.getCarFactory() != null){
				if ((int)(Math.random() * 100) == 15){
					Car car = road.getCarFactory().buildCar();
					if (car != null){
						root.getChildren().add(car.getImageView());
					}
				}
			}
		}
	}
	
	public static void main(String[] args){
		launch(args);
	}
}

