package chipschallenge.block;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WalkBlock extends Block{
	int scale = 20;
	Image blockImage = new Image("file:///Users/RyanGreen/git/SoftwareEngineering2018/chip/textures/BlankTile.png",scale,scale,true,true);
	ImageView blockImageView  = new ImageView(blockImage);
	@Override
	public ImageView getImageView() {
		return blockImageView;
	}

}

