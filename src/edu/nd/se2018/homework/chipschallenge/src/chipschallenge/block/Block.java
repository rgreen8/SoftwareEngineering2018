package chipschallenge.block;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Block {
	int scale = 20;
	Image blockImage;
	ImageView blockImageView;
	
	public abstract ImageView getImageView();

}
