package soccer;

import java.util.ArrayList;
import java.util.List;

import soccer.estados.EstadoJugadorNoSeleccionado;
import soccer.estados.EstadoJugadorSeleccionado;
import soccer.estados.EstadoPelota;
import soccer.estados.EstadoPelotaEnJuego;
import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Sprite;
import com.uqbar.vainilla.events.constants.Key;

public class Pelota extends GameComponent<SoccerScene> {

	private EstadoPelota estadoPelota;

	private Sprite image;
	private int time;
	int estado = 0;
	private Jugador jugador;
	private ArrayList<Sprite> listSprites = new ArrayList<Sprite>();

	public boolean enRemate=false;
	private int direccionRemate;
	private double potencia;
	private double gravedad = 4;
	private int ultimaDireccion = Direccion.DOWN; //para que el jugador remate cuando esta quieto
	private boolean remateEnCorner = false;
		

	public Pelota(String pelotaPath){
		super((Sprite.fromImage(pelotaPath).crop(0,0,63,63).scaleTo(15, 15)),625, 295);
	
		this.image = Sprite.fromImage(pelotaPath);
		
		for (int i = 0 ; i < 2; i++) {
			for(int j = 0; j< 3; j++ ){
				listSprites.add(this.image.crop(63*i, 63*j,63,63).scaleTo(15, 15));
			}
		}		
		this.estadoPelota = new EstadoPelotaEnJuego(this);
	}

	
	@Override
	public void update(DeltaState deltaState) {
		this.estadoPelota.update(deltaState);

	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public boolean isRemateEnCorner() {
		return remateEnCorner;
	}


	public void setRemateEnCorner(boolean remateEnCorner) {
		this.remateEnCorner = remateEnCorner;
	}
	
	public void moverPelotaPorRemate(DeltaState deltaState) {
		colisionoConJugador();
		if (this.enRemate) {
			if (Desplazador.getInstance().hayQueDesplazarCamara()) {
				moverCamara(deltaState);
			} else {
				moverPelota(deltaState);
			}
		}
	}

	public boolean isEnRemate() {
		return enRemate;
	}


	public void setEnRemate(boolean enRemate) {
		this.enRemate = enRemate;
	}


	public double getPotencia() {
		return potencia;
	}


	public void setPotencia(double potencia) {
		this.potencia = potencia;
	}


	public void colisionoConJugador() {
		List<Jugador> jugadores = super.getScene().getJugadores();

		for (Jugador jugador : jugadores) {
			Vector nuevaPosicion = new Vector(jugador.getX(), jugador.getY());
			if (Colision.mustApply(this, jugador, nuevaPosicion)
					&& !jugador.isEstaSeleccionado()) {
				this.cambiarJugadorSeleccionado(jugador);
				this.estadoPelota = new EstadoPelotaEnJuego(this);
				this.remateEnCorner = false;
			}
		}
	}

	public void cambiarJugadorSeleccionado(Jugador jugador) {
		this.setY(jugador.getY());
		this.setX(jugador.getX());
		
		this.getJugador().setEstado(new EstadoJugadorNoSeleccionado(this.getJugador()));
		this.getJugador().setEstaSeleccionado(false);

		jugador.setEstado(new EstadoJugadorSeleccionado(jugador));
		jugador.setEstaSeleccionado(true);

		this.setJugador(jugador);
		enRemate = false;
	}

	private void moverCamara(DeltaState deltaState){
		switch (direccionRemate) {
		case Direccion.UP:
			Desplazador.getInstance()
					.desplazarComponentes(potencia, deltaState);
			break;
		case Direccion.LEFT:
			this.setX(getX()-potencia);
			this.ejecutarSpritePelota();
			break;
		case Direccion.RIGHT:
			this.setX(getX()+potencia);
			this.ejecutarSpritePelota();
			break;
		case Direccion.UPRIGHT:
			Desplazador.getInstance().desplazarComponentes(potencia, deltaState);
			this.setX(getX()+potencia);
			break;
		case Direccion.UPLEFT:
			Desplazador.getInstance().desplazarComponentes(potencia, deltaState);
			this.setX(getX()-potencia);
			break;
		case Direccion.DOWN:
			Desplazador.getInstance().desplazarComponentes(potencia * -1,
					deltaState);
			break;
		case Direccion.DOWNRIGHT:
			Desplazador.getInstance().desplazarComponentes(potencia*-1, deltaState);
			this.setX(getX()+potencia);
			break;
		case Direccion.DOWNLEFT:
			Desplazador.getInstance().desplazarComponentes(potencia*-1, deltaState);
			this.setX(getX()-potencia);
			break;
		}
		this.ejecutarSpritePelota();
		potencia -= gravedad*deltaState.getDelta();
		if(potencia < 0){
			enRemate=false;
			this.estadoPelota = new EstadoPelotaEnJuego(this);
		}
	}

	private void moverPelota(DeltaState deltaState) {
		//Si la pelota esta en remate
		if(!isRemateEnCorner()){
			this.remateEnJuego(deltaState);
		}else{
			this.remateEnCorner(deltaState);
		}
		
		this.ejecutarSpritePelota();
	}


	public void remateEnCorner(DeltaState deltaState) {
		switch (direccionRemate) {
		case Direccion.UP:
			this.setY(getY()-potencia);
			this.ejecutarSpritePelota();
			break;
		case Direccion.DOWN:
			this.setY(getY()+potencia);
			this.ejecutarSpritePelota();
			break;
		case Direccion.UPRIGHT:
			this.setY((getY()+0.7)-potencia);
			this.setX((getX()+1)+potencia);
			this.ejecutarSpritePelota();
			break;
		case Direccion.UPLEFT:
			this.setY((getY()+0.7)-potencia);
			this.setX((getX()-1)-potencia);
			this.ejecutarSpritePelota();
			break;
		case Direccion.DOWNLEFT:
			this.setY((getY()-0.7)+potencia);
			this.setX((getX()-1)-potencia);
			this.ejecutarSpritePelota();
			break;
		case Direccion.DOWNRIGHT:
			this.setY((getY()-0.7)+potencia);
			this.setX((getX()+1)+potencia);
			this.ejecutarSpritePelota();
			break;
			
		}
		
		potencia-= gravedad*deltaState.getDelta();
		if(potencia < 0){
			enRemate=false;
			this.estadoPelota = new EstadoPelotaEnJuego(this);
			this.remateEnCorner = false;
		}
	}


	public void remateEnJuego(DeltaState deltaState) {
		switch (direccionRemate) {
		case Direccion.UP:
			this.setY(getY()-potencia);
			this.ejecutarSpritePelota();
			break;
		case Direccion.DOWN:
			this.setY(getY()+potencia);
			this.ejecutarSpritePelota();
			break;
		case Direccion.LEFT:
			this.setX(getX()-potencia);
			this.ejecutarSpritePelota();
			break;
		case Direccion.RIGHT:
			this.setX(getX()+potencia);
			this.ejecutarSpritePelota();
			break;
		case Direccion.UPRIGHT:
			this.setY(getY()-potencia);
			this.setX(getX()+potencia);
			this.ejecutarSpritePelota();
			break;
		case Direccion.UPLEFT:
			this.setY(getY()-potencia);
			this.setX(getX()-potencia);
			this.ejecutarSpritePelota();
			break;
		case Direccion.DOWNLEFT:
			this.setY(getY()+potencia);
			this.setX(getX()-potencia);
			this.ejecutarSpritePelota();
			break;
		case Direccion.DOWNRIGHT:
			this.setY(getY()+potencia);
			this.setX(getX()+potencia);
			this.ejecutarSpritePelota();
			break;
			
		}
		
		potencia-= gravedad*deltaState.getDelta();
		if(potencia < 0){
			enRemate=false;
			this.estadoPelota = new EstadoPelotaEnJuego(this);
		}
	}

	public void activarRemate(DeltaState deltaState) {
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

	public void ejecutarMovimiento(DeltaState deltaState, double x, double y) {
		int direccion = Direccion.obtenerDireccion(deltaState);
		switch (direccion) {
		case Direccion.UP:
			this.ultimaDireccion = Direccion.UP; 
			this.setY(y-14);
			this.setX(x + 3);
			this.ejecutarSpritePelota();
			break;
		case Direccion.DOWN:
			this.ultimaDireccion = Direccion.DOWN; 
			this.setY(y+31);
			this.setX(x);
			this.ejecutarSpritePelota();
			break;
		case Direccion.LEFT:
			this.ultimaDireccion = Direccion.LEFT; 
			this.setX(x-14);
			this.setY(y+4);
			this.ejecutarSpritePelota();
			break;
		case Direccion.RIGHT:
			this.ultimaDireccion = Direccion.RIGHT; 
			this.setX(x+31);
			this.setY(y+9);
			this.ejecutarSpritePelota();
			break;
		case Direccion.UPRIGHT:
			this.ultimaDireccion = Direccion.UPRIGHT; 
			this.setX(x + 25);
			this.setY(y - 10);
			this.ejecutarSpritePelota();
			break;
		case Direccion.UPLEFT:
			this.ultimaDireccion = Direccion.UPLEFT; 
			this.setX(x - 8);
			this.setY(y - 10);
			this.ejecutarSpritePelota();
			break;
		case Direccion.DOWNRIGHT:
			this.ultimaDireccion = Direccion.DOWNRIGHT; 
			this.setX(x + 30);
			this.setY(y + 23);
			this.ejecutarSpritePelota();
			break;
		case Direccion.DOWNLEFT:
			this.ultimaDireccion = Direccion.DOWNLEFT; 
			this.setX(x - 13);
			this.setY(y + 21);
			this.ejecutarSpritePelota();
			break;
		}				
	}

	public void ejecutarSpritePelota() {
		if (this.time == 12) {
			this.setAppearance(this.listSprites.get(this.estado));
			this.estado = (this.estado + 1) % this.listSprites.size();
			this.time = 0;
		}
		this.time++;

	}

	public EstadoPelota getEstadoPelota() {
		return estadoPelota;
	}

	public void setEstadoPelota(EstadoPelota estadoP) {
		this.estadoPelota = estadoP;
	}
	
	public int getUltimaDireccion() {
		return ultimaDireccion;
	}


	public void setUltimaDireccion(int ultimaDireccion) {
		this.ultimaDireccion = ultimaDireccion;
	}
}
