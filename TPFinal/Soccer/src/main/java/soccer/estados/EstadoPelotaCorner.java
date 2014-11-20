package soccer.estados;

import java.util.List;

import soccer.Colision;
import soccer.Direccion;
import soccer.Jugador;
import soccer.Pelota;
import soccer.Vector;

import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.events.constants.Key;

public class EstadoPelotaCorner extends EstadoPelota {


	public boolean enRemate=false;
	private int direccionRemate;
	private double potencia;
	private double gravedad = 4;
	private int ultimaDireccion = Direccion.DOWN; //para que el jugador remate cuando esta quieto
	
	public EstadoPelotaCorner(Pelota pelota) {
		this.setPelota(pelota);
	}

	@Override
	public void update(DeltaState deltaState) {
		int direccion = Direccion.obtenerDireccion(deltaState);
		for(Jugador jugador : this.getPelota().getJugador().equipoContrario().getJugadores()){
			if(jugador.isEstaSeleccionado()){
				jugador.setEstaSeleccionado(false);
				jugador.setEstado(new EstadoJugadorNoSeleccionado(jugador));
				break;
			}
		}
		if(this.noApretoDireccion(direccion) || this.getPelota().isEnRemate()){
			if(deltaState.isKeyPressed(Key.ENTER)){
				this.getPelota().activarRemate(deltaState);
//				this.activarRemate(deltaState);
				this.getPelota().getJugador().setEstado(new EstadoJugadorSeleccionado(this.getPelota().getJugador()));
				this.getPelota().setRemateEnCorner(true);
			}
			this.getPelota().moverPelotaPorRemate(deltaState);
//			this.moverPelotaPorRemate(deltaState);
		}
	}
		
	public boolean isEnRemate() {
		return enRemate;
	}

	public void setEnRemate(boolean enRemate) {
		this.enRemate = enRemate;
	}

	public int getDireccionRemate() {
		return direccionRemate;
	}

	public void setDireccionRemate(int direccionRemate) {
		this.direccionRemate = direccionRemate;
	}

	public double getPotencia() {
		return potencia;
	}

	public void setPotencia(double potencia) {
		this.potencia = potencia;
	}

	public double getGravedad() {
		return gravedad;
	}

	public void setGravedad(double gravedad) {
		this.gravedad = gravedad;
	}

	public int getUltimaDireccion() {
		return ultimaDireccion;
	}

	public void setUltimaDireccion(int ultimaDireccion) {
		this.ultimaDireccion = ultimaDireccion;
	}

	private void activarRemate(DeltaState deltaState) {
		
		//Direccion del remate
		direccionRemate = Direccion.obtenerDireccion(deltaState);
		potencia = 2;
		//Si se apreta enter para pegarle a la pelota
		if(deltaState.isKeyPressed(Key.ENTER)){
			if(direccionRemate == 0){
				direccionRemate = ultimaDireccion;
			}
			enRemate=true;
		}
	}
	
	public void moverPelotaPorRemate(DeltaState deltaState) {
		colisionoConJugador();
		moverPelota(deltaState);
	}
	
	public void colisionoConJugador() {
		List<Jugador> jugadores = this.getPelota().getScene().getJugadores();

		for (Jugador jugador : jugadores) {
			Vector nuevaPosicion = new Vector(jugador.getX(), jugador.getY());
			if (Colision.mustApply(this, jugador, nuevaPosicion)
					&& !jugador.isEstaSeleccionado()) {
				this.cambiarJugadorSeleccionado(jugador);
				this.getPelota().setEstadoPelota(new EstadoPelotaEnJuego(this.getPelota()));
			}
		}
	}

	public void cambiarJugadorSeleccionado(Jugador jugador) {
		this.setY(jugador.getY());
		this.setX(jugador.getX());
		
		this.getPelota().getJugador().setEstado(new EstadoJugadorNoSeleccionado(this.getPelota().getJugador()));
		this.getPelota().getJugador().setEstaSeleccionado(false);

		jugador.setEstado(new EstadoJugadorSeleccionado(jugador));
		jugador.setEstaSeleccionado(true);

		this.getPelota().setJugador(jugador);
		enRemate = false;
	}
	
	public void moverPelota(DeltaState deltaState) {
		//Si la pelota esta en remate
			switch (direccionRemate) {
			case Direccion.UP:
				this.getPelota().setY(getY()-potencia);
				this.getPelota().ejecutarSpritePelota();
				break;
			case Direccion.DOWN:
				this.getPelota().setY(getY()+potencia);
				this.getPelota().ejecutarSpritePelota();
				break;
			case Direccion.LEFT:
				this.getPelota().setX(getX()-potencia);
				this.getPelota().ejecutarSpritePelota();
				break;
			case Direccion.RIGHT:
				this.getPelota().setX(getX()+potencia);
				this.getPelota().ejecutarSpritePelota();
				break;
			case Direccion.UPRIGHT:
				this.getPelota().setY(getY()-potencia);
				this.getPelota().setX(getX()+potencia);
				this.getPelota().ejecutarSpritePelota();
				break;
			case Direccion.UPLEFT:
				this.getPelota().setY(getY()-potencia);
				this.getPelota().setX(getX()-potencia);
				this.getPelota().ejecutarSpritePelota();
				break;
			case Direccion.DOWNLEFT:
				this.getPelota().setY(getY()+potencia);
				this.getPelota().setX(getX()-potencia);
				this.getPelota().ejecutarSpritePelota();
				break;
			case Direccion.DOWNRIGHT:
				this.getPelota().setY(getY()+potencia);
				this.getPelota().setX(getX()+potencia);
				this.getPelota().ejecutarSpritePelota();
				break;
			}
			this.getPelota().ejecutarSpritePelota();
			potencia-= gravedad*deltaState.getDelta();
			if(potencia < 0){
				enRemate=false;
				this.getPelota().setEstadoPelota(new EstadoPelotaEnJuego(this.getPelota()));
			}
	}

	public boolean noApretoDireccion(int direccion){
		return Direccion.LEFT != direccion && Direccion.DOWN != direccion 
				&& Direccion.UP != direccion && Direccion.UP != direccion
				&& Direccion.DOWNLEFT != direccion && Direccion.UPLEFT != direccion
				&& Direccion.DOWNRIGHT != direccion && Direccion.UPRIGHT != direccion;
	}
}
