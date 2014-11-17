package soccer;

import java.awt.Color;
import java.awt.Font;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Label;

public class Marcador extends GameComponent<SoccerScene>{
	private int marcadorLocal = 0;
	private int marcadorVisitante = 0;
	
	public Marcador() {
		super(new Label(new Font("verdana",  Font.BOLD, 20), Color.BLACK, "0" ), 0, 10);
	}

	
	@Override
	public void update(DeltaState deltaState) {
		((Label)this.getAppearance()).setText(this.marcadorLocal + ":" + this.marcadorVisitante);
		this.actualizarMarcadores();
		super.update(deltaState);
	}


	private void actualizarMarcadores() {
		Pelota pelota = super.getScene().getPelota();
		boolean gol = (pelota.getX()>550 && pelota.getX()<700 );
		boolean arcoSuperior = pelota.getY() < 50 && gol; 
		boolean arcoInferior = pelota.getY() > 528 && gol;
		if(arcoSuperior){
			this.marcadorLocal++;
			super.getScene().resetScene(this);
		}
		if(arcoInferior){
			this.marcadorVisitante++;
			super.getScene().resetScene(this);
		}
	}
}
