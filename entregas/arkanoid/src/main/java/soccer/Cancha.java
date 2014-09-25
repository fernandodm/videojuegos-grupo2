package soccer;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Sprite;

public class Cancha extends GameComponent<SoccerScene>{

	
	public Cancha(String imagePath1, double x, double y){
		super(Sprite.fromImage(imagePath1),x,y);
	}
	@Override
	public void update(DeltaState deltaState) {

	}
}
	

