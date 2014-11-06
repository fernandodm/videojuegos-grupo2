package soccer;

import java.util.ArrayList;
import java.util.List;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Sprite;
import com.uqbar.vainilla.events.constants.Key;

public class Pelota extends GameComponent<SoccerScene> {

	private EstadoPelota estadoP = null;

	private double velocidad;
	private Sprite image;
	private int time;
	int estado = 0;
	private Jugador jugador;
	private ArrayList<Sprite> listSprites = new ArrayList<Sprite>();

	public Pelota(String pelotaPath, double velocidad) {

		super(
				(Sprite.fromImage(pelotaPath).crop(0, 0, 63, 63)
						.scaleTo(15, 15)), 650, 300);
		this.velocidad = velocidad;

		this.image = Sprite.fromImage(pelotaPath);

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3; j++) {
				listSprites.add(this.image.crop(63 * i, 63 * j, 63, 63)
						.scaleTo(15, 15));
			}
		}

		// this.estadoP = new EstadoPelotaEnJuego(this);
	}

	public double getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(double velocidad) {
		this.velocidad = velocidad;
	}

	public boolean enRemate = false;
	private int direccionRemate;
	private double potencia;

	@Override
	public void update(DeltaState deltaState) {

		if (this.estadoP == null) {
			for (Jugador jugador : super.getScene().getJugadores()) {
				Vector nuevaPosicion = new Vector(jugador.getX(),
						jugador.getY());
				if (Colision.mustApply(this, jugador, nuevaPosicion)
						&& jugador.isEstaSeleccionado()) {
					this.setJugador(jugador);
					this.ejecutarMovimiento(deltaState, jugador.getX(),
							jugador.getY());
					jugador.flag = true;
					this.activarRemate(deltaState);
				} else {
					jugador.flag = false;
				}
			}
			this.moverPelotaPorRemate(deltaState);
		} else {
			this.estadoP.update(deltaState);
		}
		
		if(this.getX() < 170 ){
			this.estadoP = new EstadoPelotaFueraDeJuego(this);
		}

		 System.out.println(this.getX());
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	void moverPelotaPorRemate(DeltaState deltaState) {
		colisionoConJugador();
		if (this.enRemate) {
			if (Desplazador.getInstance().hayQueDesplazarCamara()) {
				moverCamara(deltaState);
			} else {
				moverPelota();
			}
		}
	}

	public void colisionoConJugador() {
		List<Jugador> jugadores = super.getScene().getJugadores();

		for (Jugador jugador : jugadores) {
			Vector nuevaPosicion = new Vector(jugador.getX(), jugador.getY());
			if (Colision.mustApply(this, jugador, nuevaPosicion)
					&& !jugador.isEstaSeleccionado()) {
				this.cambiarJugadorSeleccionado(jugador);
			}
		}
	}

	public void cambiarJugadorSeleccionado(Jugador jugador) {
		this.setY(jugador.getY());
		this.setX(jugador.getX());

		this.getJugador().setEstaSeleccionado(false);

		jugador.setEstaSeleccionado(true);

		this.setJugador(jugador);
		enRemate = false;
		potencia = -1;

	}

	private void moverCamara(DeltaState deltaState) {
		switch (direccionRemate) {
		case Direccion.UP:
			Desplazador.getInstance()
					.desplazarComponentes(potencia, deltaState);
			break;
		case Direccion.UPRIGHT:
			Desplazador.getInstance()
					.desplazarComponentes(potencia, deltaState);
			this.setX(getX() + (0.4 * potencia));
			break;
		case Direccion.UPLEFT:
			Desplazador.getInstance()
					.desplazarComponentes(potencia, deltaState);
			this.setX(getX() - (0.4 * potencia));
			break;
		case Direccion.DOWN:
			Desplazador.getInstance().desplazarComponentes(potencia * -1,
					deltaState);
			break;
		case Direccion.DOWNRIGHT:
			Desplazador.getInstance().desplazarComponentes(potencia * -1,
					deltaState);
			this.setX(getX() + (0.4 * potencia));
			break;
		case Direccion.DOWNLEFT:
			Desplazador.getInstance().desplazarComponentes(potencia * -1,
					deltaState);
			this.setX(getX() - (0.4 * potencia));
			break;
		default:
			moverPelota();
			break;
		}
		this.ejecutarSpritePelota();
		potencia -= 1;
		if (potencia < 0) {
			enRemate = false;
		}
	}

	private void moverPelota() {
		// Si la pelota esta en remate
		switch (direccionRemate) {
		case Direccion.UP:
			this.setY(getY() - potencia);
			this.ejecutarSpritePelota();
			break;
		case Direccion.DOWN:
			this.setY(getY() + potencia);
			this.ejecutarSpritePelota();
			break;
		case Direccion.LEFT:
			this.setX(getX() - potencia);
			this.ejecutarSpritePelota();
			break;
		case Direccion.RIGHT:
			this.setX(getX() + potencia);
			this.ejecutarSpritePelota();
			break;
		case Direccion.UPRIGHT:
			this.setY(getY() - potencia);
			this.setX(getX() + potencia);
			this.ejecutarSpritePelota();
			break;
		case Direccion.UPLEFT:
			this.setY(getY() - potencia);
			this.setX(getX() - potencia);
			this.ejecutarSpritePelota();
			break;
		case Direccion.DOWNLEFT:
			this.setY(getY() + potencia);
			this.setX(getX() - potencia);
			this.ejecutarSpritePelota();
			break;
		case Direccion.DOWNRIGHT:
			this.setY(getY() + potencia);
			this.setX(getX() + potencia);
			this.ejecutarSpritePelota();
			break;
		}
		this.ejecutarSpritePelota();
		potencia -= 1;
		if (potencia < 0) {
			enRemate = false;
		}
	}

	void activarRemate(DeltaState deltaState) {
		// Direccion del remate
		direccionRemate = Direccion.obtenerDireccion(deltaState);
		potencia = 22;
		// Si se apreta enter para pegarle a la pelota
		if (deltaState.isKeyPressed(Key.ENTER) && direccionRemate != 0) {

			enRemate = true;
		}
	}

	public void ejecutarMovimiento(DeltaState deltaState, double x, double y) {
		int direccion = Direccion.obtenerDireccion(deltaState);
		switch (direccion) {
		case Direccion.UP:
			this.setY(y - 14);
			this.setX(x + 3);
			this.ejecutarSpritePelota();
			break;
		case Direccion.DOWN:
			this.setY(y + 26);
			this.setX(x);
			this.ejecutarSpritePelota();
			break;
		case Direccion.LEFT:
			this.setX(x - 11);
			this.setY(y + 4);
			this.ejecutarSpritePelota();
			break;
		case Direccion.RIGHT:
			this.setX(x + 25);
			this.setY(y + 9);
			this.ejecutarSpritePelota();
			break;
		case Direccion.UPRIGHT:
			this.setX(x + 20);
			this.setY(y - 5);
			this.ejecutarSpritePelota();
			break;
		case Direccion.UPLEFT:
			this.setX(x - 6);
			this.setY(y - 8);
			this.ejecutarSpritePelota();
			break;
		case Direccion.DOWNRIGHT:
			this.setX(x + 25);
			this.setY(y + 7);
			this.ejecutarSpritePelota();
			break;
		case Direccion.DOWNLEFT:
			this.setX(x - 5);
			this.setY(y + 15);
			this.ejecutarSpritePelota();
			break;
		}
	}

	private void ejecutarSpritePelota() {
		if (this.time == 12) {
			this.setAppearance(this.listSprites.get(this.estado));
			this.estado = (this.estado + 1) % this.listSprites.size();
			this.time = 0;
		}
		this.time++;

	}

	public EstadoPelota getEstadoP() {
		return estadoP;
	}

	public void setEstadoP(EstadoPelota estadoP) {
		this.estadoP = estadoP;
	}
}
