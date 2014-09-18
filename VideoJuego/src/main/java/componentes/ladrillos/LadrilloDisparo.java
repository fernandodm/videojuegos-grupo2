package componentes.ladrillos;

import java.awt.Color;

import componentes.Marcador;
import componentes.Pelota;
import componentes.Raqueta;

public class LadrilloDisparo extends LadrilloEspecial {

	public LadrilloDisparo(Color color, int ancho, int alto, double x,
			double y, Marcador marcador, Raqueta raqueta, Pelota pelota) {
		super(color, ancho, alto, x, y, marcador, raqueta, pelota);
	}

	@Override
	public void aplicar() {
		this.getPaleta().setPistolera(true);
	}

}
