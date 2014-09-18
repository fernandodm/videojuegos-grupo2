package componentes.ladrillos;

import java.awt.Color;

import componentes.Marcador;
import componentes.Pelota;
import componentes.Raqueta;

public class LadrilloPaletaPegajosa extends LadrilloEspecial {

	public LadrilloPaletaPegajosa(Color color, int ancho, int alto, double x,
			double y, Marcador marcador, Raqueta raqueta, Pelota pelota) {
		super(color, ancho, alto, x, y, marcador, raqueta, pelota);
	}
	
	@Override
	public void aplicar() {
		this.getPaleta().setPegajosa(true);
	}

}
