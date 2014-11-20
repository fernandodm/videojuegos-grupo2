package soccer;

import java.awt.Color;
import java.awt.Font;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.GameScene;
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
			String resultado = "Resultado: " + this.getScene().getMarcador().getMarcadorLocal() + " - " + this.getScene().getMarcador().getMarcadorVisitante();
			if(this.getScene().getMarcador().getMarcadorLocal() > this.getScene().getMarcador().getMarcadorVisitante()){
				this.getGame().setCurrentScene(((SoccerGame)this.getGame()).buildScene("Ganaste!!! Presione N para volver a jugar.", resultado));
			}else{
				if(this.getScene().getMarcador().getMarcadorLocal() < this.getScene().getMarcador().getMarcadorVisitante()){
					this.getGame().setCurrentScene(((SoccerGame)this.getGame()).buildScene("Perdiste!!! Presione N para volver a jugar.", resultado));
				}else{
					this.getGame().setCurrentScene(((SoccerGame)this.getGame()).buildScene("Empataron!!! Presione N para volver a jugar.", resultado));
				}
			}
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
