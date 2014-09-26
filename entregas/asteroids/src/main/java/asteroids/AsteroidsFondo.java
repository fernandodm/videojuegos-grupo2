package asteroids;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Sprite;

public class AsteroidsFondo extends GameComponent<GameScene>{

	
	public AsteroidsFondo(String imagePath, double x, double y){
		super(Sprite.fromImage(imagePath),x,y);
	}

}
