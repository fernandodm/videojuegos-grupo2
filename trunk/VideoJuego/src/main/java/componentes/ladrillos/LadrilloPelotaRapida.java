package componentes.ladrillos;

import java.awt.Color;

import componentes.Marcador;
import componentes.Pelota;
import componentes.Raqueta;

public class LadrilloPelotaRapida extends LadrilloEspecial {

	public LadrilloPelotaRapida(Color color, int ancho, int alto, double x,
			double y, Marcador marcador, Raqueta raqueta, Pelota pelota) {
		super(color, ancho, alto, x, y, marcador, raqueta, pelota);
	}

	@Override
	public void aplicar() {
		this.getPelota().setVelocidad(500);

	}

}
