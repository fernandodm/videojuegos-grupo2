package soccer;

import java.util.ArrayList;
import java.util.List;

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
	
	private boolean puedeEjecutarMovimiento = true;
		

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
				this.enRemate = false;
				this.puedeEjecutarMovimiento = false;
			}
		}
	}

	public void cambiarJugadorSeleccionado(Jugador jugador) {
		this.setY(jugador.getY());
		this.setX(jugador.getX());

		if(this.jugador == null){

			
		}else{
			
		if(jugador instanceof ArqueroVisitante
				&& this.getJugador() instanceof JugadorLocal){
			
			List<Jugador> jugadoresv = super.getScene().getJugadoresVisitantes();

			for (Jugador jv : jugadoresv) {
				jv.setEstadoNoSeleccionado();
				jv.setEstaSeleccionado(false);
				jv.flag=false;
			}
			
			jugador.flag = true;
			jugador.setEstaSeleccionado(true);
			jugador.setEstadoSeleccionado();
			
		}else 
			if(jugador instanceof JugadorLocal
				&& this.getJugador() instanceof ArqueroVisitante){
				
			List<Jugador> jugadoresl = super.getScene().getJugadoresLocales();
			for (Jugador jl : jugadoresl) {
				jl.setEstadoNoSeleccionado();
				jl.setEstaSeleccionado(false);
				jl.flag=false;
			}
			
			jugador.flag = true;
			jugador.setEstaSeleccionado(true);
			jugador.setEstadoSeleccionado();
			
		}else 
			if(jugador instanceof JugadorVisitante
				&& !(this.getJugador() instanceof JugadorVisitante)){
			
			List<Jugador> jugadoresv = super.getScene().getJugadoresVisitantes();
			for (Jugador jv : jugadoresv) {
				jv.setEstadoNoSeleccionado();
				jv.setEstaSeleccionado(false);
				jv.flag=false;
			}
			jugador.setEstadoSeleccionado();
			jugador.setEstaSeleccionado(true);
			jugador.flag=true;
			
			List<Jugador> jugadores = super.getScene().getJugadoresLocales();

			for (Jugador j : jugadores) {
				j.setEstadoNoSeleccionado();
				j.setEstaSeleccionado(false);
				j.flag=false;
			}
			this.getJugador().setEstadoSeleccionado();
			this.getJugador().setEstaSeleccionado(true);
			
		}else if(jugador instanceof JugadorVisitante
				&& this.getJugador() instanceof JugadorVisitante){
			jugador.setEstadoSeleccionado();
			jugador.setEstaSeleccionado(true);
			jugador.flag=true;
			this.getJugador().setEstadoNoSeleccionado();
			this.getJugador().setEstaSeleccionado(false);
			this.getJugador().flag=false;
			
		}else if(!(jugador instanceof JugadorVisitante)
				&& this.getJugador() instanceof JugadorVisitante){
			
			List<Jugador> jugadoresl = super.getScene().getJugadoresLocales();

			for (Jugador jl : jugadoresl) {
				jl.setEstadoNoSeleccionado();
				jl.setEstaSeleccionado(false);
				jl.flag=false;
			}
			jugador.setEstadoSeleccionado();
			jugador.setEstaSeleccionado(true);
			jugador.flag=true;
			
			List<Jugador> jugadores = super.getScene().getJugadoresVisitantes();

			for (Jugador j : jugadores) {
				j.setEstadoNoSeleccionado();
				j.setEstaSeleccionado(false);
				j.flag=false;
			}
			this.getJugador().setEstadoNoSeleccionado();
			this.getJugador().setEstaSeleccionado(false);
			this.getJugador().flag=true;
			
		}else if(!(jugador instanceof JugadorVisitante)
				&& !(this.getJugador() instanceof JugadorVisitante)){
			jugador.setEstadoSeleccionado();
			jugador.setEstaSeleccionado(true);
			jugador.flag=true;
			this.getJugador().setEstadoNoSeleccionado();
			this.getJugador().setEstaSeleccionado(false);
			this.getJugador().flag=false;
		}
		
		}
		


		this.setJugador(jugador);
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
	
	public void activarRemateCPU(DeltaState deltaState,int direccion) {
		//Direccion del remate

		direccionRemate =direccion;
		potencia = 2;
		if(direccionRemate == 0){
				direccionRemate = ultimaDireccion;
		}
		enRemate=true;

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


	public int getDireccionRemate() {
		return direccionRemate;
	}


	public void setDireccionRemate(int direccionRemate) {
		this.direccionRemate = direccionRemate;
	}


	public boolean isPuedeEjecutarMovimiento() {
		return puedeEjecutarMovimiento;
	}


	public void setPuedeEjecutarMovimiento(boolean puedeEjecutarMovimiento) {
		this.puedeEjecutarMovimiento = puedeEjecutarMovimiento;
	}
}
