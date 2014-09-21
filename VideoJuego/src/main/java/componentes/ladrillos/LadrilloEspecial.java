package componentes.ladrillos;

import java.awt.Color;

import utils.Task;
import utils.Timer;
import Arkanoid.Colision;
import Arkanoid.Vector;

import com.uqbar.vainilla.DeltaState;
import componentes.Marcador;
import componentes.Pelota;
import componentes.Raqueta;

public abstract class LadrilloEspecial extends Ladrillo {

	private boolean colisiona = false;
	private Raqueta raqueta;
	private Pelota pelota;
	private Timer timer;

	public LadrilloEspecial(Color color, int ancho, int alto, double x,
			double y, Marcador marcador, Raqueta raqueta, Pelota pelota) {
		super(color, ancho, alto, x, y, marcador);
		this.raqueta = raqueta;
		this.setPelota(pelota);

	}

	public abstract void estadoInicial();

	public abstract void aplicar();

	public abstract boolean estaAplicadoAcutalmente();

	public void aplicarConTimer() {
		if (!estaAplicadoAcutalmente()) {
			System.out.println(getClass().getName());
			this.aplicar();
			this.setTimer(new Timer(5, new Task() {

				@Override
				public void execute() {
					estadoInicial();
				}
			}));
		}

	}

	@Override
	public void update(DeltaState deltaState) {
		if (colisiona) {
			this.setY(this.getY() + 0.3);
			Vector nuevaPosicion = new Vector(this.getX(), this.getY() - 30);
			if (Colision.colisiona(raqueta, this, nuevaPosicion)) {
				this.getScene().removeComponent(this);
				this.aplicarConTimer();
			}
		}
	}

	public boolean collision(DeltaState deltaState, Pelota pelota,
			Vector nuevaPosicion) {
		if (colisiona = Colision.colisiona(this, pelota, nuevaPosicion)) {
			super.sumarPts(50);
			super.sumarVida();
			Colision.apply(this, pelota, nuevaPosicion);
		}
		return colisiona;
	}

	public Pelota getPelota() {
		return pelota;
	}

	public void setPelota(Pelota pelota) {
		this.pelota = pelota;
	}

	public Raqueta getPaleta() {
		return raqueta;
	}

	public void setPaleta(Raqueta paleta) {
		this.raqueta = paleta;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

}
