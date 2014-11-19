package soccer;

import java.awt.Color;
import java.awt.Font;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Label;

public class Tiempo extends GameComponent<SoccerScene> {
	private int minutos = 0;
	private int segundos = 0;
	private int actualizar;
	
	public Tiempo(double x, double y, Color color) {		
		super(new Label(new Font("verdana",  Font.BOLD, 18), color, "0" ), x, y);
	}
	

	@Override
	public void update(DeltaState deltaState) {
		if(minutos == 4){
			this.getGame().setCurrentScene(new FinalScene());
		}
		((Label)this.getAppearance()).setText("Tiempo: " + this.minutos + ":" + this.segundos);
		this.ejecutar();
		super.update(deltaState);
	}
	
	public void ejecutar(){
		if(actualizar == 100){
			if(segundos == 59){
				segundos = 0;
				minutos++;
			}else{
				segundos++;
		
			}
			actualizar = 0;
		}
		
		actualizar++;
	}
}
