package asteroids;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Sprite;

public class Nave extends GameComponent<GameScene>{

	
	public Nave(String imagePath, double x, double y){
		super(Sprite.fromImage(imagePath),x,y);
	}

}
