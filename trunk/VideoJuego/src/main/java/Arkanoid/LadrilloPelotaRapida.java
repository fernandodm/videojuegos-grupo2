package Arkanoid;

import java.awt.Color;

public class LadrilloPelotaRapida extends LadrilloEspecial {

	public LadrilloPelotaRapida(Color color, int ancho, int alto, double x,
			double y, Marcador marcador, Paleta paleta, Pelota pelota) {
		super(color, ancho, alto, x, y, marcador, paleta, pelota);
	}

	@Override
	public void aplicar(LadrilloEspecial ladrillo) {
		ladrillo.getPelota().setVelocidad(500);

	}

}
