package componentes.ladrillos;

import java.awt.Color;

import com.uqbar.vainilla.appearances.Appearance;
import com.uqbar.vainilla.appearances.Rectangle;

import componentes.Marcador;
import componentes.Pelota;
import componentes.Raqueta;

public class LadrilloPaletaChica extends LadrilloEspecial {

	public LadrilloPaletaChica(Color color, int ancho, int alto, double x,
			double y, Marcador marcador, Raqueta raqueta, Pelota pelota) {
		super(color, ancho, alto, x, y, marcador, raqueta, pelota);
	}

	@Override
	public void aplicar() {
		Appearance app = this.getPaleta().getAppearance();
		int largo = (int) app.getWidth() - 20;
		int alto = (int) app.getHeight();
		this.getPaleta().setAppearance(new Rectangle(Color.BLACK, largo, alto));

	}

}
