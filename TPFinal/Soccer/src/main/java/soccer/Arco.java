package soccer;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Sprite;

public class Arco extends GameComponent<SoccerScene>{
	
	public Arco(String arcoPath){
		
		super(Sprite.fromImage(arcoPath).scaleTo(210, 100), 525,8);
		
	
	}

}
