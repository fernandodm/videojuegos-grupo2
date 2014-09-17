package Arkanoid;

import java.awt.Color;

import com.uqbar.vainilla.DeltaState;

public class LadrilloPaletaPegajosa extends LadrilloEspecial {

	public LadrilloPaletaPegajosa(Color color, int ancho, int alto, double x,
			double y, Marcador marcador, Paleta paleta, Pelota pelota) {
		super(color, ancho, alto, x, y, marcador, paleta, pelota);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void aplicar() {
		this.getPaleta().setPegajosa(true);
	}

}
