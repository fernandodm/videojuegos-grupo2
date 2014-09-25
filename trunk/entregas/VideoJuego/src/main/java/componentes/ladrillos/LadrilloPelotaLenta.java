package componentes.ladrillos;

import java.awt.Color;

import componentes.Marcador;
import componentes.Pelota;
import componentes.Raqueta;

public class LadrilloPelotaLenta extends LadrilloEspecial {

	
	private double velocidadInicialPelota;

	public LadrilloPelotaLenta(Color color, int ancho, int alto, double x, double y,
			Marcador marcador, Raqueta raqueta, Pelota pelota) {
		super(color, ancho, alto, x, y, marcador, raqueta, pelota);
		velocidadInicialPelota=this.getPelota().getVelocidad();
	}

	@Override
	public void aplicar() {
		this.getPelota().setVelocidad(200);
		
	}

	@Override
	public void estadoInicial() {
		this.getPelota().setVelocidad(velocidadInicialPelota);
	}

	@Override
	public boolean estaAplicadoAcutalmente() {
		return velocidadInicialPelota > this.getPelota().getVelocidad();
	}


}
