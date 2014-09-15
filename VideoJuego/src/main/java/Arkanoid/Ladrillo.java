package Arkanoid;

import java.awt.Color;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Rectangle;

public class Ladrillo extends GameComponent<ArkanoidScene>{
	
	private Marcador marcador;
		
	public Ladrillo(Color color, int ancho, int alto, double x, double y,  Marcador marcador){
		super(new Rectangle(color, ancho, alto), x * 50, y * 20);
		this.marcador = marcador;
	}
	
	public boolean collision(DeltaState deltaState, Pelota pelota, Vector nuevaPosicion) {
		boolean colisiona;
		if (colisiona=Colision.colisiona(this, pelota, nuevaPosicion)) {
			this.getScene().removeComponent(this);
			Colision.apply(this, pelota,nuevaPosicion);
			sumarPts(50);
		}
		return colisiona;
	}

	private void sumarPts(int pts) {
		this.marcador.setValue(this.marcador.getValue() + pts);	
	}
}
