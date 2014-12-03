package soccer;

import java.awt.Color;

import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
import com.uqbar.vainilla.appearances.Circle;

public class LabelSeleccionado extends GameComponent<GameScene>{
	
	public LabelSeleccionado(double x, double y){
		super(new Circle(Color.LIGHT_GRAY, 8),x,y);
	}

}
