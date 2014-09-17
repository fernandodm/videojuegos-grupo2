package Arkanoid;

import java.awt.Color;

import com.uqbar.vainilla.appearances.Appearance;
import com.uqbar.vainilla.appearances.Rectangle;

public class LadrilloPaletaGrande extends LadrilloEspecial {

	public LadrilloPaletaGrande(Color color, int ancho, int alto, double x,
			double y, Marcador marcador, Paleta paleta, Pelota pelota) {
		super(color, ancho, alto, x, y, marcador, paleta, pelota);
	}

	@Override
	public void aplicar(LadrilloEspecial ladrillo) {
		Appearance app = ladrillo.getPaleta().getAppearance();
		int largo = (int) app.getWidth() + 35;
		int alto = (int) app.getHeight();
		ladrillo.getPaleta().setAppearance(new Rectangle(Color.BLACK, largo, alto));
	}

}
