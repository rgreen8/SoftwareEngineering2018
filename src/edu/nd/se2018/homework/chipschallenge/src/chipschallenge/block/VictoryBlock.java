package chipschallenge.block;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class VictoryBlock extends Block{
	Image blockImage  = new Image("file:///Users/RyanGreen/git/SoftwareEngineering2018/chip/textures/portal.png",scale,scale,true,true);;
	ImageView blockImageView = new ImageView(blockImage);
	@Override
	public ImageView getImageView() {
		return blockImageView;
	}

}
