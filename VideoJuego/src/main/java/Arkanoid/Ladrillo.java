package Arkanoid;

import java.awt.Color;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Rectangle;

public class Ladrillo extends GameComponent<ArkanoidScene>{
	
	private Pelota pelota;
	private Marcador marcador;
	
	public Ladrillo(Color color, int ancho, int alto, double x, double y, Pelota pelota, Marcador marcador){
		super(new Rectangle(color, ancho, alto), x * 50, y * 20);
		this.pelota = pelota;
		this.marcador = marcador;
	}
	
	@Override
	public void update(DeltaState deltaState) {
		Vector nuevaPosicion = pelota
				.getDireccion()
				.producto(pelota.getVelocidad()* deltaState.getDelta())
				.suma(new Vector(pelota.getX(), pelota.getY()));
		if (Colision.colisiona(this, pelota, nuevaPosicion)) {
			this.getScene().removeComponent(this);
			Colision.apply(this, pelota,nuevaPosicion);
			sumarPts(100);
		}
	}

	private void sumarPts(int i) {
		this.marcador.setValue(this.marcador.getValue() + 50);	
	}
}
