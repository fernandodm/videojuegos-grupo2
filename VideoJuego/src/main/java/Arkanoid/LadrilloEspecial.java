package Arkanoid;

import java.awt.Color;

import com.uqbar.vainilla.DeltaState;

public abstract class LadrilloEspecial extends Ladrillo {

	private boolean colisiona =false;
	private Paleta paleta;
	private Pelota pelota;
		
	public LadrilloEspecial(Color color, int ancho, int alto, double x,
			double y, Marcador marcador, Paleta paleta, Pelota pelota) {
		super(color, ancho, alto, x, y, marcador);
		this.paleta = paleta;
		this.setPelota(pelota);
	}

	public abstract void aplicar(LadrilloEspecial ladrillo);
	
	@Override
	public void update(DeltaState deltaState) {
		if(colisiona){
			this.setY(this.getY()+0.3);
			Vector nuevaPosicion = new Vector(this.getX(), this.getY() - 30);
			if(Colision.colisiona(paleta, this, nuevaPosicion)){
				this.getScene().removeComponent(this);
				this.aplicar(this);
			}
		}
		
		
	}
		
	public boolean collision(DeltaState deltaState, Pelota pelota, Vector nuevaPosicion) {
		if (colisiona=Colision.colisiona(this, pelota, nuevaPosicion)) {
			super.sumarPts(50);
			super.sumarVida();
			Colision.apply(this, pelota,nuevaPosicion);
		}
		return colisiona;
	}

	public Pelota getPelota() {
		return pelota;
	}

	public void setPelota(Pelota pelota) {
		this.pelota = pelota;
	}
	
	public Paleta getPaleta() {
		return paleta;
	}

	public void setPaleta(Paleta paleta) {
		this.paleta = paleta;
	}
	
}
